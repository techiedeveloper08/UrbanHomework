package com.urban.androidhomework.ui.home

import android.annotation.SuppressLint
import com.urban.androidhomework.api.NetworkApi
import com.urban.androidhomework.model.Person
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Response

class PersonsRepoImpl(private val service: NetworkApi) : PersonsRepo {

    @SuppressLint("CheckResult")
    override fun getPersonsData(onSuccess: (personsRes: Response<Person>) -> Unit) {
        service.allCharacters()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess)
    }
}