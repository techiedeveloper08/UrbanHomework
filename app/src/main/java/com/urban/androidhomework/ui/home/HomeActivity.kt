package com.urban.androidhomework.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.urban.androidhomework.R
import com.urban.androidhomework.api.NetworkApi
import com.urban.androidhomework.dialog.ErrorDialog
import com.urban.androidhomework.dialog.LoadingOverlayDialogController
import com.urban.androidhomework.model.Person
import com.urban.androidhomework.model.PersonData
import com.urban.androidhomework.ui.charecterdetail.PersonDetailFragment
import com.urban.androidhomework.ui.charecterdetail.PersonDetailFragment.Companion.newInstance
import com.urban.androidhomework.ui.filter.FilterActivity
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat


class HomeActivity : AppCompatActivity(), Home.Persons.Controller, OnPersonClickListener {

    private lateinit var personsRepo: PersonsRepo
    private lateinit var modelImpl: HomeModelImpl
    private lateinit var presenterImpl: HomePresenterImpl
    private lateinit var personsAdapter: PersonsAdapter
    private var personDetailFragment = PersonDetailFragment()

    private lateinit var recyclerView: RecyclerView
    private lateinit var filterFab: ExtendedFloatingActionButton
    private var loadingDialog: LoadingOverlayDialogController? = null
    private var errorDialog: ErrorDialog? = null
    private var personsList: List<PersonData> = ArrayList()
    private var datesList = ArrayList<String>()
    private var selectedDates = ArrayList<String>()

    private var dateFormat = SimpleDateFormat("yyyy-MM-DD")

    private val service: NetworkApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadingDialog = LoadingOverlayDialogController(this@HomeActivity, Runnable { })

        filterFab = findViewById(R.id.extended_fab)
        filterFab.setOnClickListener {
            startActivityForResult(Intent(this@HomeActivity, FilterActivity::class.java)
                    .putExtra("dates", datesList)
                    .putExtra("selectedDates", selectedDates), 0)
        }
        recyclerView = findViewById(R.id.characters_list)
        val layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        personsAdapter = PersonsAdapter(this, onPersonClickListener = this@HomeActivity)
        recyclerView.adapter = personsAdapter

        personsRepo = PersonsRepoImpl(service)
        modelImpl = HomeModelImpl(personsRepo)
        presenterImpl = HomePresenterImpl(modelImpl = modelImpl, controller = this@HomeActivity)

        presenterImpl.initialize()
    }

    override fun onPersonsDataSuccess(personsRes: Person) {
        personsList = personsRes.results

        personsList.forEach {
            val date = dateFormat.format(dateFormat.parse(it.created)!!)
            if (!datesList.contains(date))
                datesList.add(date)
        }
        personsAdapter.setPersonsData(personsList)
    }

    override fun onPersonsDataFail(errorMsg: String) {
        errorDialog = ErrorDialog(this, getString(R.string.errorDialogTitle), errorMsg, Runnable { })
        errorDialog?.show()
    }

    override fun onShowLoading(showLoading: Boolean) {
        if (showLoading) {
            loadingDialog?.show()
        } else {
            loadingDialog?.dismiss()
        }
    }

    override fun onPersonClicked(person: PersonData) {
        filterFab.hide()
        personDetailFragment = newInstance(person)
        supportFragmentManager.beginTransaction().replace(R.id.main_container, personDetailFragment).commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            selectedDates = data.let { it?.getSerializableExtra("dates") } as ArrayList<String>

            if (selectedDates.isNotEmpty()) {
                val filterData = personsList.filter { selectedDates.contains(dateFormat.format(dateFormat.parse(it.created)!!)) }
                personsAdapter.setPersonsData(filterData)
            } else {
                personsAdapter.setPersonsData(personsList)
            }
        }
    }

    override fun onBackPressed() {
        if (personDetailFragment.isFragmentVisible()) {
            filterFab.show()
            for (fragment in supportFragmentManager.fragments) {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        } else {
            super.onBackPressed()
        }
    }
}