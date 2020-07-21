package com.urban.androidhomework.ui.charecterdetail

import com.urban.androidhomework.model.LocationResponse

class PersonDetailModelImpl(private val personDetailRepo: PersonDetailRepo) : PersonDetail.Location.Model {

    override fun getLocationData(locationId: String, onSuccess: (locationRes: LocationResponse) -> Unit,
                                 onFail: (errorMsg: String) -> Unit) {
        personDetailRepo.getLocationData(locationId = locationId, onSuccess = { locationRes ->
            if (locationRes.isSuccessful) {
                locationRes.body()?.let { onSuccess(it) }
            } else {
                locationRes.errorBody()?.let { onFail(it.toString()) }
            }
        })
    }
}