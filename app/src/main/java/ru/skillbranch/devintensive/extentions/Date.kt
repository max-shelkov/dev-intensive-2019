package ru.skillbranch.devintensive.extentions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24
//const val MONTH = DAY * 31
//const val YEAR = MONTH *12




fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") : String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Long, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time
    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
//        TimeUnits.MONTH -> value * MONTH
//        TimeUnits.YEAR -> value * YEAR
    }
    this.time = time
    return this
}

fun Date?.humanizeDiff(date : Date = Date()): String {
    return when (val dif = date.time - (this?.time?:Date().time)) {
        in (0 until SECOND) -> "только что"
        in (SECOND until 45 * SECOND) -> "несколько секунд назад"
        in (45 * SECOND until 75 * SECOND) -> "минуту назад"
        in (75 * SECOND until 45 * MINUTE) -> "${TimeUnits.MINUTE.plural((dif / MINUTE).toInt())} назад"
        in (45 * MINUTE until 75 * MINUTE) -> "час назад"
        in (75 * MINUTE until 22 * HOUR) -> "${TimeUnits.HOUR.plural((dif / HOUR).toInt())} назад"
        in (22 * HOUR until 26 * HOUR) -> "день назад"
        in (26 * HOUR until 360 * DAY) -> "${TimeUnits.DAY.plural((dif / DAY).toInt())} назад"
        else -> "больше года назад"
    }
}


enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY,
//    MONTH,
//    YEAR
    ;
    fun plural (value: Int = 0): String {
        return if (value in (5..20)) {
            when (this) {
                SECOND -> "$value секунд"
                MINUTE -> "$value минут"
                HOUR -> "$value часов"
                DAY -> "$value дней"
//                MONTH -> "$value месяцев"
//                YEAR -> "$value лет"
            }
        } else {
            when ((value % 10).toLong()) {
                1L -> when (this) {
                    SECOND -> "$value секунду"
                    MINUTE -> "$value минуту"
                    HOUR -> "$value час"
                    DAY -> "$value день"
//                    MONTH -> "$value месяц"
//                    YEAR -> "$value год"
                }
                in 2L..4L -> when (this) {
                    SECOND -> "$value секунды"
                    MINUTE -> "$value минуты"
                    HOUR -> "$value часа"
                    DAY -> "$value дня"
//                    MONTH -> "$value месяца"
//                    YEAR -> "$value года"
                }
                0L, in 5L..9L -> when (this) {
                    SECOND -> "$value секунд"
                    MINUTE -> "$value минут"
                    HOUR -> "$value часов"
                    DAY -> "$value дней"
//                    MONTH -> "$value месяцев"
//                    YEAR -> "$value лет"
                }
                else -> {
                    "unknown"
                }
            }
        }
    }
}

