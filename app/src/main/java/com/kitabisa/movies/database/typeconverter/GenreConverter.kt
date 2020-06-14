package com.kitabisa.movies.database.typeconverter

import androidx.room.TypeConverter

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 15/06/20.
 */
class GenreConverter {
    @TypeConverter
    fun gettingListFromString(genreIds: String): List<Int> {
        val list = mutableListOf<Int>()

        val array = genreIds.split(",".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()

        for (s in array) {
            if (s.isNotEmpty()) {
                list.add(s.toInt())
            }
        }
        return list
    }

    @TypeConverter
    fun writingStringFromList(list: List<Int>): String {
        var genreIds=""
        for (i in list) genreIds += ",$i"
        return genreIds
    }}
