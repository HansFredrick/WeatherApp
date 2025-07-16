    package com.example.myweatherapp.presentation.home

    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.material3.Icon
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.graphics.painter.Painter
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import com.example.myweatherapp.R

    class HomeComposeTest {
        @Composable
        fun mainContainer(
            locationName: String,
            temperature: String,
            condition: String,
            conditionImage: Painter,  // Changed to Painter type
            time: String
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(text = locationName)
                Text(text = "$temperature°C")
                Row {
                    Text(text = condition)
                    Image(
                        painter = conditionImage,
                        contentDescription = condition
                    )
                }
                Row {
                    Column { }
                    Text(text = time)
                }
                Text(text = "FORECAST DAYS")
                LazyColumn { }
                Row { }
                Row { }
            }
        }
        @Preview
        @Composable
        fun defualtPreview(){
            val sampleImage: Painter = painterResource(id = R.drawable.ic_sunny) // Replace with your actual drawable

            mainContainer(
                locationName = "New York",
                temperature = "24",
                condition = "Sunny",
                conditionImage = sampleImage,
                time = "10:00 AM"
            )

        }

    }

