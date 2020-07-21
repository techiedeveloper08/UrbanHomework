package com.urban.androidhomework

import com.urban.androidhomework.model.LocationResponse
import org.junit.Before
import org.junit.Test

class PersonDetailPresenterImplTests {

    private lateinit var controller: PersonDetailPresenterTestController

    private val locationId = "20"

    private val locationData = LocationResponse(id = 20, name = "Earth (Replacement Dimension)", dimension = "Replacement Dimension", created = "2017-11-18T19:33:01.173Z", type = "Planet")


    @Before
    fun setUp() {
        controller = PersonDetailPresenterTestController()
    }

    @Test
    fun `given api location data person detail with location id is correct`() {

        val expectedResult = locationData.copy()

        controller.apply {
            presenter.initialize(locationId)

            // Assert
            assertThatResponseDataEquals(expectedResult)
        }
    }
}