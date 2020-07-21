package com.urban.androidhomework.ui.home

import com.urban.androidhomework.model.Person
import retrofit2.Response

interface PersonsRepo {
    fun getPersonsData(onSuccess: (personsRes: Response<Person>) -> Unit)
}