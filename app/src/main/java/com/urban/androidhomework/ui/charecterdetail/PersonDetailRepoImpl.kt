package com.urban.androidhomework.ui.charecterdetail

import android.annotation.SuppressLint
import com.urban.androidhomework.api.NetworkApi
import com.urban.androidhomework.model.LocationResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Response

class PersonDetailRepoImpl(private val service: NetworkApi) : PersonDetailRepo {

    @SuppressLint("CheckResult")
    override fun getLocationData(locationId: String, onSuccess: (location: Response<LocationResponse>) -> Unit) {
        service.getLocation(locationId = locationId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess)
    }
}