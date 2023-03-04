package com.example.newsapplication.common

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


object DateUtil {
    const val dd_MMM_yyyy ="dd MMM yyyy"
    const val PATTERN ="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    /**
     * @param currentDateTime= provide actul date to be parsed  ex.("30-07-2019 14:41:38")
     * @param currentDateTimeFormat= input date format ex. ("dd-MM-yyyy hh:mm:ss")
     * @param newDateTimeFormat= outPut date format ex.("h:mm a 'on' d MMM yyyy")
     * @author Created by 1000292 on 17,June,2020
     * @return it returns list date in string format if exception occures it returns currentDateTime
     */
    fun universalParseDate(currentDateTime: String, currentDateTimeFormat: String, newDateTimeFormat: String): String {
        return try {
            val inputSimpleDateFormat = SimpleDateFormat(currentDateTimeFormat, Locale.getDefault())
            val date = inputSimpleDateFormat.parse(currentDateTime)

            val outputSimpleDateFormat = SimpleDateFormat(newDateTimeFormat, Locale.getDefault())
            outputSimpleDateFormat.format(date!!)
        }catch (e: Exception){
            Log.d("TAG_TIME",e.printStackTrace().toString())
            e.printStackTrace()
            currentDateTime
        }
    }

}