package com.example.myweatherapp.presentation.sharedintent

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntentBus @Inject constructor() {
    private val _events = MutableSharedFlow<UserIntent>(
        replay = 0,
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val events: SharedFlow<UserIntent> = _events

    suspend fun dispatch(intent: UserIntent) { _events.emit(intent) }
    suspend fun collect(action: suspend (UserIntent) -> Unit) {
        events.collect { intent ->
            try {
                action(intent)
            } catch (e: Exception) {
                // Log or handle errors
            }
        }
    }
}