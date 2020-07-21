package com.urban.androidhomework

import com.google.gson.Gson
import com.urban.androidhomework.model.LocationResponse
import com.urban.androidhomework.ui.charecterdetail.PersonDetailRepo
import io.reactivex.Observable
import retrofit2.Response

class TestPersonDetailRepo: PersonDetailRepo {

    private val gson = Gson()

    private val locationDummyData: LocationResponse
        get() {
            return gson.fromJson(PersonDetailTest.locationById, LocationResponse::class.java)
        }

    override fun getLocationData(locationId: String, onSuccess: (location: Response<LocationResponse>) -> Unit) {
        Observable.just(locationDummyData).subscribe {
            onSuccess(Response.success(it))
        }
    }
}