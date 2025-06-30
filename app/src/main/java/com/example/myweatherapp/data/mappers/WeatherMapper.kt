package com.example.myweatherapp.data.mappers
//remote entities


//room entities


// domain models
import com.example.myweatherapp.data.entities.currentweather.remote.CurrentWeatherAirQualityRemote
import com.example.myweatherapp.data.entities.currentweather.remote.CurrentWeatherConditionRemote
import com.example.myweatherapp.data.entities.currentweather.remote.CurrentWeatherRemote
import com.example.myweatherapp.data.entities.currentweather.remote.CurrentWeatherResponse
import com.example.myweatherapp.data.entities.currentweather.remote.LocationRemote
import com.example.myweatherapp.data.entities.currentweather.room.CurrentWeatherEntity
import com.example.myweatherapp.data.entities.currentweather.room.LocationEntity
import com.example.myweatherapp.data.entities.currentweather.room.LocationWrapper
import com.example.myweatherapp.data.entities.forecastweather.remote.DayConditionRemote
import com.example.myweatherapp.data.entities.forecastweather.remote.DayRemote
import com.example.myweatherapp.data.entities.forecastweather.remote.ForecastDayRemote
import com.example.myweatherapp.data.entities.forecastweather.remote.ForecastWeatherResponse
import com.example.myweatherapp.data.entities.forecastweather.remote.ForecastXRemote
import com.example.myweatherapp.data.entities.forecastweather.room.DayEntity
import com.example.myweatherapp.data.entities.forecastweather.room.ForecastDayEntity
import com.example.myweatherapp.data.entities.forecastweather.room.ForecastDayWrapper
import com.example.myweatherapp.domain.models.currentweather.CurrentWeather
import com.example.myweatherapp.domain.models.currentweather.CurrentWeatherAirQuality
import com.example.myweatherapp.domain.models.currentweather.CurrentWeatherCondition
import com.example.myweatherapp.domain.models.currentweather.Location
import com.example.myweatherapp.domain.models.currentweather.Weather
import com.example.myweatherapp.domain.models.forecast.Day
import com.example.myweatherapp.domain.models.forecast.DayCondition
import com.example.myweatherapp.domain.models.forecast.ForecastDay
import com.example.myweatherapp.domain.models.forecast.ForecastWeather
import com.example.myweatherapp.domain.models.forecast.ForecastX

// the Following methods are mapper to domain for current weather remote entities

fun CurrentWeatherResponse.toDomain(): Weather {
    return Weather(
        location = this.location.toDomain(),
        current = this.current.toDomain()
    )
}

fun CurrentWeatherAirQualityRemote.toDomain(): CurrentWeatherAirQuality {
    return CurrentWeatherAirQuality(
        usEPAAirQualityIndex = this.usEPAAirQualityIndex
    )
}

fun CurrentWeatherConditionRemote.toDomain(): CurrentWeatherCondition {
    return CurrentWeatherCondition(
        text = this.text,
        icon = this.icon

    )
}

/*
toDomain and toEntity of CurrentWeatherRemote
 */
fun CurrentWeatherRemote.toDomain(): CurrentWeather {
    return CurrentWeather(
        temperatureCelsius = this.temperatureCelsius,
        currentWeatherCondition = this.currentWeatherCondition.toDomain(),
        airQuality = this.airQuality.toDomain(),
        windSpeedMiles = this.windSpeedMiles,
        windSpeedKilometers = this.windSpeedKilometers,
        precipitationMilimeter = this.precipitationMilimeter,
        precipitationInch = this.precipitationInch,
        uvIndex = this.uvIndex
    )
}

fun CurrentWeatherRemote.toEntity(locationID: Int): CurrentWeatherEntity {
    return CurrentWeatherEntity(
        locationId = locationID,
        temperatureCelsius = this.temperatureCelsius,
        currentWeatherConditionText = this.currentWeatherCondition.text,
        currentWeatherConditionIcon = this.currentWeatherCondition.icon,
        airQuality = this.airQuality.usEPAAirQualityIndex,
        windSpeedMiles = windSpeedMiles,
        windSpeedKilometers = windSpeedKilometers,
        precipitationMilimeter = precipitationMilimeter,
        precipitationInch = precipitationInch,
        uvIndex = uvIndex
    )
}

/*
toDomain and toEntity of Location
 */
fun LocationRemote.toDomain(): Location {
    return Location(
        id =null,
        name = name,
        country = country,
        timeZone = timeZone,
        localTime = localTime,
    )
}

fun LocationRemote.toEntity(): LocationEntity {
    return LocationEntity(
        name = name,
        country = country,
        timeZone = timeZone,
        localtime = localTime,
    )
}

fun LocationEntity.toDomain(): Location {
    return Location(
        id = this.locationId,
        name = this.name,
        country = this.country,
        timeZone = this.timeZone,
        localTime = this.localtime
    )
}

fun LocationWrapper.toDomain():Weather{
    return Weather(
        location = this.locationEntity.toDomain(),
        current = this.currentWeatherEntity!!.toDomain()
    )
}
fun LocationWrapper.toDomain2():ForecastX{
    return ForecastX(
        forecastDays = this.forecastDayWrapper!!.map {
            it.toDomain()
        }
    )
}

fun ForecastDayWrapper.toDomain():ForecastDay{
   return ForecastDay(
       date = this.forecastDayEntity.date,
       day = this.dayEntity!!.toDomain()
   )
}

fun DayEntity.toDomain():Day{
    return Day(
        averageTemperatureCelsus = this.averageTemperatureCelsius,
        maximumTemperatureCelsus = this.maximumTemperatureCelsius,
        dayCondition = DayCondition(
            icon = this.dayConditionIcon,
            text = this.dayConditionText)
    )
}
fun CurrentWeatherEntity.toDomain(): CurrentWeather {
    return CurrentWeather(
        temperatureCelsius = temperatureCelsius,
        currentWeatherCondition = CurrentWeatherCondition(
            text = currentWeatherConditionText,
            icon = currentWeatherConditionIcon
        ),
        airQuality = CurrentWeatherAirQuality(
            usEPAAirQualityIndex = airQuality
        ),
        windSpeedMiles = windSpeedMiles,
        windSpeedKilometers = windSpeedKilometers,
        precipitationMilimeter = precipitationMilimeter,
        precipitationInch = precipitationInch,
        uvIndex = uvIndex
    )
}

// the Following methods are mapper to domain for forecast weather remote entities

fun DayConditionRemote.toDomain(): DayCondition {
    return DayCondition(
        text = this.text,
        icon = this.icon
    )
}

fun DayRemote.toDomain(): Day {
    return Day(
        averageTemperatureCelsus = this.averageTemperatureCelsus,
        maximumTemperatureCelsus = this.maximumTemperatureCelsus,
        dayCondition = this.dayCondition.toDomain(),
    )
}

fun ForecastDayRemote.toDomain(): ForecastDay {
    return ForecastDay(
        date = this.date,
        day = this.day.toDomain()
    )
}

fun ForecastXRemote.toDomain(): ForecastX {
    return ForecastX(
        forecastDays = this.forecastDay.map { it.toDomain() }
    )
}

fun ForecastWeatherResponse.toDomain(): ForecastWeather {
    return ForecastWeather(
        forecast = this.forecast.toDomain()
    )
}

fun ForecastDayRemote.toEntity(locationID: Int): ForecastDayEntity {
    return ForecastDayEntity(
        locationId = locationID,
        date = this.date
    )
}

fun DayRemote.toEntity(forecastID: Int): DayEntity {
    return DayEntity(
        forecastDayId = forecastID,
        averageTemperatureCelsius = this.averageTemperatureCelsus,
        maximumTemperatureCelsius = this.maximumTemperatureCelsus,
        dayConditionText = this.dayCondition.text,
        dayConditionIcon = this.dayCondition.text
    )
}


