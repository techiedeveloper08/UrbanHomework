package com.urban.androidhomework.ui.home

import com.urban.androidhomework.model.Person

class HomePresenterImpl(
        private val modelImpl: HomeModelImpl,
        private val controller: Home.Persons.Controller)
    : Home.Persons.Presenter {

    override fun initialize() {
        controller.onShowLoading(true)
        modelImpl.getPersonsData(onSuccess = ::onPersonsDataSuccess, onFail = ::onPersonsDataFail)
    }

    private fun onPersonsDataSuccess(personsRes: Person) {
        controller.onShowLoading(false)
        controller.onPersonsDataSuccess(personsRes)
    }

    private fun onPersonsDataFail(errorMsg: String) {
        controller.onShowLoading(false)
        controller.onPersonsDataFail(errorMsg)
    }
}