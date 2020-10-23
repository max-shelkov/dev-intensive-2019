package ru.skillbranch.devintensive.utils

import java.lang.StringBuilder
import java.util.*

object Utils {
    fun parseFullName(fullName : String?) : Pair<String?, String?>{
        //homework
        return if (fullName==null || fullName.isEmpty() || fullName.trim().isEmpty()) {
            Pair(null, null)
        } else if (!fullName.contains(" ")){
            Pair(fullName, null)
        } else {
            val parts: List<String>? = fullName.split(" ")
            val firstName = parts?.getOrNull(0)
            val lastName = parts?.getOrNull(1)
            Pair(firstName, lastName)
        }
    }

    fun transliteration(payload: String, divider : String = " "): String {
        var nickname = "";
        val rusLetters : List<String> = listOf("а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "ч", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я")
        val latLetters : List<String> = listOf("a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "i", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "c", "ch", "sh", "sh", "", "i", "", "e", "yu", "ya")
        payload.forEach { literal ->
            val num = rusLetters.indexOf(literal.toString().toLowerCase(Locale("ru")))
            nickname += if (num<0) divider else latLetters[num]
        }
        return nickname
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var res : String? = ""
        if ((firstName == null && lastName == null)
                || (firstName?.trim()?.isEmpty() != false && lastName?.trim()?.isEmpty() != false)) {
            res = null
        } else {
            res += if (firstName?.trim().isNullOrBlank() || firstName?.trim()?.length!! == 0) ""
            else firstName.trim()[0].toUpperCase()
            res += if (lastName?.trim().isNullOrBlank() || lastName?.trim()?.length!! == 0) ""
            else lastName.trim()[0].toUpperCase()
        }
        return res
    }


}