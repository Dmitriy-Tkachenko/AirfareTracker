package ru.tk4dmitriy.core.utils.ui

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import java.util.Date

object DatePickerHelper {
    interface DatePickerListener {
        fun onDateSelected(date: Date)
    }

    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

    fun showDatePickerDialog(context: Context, listener: DatePickerListener) {
        calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(context,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                listener.onDateSelected(selectedDate.time)
            }, year, month, dayOfMonth
        )

        datePickerDialog.show()
    }
}