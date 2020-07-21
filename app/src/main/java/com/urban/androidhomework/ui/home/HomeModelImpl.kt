package com.urban.androidhomework.ui.home

import com.urban.androidhomework.model.Person

class HomeModelImpl(private val personsRepo: PersonsRepo) : Home.Persons.Model {

    override fun getPersonsData(onSuccess: (personsRes: Person) -> Unit, onFail: (errorMsg: String) -> Unit) {
        personsRepo.getPersonsData(onSuccess = { personData ->
            if (personData.isSuccessful) {
                personData.body()?.let { onSuccess(it) }
            } else {
                personData.errorBody()?.let { onFail(it.toString()) }
            }
        })
    }
}