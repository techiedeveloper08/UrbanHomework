package com.urban.androidhomework.ui.home

import com.urban.androidhomework.model.Person

interface Home {

    interface Persons {
        interface Model {
            fun getPersonsData(onSuccess: (personsRes: Person) -> Unit, onFail: (errorMsg: String) -> Unit)
        }

        interface Controller {
            fun onShowLoading(showLoading: Boolean)
            fun onPersonsDataSuccess(personsRes: Person)
            fun onPersonsDataFail(errorMsg: String)
        }

        interface Presenter {
            fun initialize()
        }
    }
}