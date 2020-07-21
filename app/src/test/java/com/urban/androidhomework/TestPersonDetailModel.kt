package com.urban.androidhomework

import com.urban.androidhomework.model.LocationResponse
import com.urban.androidhomework.ui.charecterdetail.PersonDetail
import com.urban.androidhomework.ui.charecterdetail.PersonDetailModelImpl

class TestPersonDetailModel(
        private val model: PersonDetailModelImpl
) : PersonDetail.Location.Model by model {

    override fun getLocationData(locationId: String, onSuccess: (locationRes: LocationResponse) -> Unit, onFail: (errorMsg: String) -> Unit) {
        model.getLocationData(locationId = locationId, onSuccess = onSuccess, onFail = onFail)
    }
}