package com.hilt.hiltsample.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hilt.hiltsample.model.Category
import com.hilt.hiltsample.model.ProductsModel
import com.hilt.hiltsample.model.Ranking
import java.util.*


class GenreConverter {

    @TypeConverter
    fun fromCategoryString(value: String): List<Category> {
        val type = object : TypeToken<List<Category>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromCategoryArrayList(list: List<Category>): String {
        val type = object : TypeToken<List<Category>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun fromString(value: String): List<Ranking> {
        val type = object : TypeToken<List<Ranking>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromArrayList(list: List<Ranking>): String {
        val type = object : TypeToken<List<Ranking>>() {}.type
        return Gson().toJson(list, type)
    }


    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}
/*
class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromInstant(value: ProductsModel): Long {
            return value.toEpochMilli()
        }

        @TypeConverter
        @JvmStatic
        fun toInstant(value: ProductsModel): Instant {
            return Instant.ofEpochMilli(value)
        }
    }
}*/
