package com.urban.androidhomework.api

import com.urban.androidhomework.model.LocationResponse
import com.urban.androidhomework.model.Person
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApi {

    @GET("character/")
    fun allCharacters(): Single<Response<Person>>

    @GET("location/{locationId}")
    fun getLocation(@Path("locationId") locationId: String): Single<Response<LocationResponse>>
}