package com.urban.androidhomework.ui.charecterdetail

import com.urban.androidhomework.model.LocationResponse

interface PersonDetail {

    interface Location {
        interface Model {
            fun getLocationData(locationId: String,
                                onSuccess: (locationRes: LocationResponse) -> Unit,
                                onFail: (errorMsg: String) -> Unit)
        }

        interface Controller {
            fun onShowLoading(showLoading: Boolean)
            fun onLocationDataSuccess(personsRes: LocationResponse)
            fun onLocationDataFail(errorMsg: String)
        }

        interface Presenter {
            fun initialize(locationId: String)
        }
    }
}