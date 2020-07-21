package com.urban.androidhomework.ui.charecterdetail

import com.urban.androidhomework.model.LocationResponse
import retrofit2.Response

interface PersonDetailRepo {
    fun getLocationData(locationId: String, onSuccess: (location: Response<LocationResponse>) -> Unit)
}