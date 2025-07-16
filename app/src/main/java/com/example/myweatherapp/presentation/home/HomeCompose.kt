package com.example.myweatherapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.R

class HomeCompose {
    @Composable
    fun WeatherScreen() {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.ic_afternoon),
                contentDescription = "Baguio City Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Content Overlay
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black.copy(alpha = 0.3f)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Header: City Name
                    Text(
                        text = "Baguio City",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "21°C",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Surface(
                        color = Color(0x43B3B3B7),
                        modifier = Modifier
                            .size(340.dp)
                            .padding(bottom = 16.dp)
                            .clip(CircleShape),
                        shape = CircleShape
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(android.R.drawable.sym_action_call),
                                contentDescription = "Sunny",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(200.dp)
                                    .padding(bottom = 10.dp)
                            )
                            Text(
                                text = "Sunny",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFEBEFF3),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                        }
                    }

                    Surface(
                        color = Color(0x4F040407),
                        modifier = Modifier
                            .height(120.dp)
                            .width(340.dp)
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Date and Time
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "11:09AM",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.White
                                )
                                Text(
                                    text = "July 10, 2025",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.White.copy(alpha = 0.9f)
                                )
                            }

                            Divider(
                                color = Color(0xC1EBEFF3),
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(3.dp)
                            )

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Philippines",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = "Asia/Manila",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }

                    // Daily Forecast
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier
                            .height(120.dp)
                            .width(340.dp)
                            .padding(bottom = 16.dp)
                    ) {
                        items(3) { index ->
                            val days = listOf("Wednesday", "Thursday", "Friday")
                            val highs = listOf("21°C", "21°C", "21°C")
                            val lows = listOf("19°C", "18°C", "18°C")
                            val conditions = listOf("Sunny", "Sunny", "Cloudy")

                            DayForecast(
                                day = days[index],
                                high = highs[index],
                                low = lows[index],
                                condition = conditions[index]
                            )
                        }
                    }

                    ValueIndex(
                        indexType = "AIR QUALITY INDEX: ",
                        indexValue = "GOOD",
                        image = R.drawable.aqi_good
                    )
                    ValueIndex(
                        indexType = "UV INDEX: ",
                        indexValue = "0.0",
                        image = R.drawable.aqi_good
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ){
                        ValueMeasurements(
                            measurementType = "WIND SPEED:",
                            measurementValueOne = "35.00",
                            measurementValueTwo = "56.00",
                            measurementLabelOne = "MPH",
                            measurementLabelTwo = "KPH"
                        )
                        ValueMeasurements(
                            measurementType = "PRECIPITATION:",
                            measurementValueOne = "76.2",
                            measurementValueTwo = "3.0",
                            measurementLabelOne = "MM",
                            measurementLabelTwo = "IN"
                        )
                    }

                }// end of  parent column
            }// end of parent Surface
        }// end of box
    }// end of function

    @Composable
    fun ValueMeasurements(measurementType: String,
                          measurementValueOne: String,
                          measurementValueTwo: String,
                          measurementLabelOne:String,
                          measurementLabelTwo:String){
        Surface(
            color = Color(0x4F040407),
            modifier = Modifier
                .height(120.dp)
                .width(170.dp),
            shape = RoundedCornerShape(70.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding( top = 10.dp)
            ){
                Text(text = measurementType,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding( bottom = 5.dp))
                Surface(
                    color = Color(0x43B3B3B7),
                    modifier = Modifier
                        .height(60.dp)
                        .width(150.dp),
                    shape = RoundedCornerShape(90.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = measurementValueOne,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = measurementLabelOne,
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }

                        Divider(
                            color = Color.White,
                            modifier = Modifier
                                .height(24.dp)
                                .width(1.dp)
                        )

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = measurementValueTwo,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = measurementLabelTwo,
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }


                }
            }

        }

    }

    @Composable
    fun ValueIndex(indexType: String, indexValue: String, image: Int){
        Surface (color = Color(0x4F040407),
            modifier = Modifier
                .height(120.dp)
                .width(340.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(30.dp)){
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Icon(
                    painter = painterResource(image), // Add your icon
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(140.dp).weight(4f).padding(horizontal = 5.dp)
                )
                Column (
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(3f)
                ){
                    Text(
                        text = indexType,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Text(
                        text = indexValue,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }
    }

    @Composable
    fun DayForecast(day: String,
                    high: String,
                    low: String,
                    condition: String
    ) {
        Surface(
            color = Color(0x4F040407),
            modifier = Modifier
                .height(120.dp)
                .width(100.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = day,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Surface(
                    color = Color(0x28FFFFFF),  // Light translucent white
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(4.dp)
                    ){
                        Icon(
                            painter = painterResource(
                                when (condition) {
                                    "Sunny" -> R.drawable.ic_sunny
                                    else -> R.drawable.ic_cloudy
                                }
                            ),
                            contentDescription = condition,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Column {
                            Text(
                                text = high,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = low,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
                Text(
                    text = condition,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )


            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun WeatherScreenPreview() {
        MaterialTheme {  // Replace with your app's theme
            WeatherScreen()
        }
    }
}