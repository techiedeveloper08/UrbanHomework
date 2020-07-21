package com.urban.androidhomework

import com.urban.androidhomework.model.LocationResponse
import com.urban.androidhomework.ui.charecterdetail.PersonDetail
import com.urban.androidhomework.ui.charecterdetail.PersonDetailModelImpl
import com.urban.androidhomework.ui.charecterdetail.PersonDetailPresenterImpl
import org.junit.Assert

class PersonDetailPresenterTestController {

    private val personDetailRepo: TestPersonDetailRepo = TestPersonDetailRepo()

    private val theModel: TestPersonDetailModel = TestPersonDetailModel(
            model = PersonDetailModelImpl(personDetailRepo = personDetailRepo)
    )

    private var personResponse: LocationResponse? = null

    private val personDetailController = object : PersonDetail.Location.Controller {
        override fun onShowLoading(showLoading: Boolean) {

        }

        override fun onLocationDataSuccess(personsRes: LocationResponse) {
            personResponse = personsRes
        }

        override fun onLocationDataFail(errorMsg: String) {

        }

    }

    val presenter: PersonDetail.Location.Presenter = PersonDetailPresenterImpl(model = theModel, controller = personDetailController)

    fun assertThatResponseDataEquals(expectedResult: LocationResponse) {
        Assert.assertEquals(
                expectedResult, personResponse
        )
    }
}