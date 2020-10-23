package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id : String?,
    var firstName : String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = null,
    var isOnline: Boolean = false
) {
    var introBit : String

    constructor(id: String?, firstName: String?, lastName: String?) : this(
        id,
        firstName,
        lastName,
    null
    )

    constructor(id : String?) : this(id, "John", "Doe");

    private constructor(builder : Builder) : this(builder.id, builder.firstName, builder.lastName){
        this.avatar = builder.avatar
        this.rating = builder.rating
        this.respect = builder.respect
        this.lastVisit = builder.lastVisit
        this.isOnline = builder.isOnline
        this.introBit = builder.introBit
    }

    init {
        introBit = getIntro()
//        println("$id is alive! \n ${if (lastName.equals("Doe")) "his name is $firstName $lastName" else "And his name is $firstName $lastName"}\n" +
//                "${getIntro()}")
    }

    companion object Factory{
        private var lastId : Int = -1;
        fun makeUser(fullName : String?) : User{
            lastId++;
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "${lastId}", firstName = firstName, lastName = lastName)
        }
    }

    private fun getIntro()= """
        text from intro:
        $firstName $lastName
    """.trimIndent()

    fun printMe(){
        println("""
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: $lastVisit
        """.trimIndent())
    }

    class Builder{
        var id : String? = null
        private set
        var firstName : String? = null
        private set
        var lastName: String? = null
        private set
        var avatar: String? = null
        private set
        var rating: Int = 0
        private set
        var respect: Int = 0
        private set
        var lastVisit: Date? = null
        private set
        var isOnline: Boolean = false
        private set
        var introBit:String = ""

        fun id(id : String) : Builder {
            this.id = id
            return this
        }
        fun firstName(firstName : String) : Builder {
            this.firstName = firstName
            return this
        }
        fun lastName(lastName : String) : Builder {
            this.lastName = lastName
            return this
        }
        fun avatar(avatar : String) : Builder {
            this.avatar = avatar
            return this
        }
        fun rating(rating : Int) : Builder {
            this.rating = rating
            return this
        }
        fun respect(respect : Int) : Builder {
            this.respect = respect
            return this
        }
        fun lastVisit(lastVisit : Date) : Builder {
            this.lastVisit = lastVisit
            return this
        }
        fun isOnline(isOnline : Boolean) : Builder {
            this.isOnline = isOnline
            return this
        }
        fun introBit(introBit : String) : Builder {
            this.introBit = introBit
            return this
        }
        fun build() = User(this)
    }
}