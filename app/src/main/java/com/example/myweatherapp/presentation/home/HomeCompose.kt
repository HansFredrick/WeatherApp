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
import androidx.compose.material3.HorizontalDivider
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
    fun WeatherScreen(
//        viewModel: HomeViewModel = hiltViewModel()
    ) {
//        val weatherState by viewModel._uiState.collect()

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
                        .padding(30.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Baguio City",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "21°C",
                        style = MaterialTheme.typography.displayLarge,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    // Condition icon and name
                    Surface(
                        color = Color(0x25B3B3B7),
                        modifier = Modifier
                            .size(360.dp)
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
                                painter = painterResource(R.drawable.ic_sunny),
                                contentDescription = "Sunny",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(260.dp)
                                    .padding(bottom = 5.dp)
                            )
                            Text(
                                text = "Sunny",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color(0xFFE3DEDE),
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
                            // Date and time
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

                            HorizontalDivider(
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

                    // Daily forecast
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
                    // Index values
                    ValueIndex(
                        indexType = "AIR QUALITY INDEX: ",
                        indexValue = "10",
                        indexMeaning = "Good",
                        image = R.drawable.aqi_good
                    )
                    ValueIndex(
                        indexType = "UV INDEX: ",
                        indexValue = "0.0",
                        indexMeaning = "Good",
                        image = R.drawable.aqi_good
                    )
                    // Measurement values
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

                        HorizontalDivider(
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
    fun ValueIndex(indexType: String, indexMeaning: String, indexValue: String, image: Int){
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
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .weight(3f)
                        .padding(start = 8.dp) // Added left padding for better spacing
                ) {
                    // Index Type Label (e.g., "AIR QUALITY INDEX")
                    Text(
                        text = indexType.uppercase(), // Make uppercase for consistency
                        style = MaterialTheme.typography.labelMedium, // More appropriate than bodySmall
                        color = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier.padding(bottom = 4.dp) // Reduced padding
                    )

                    // Index Value (e.g., "42")
                    Text(
                        text = indexValue,
                        style = MaterialTheme.typography.headlineSmall, // Slightly larger than titleLarge
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )

                    // Index Meaning (e.g., "Good")
                    Text(
                        text = indexMeaning,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.9f) // Slightly transparent
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
                            modifier = Modifier.size(27.dp)
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