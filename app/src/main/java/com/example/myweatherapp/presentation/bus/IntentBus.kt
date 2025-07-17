package com.example.myweatherapp.presentation.bus

import com.example.myweatherapp.presentation.main.MainIntent
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntentBus @Inject constructor() {
    private val _intents = MutableSharedFlow<MainIntent>(
        replay = 0,
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val intents: SharedFlow<MainIntent> = _intents

    suspend fun dispatch(intent: MainIntent) { _intents.emit(intent) } // used to pass tye intent
    suspend fun collect(action: suspend (MainIntent) -> Unit) { // it to collect the  intent
        intents.collect { intent ->
            try {
                action(intent)
            } catch (e: Exception) {
                e.message?.let { error(it) }
            }
        }
    }
}