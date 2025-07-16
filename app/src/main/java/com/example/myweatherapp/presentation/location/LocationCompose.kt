package com.example.myweatherapp.presentation.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.R
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen() {
    var location by remember { mutableStateOf("") }

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

        Surface(
            color = Color.Black.copy(alpha = 0.3f),
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = location,
                    onValueChange = { location = it },
                    label = {
                        Text(
                            text = "Enter city name",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x43B3B3B7),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "Search",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    singleLine = true
                )
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .width(340.dp)
                        .padding(bottom = 16.dp).padding(top = 16.dp)
                ) {
                    items(4){
                            index ->
                        LocationResultItem()
                    }

                }
            }
        }

    }

}

@Composable
fun LocationResultItem(
    locationDisplayName: String = "Baguio, Cordillera Administrative Region, 2600, Philippines",
    longitude: String = "120.593°",
    latitude: String = "16.411°"
) {
    Surface(
        color = Color(0x28FFFFFF),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Location Name
            Text(
                text = locationDisplayName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Coordinates Section
            Surface(
                color = Color(0x43B3B3B7),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    CoordinateRow(
                        label = "LONGITUDE",
                        value = longitude,
                        iconId = R.drawable.ic_longitude
                    )

                    VerticalDivider(
                        color = Color.White.copy(alpha = 0.2f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    CoordinateRow(
                        label = "LATITUDE",
                        value = latitude,
                        iconId = R.drawable.ic_latitude
                    )
                }
            }
        }
    }
}

@Composable
private fun CoordinateRow(
    label: String,
    value: String,
    iconId: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.White.copy(alpha = 0.8f),
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun LocationScreenPreview() {
    MyWeatherAppTheme {
        LocationScreen()
    }
}