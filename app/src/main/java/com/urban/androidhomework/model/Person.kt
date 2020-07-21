package com.urban.androidhomework.model

import java.io.Serializable

data class Person(
        val results: List<PersonData>
) : Serializable

data class PersonData(
        val id: Int,
        val name: String,
        val image: String,
        val location: Location,
        val gender: String,
        var created: String
) : Serializable

data class Location(val name: String, val url: String) : Serializable
