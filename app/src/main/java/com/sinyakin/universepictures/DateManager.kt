package com.sinyakin.universepictures

import com.google.gson.internal.bind.util.ISO8601Utils
import java.text.ParseException
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

class DateManager {

    fun getCurrentDate(): String = formatDate(Calendar.getInstance().time)

    @Throws(ParseException::class)
    fun addDaysToDate(stringDate: String, daysCount: Int): String {
        val initialDate = ISO8601Utils.parse(stringDate, ParsePosition(0))
        val initialCalendar = Calendar.getInstance().apply { time = initialDate }
        val calendar = initialCalendar.apply { add(Calendar.DATE, daysCount) }
        return formatDate(calendar.time)
    }

    private fun formatDate(date: Date) =
        SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(date)

    companion object {
        private const val DATE_PATTERN = "yyyy-MM-dd"
    }
}