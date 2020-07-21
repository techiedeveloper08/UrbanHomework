package com.urban.androidhomework.ui.charecterdetail

import com.urban.androidhomework.model.LocationResponse

class PersonDetailPresenterImpl(
        private val model: PersonDetail.Location.Model,
        private val controller: PersonDetail.Location.Controller)
    : PersonDetail.Location.Presenter {

    override fun initialize(locationId: String) {
        controller.onShowLoading(true)
        model.getLocationData(locationId, onSuccess = ::onPersonsDataSuccess, onFail = ::onPersonsDataFail)
    }

    private fun onPersonsDataSuccess(locationRes: LocationResponse) {
        controller.onShowLoading(false)
        controller.onLocationDataSuccess(locationRes)
    }

    private fun onPersonsDataFail(errorMsg: String) {
        controller.onShowLoading(false)
        controller.onLocationDataFail(errorMsg)
    }
}