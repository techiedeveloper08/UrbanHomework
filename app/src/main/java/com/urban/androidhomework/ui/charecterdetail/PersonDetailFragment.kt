package com.urban.androidhomework.ui.charecterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.urban.androidhomework.R
import com.urban.androidhomework.api.NetworkApi
import com.urban.androidhomework.dialog.ErrorDialog
import com.urban.androidhomework.dialog.LoadingOverlayDialogController
import com.urban.androidhomework.model.LocationResponse
import com.urban.androidhomework.model.Person
import com.urban.androidhomework.model.PersonData
import org.koin.android.ext.android.inject

private const val ARG_PERSON = "person"

class PersonDetailFragment : Fragment(), PersonDetail.Location.Controller {

    private var personName: AppCompatTextView? = null
    private var personGender: AppCompatTextView? = null
    private var personPic: RoundedImageView? = null
    private var locationName: AppCompatTextView? = null
    private var locationType: AppCompatTextView? = null
    private var locationDimension: AppCompatTextView? = null

    private var person: PersonData? = null
    private var loadingDialog: LoadingOverlayDialogController? = null
    private var errorDialog: ErrorDialog? = null
    private lateinit var personsRepo: PersonDetailRepo
    private lateinit var modelImpl: PersonDetailModelImpl
    private lateinit var presenterImpl: PersonDetailPresenterImpl

    var isDetailVisible = false

    private val service: NetworkApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            person = it.getSerializable(ARG_PERSON) as PersonData?
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_person_detail, container, false)
        personName = view.findViewById(R.id.personName)
        personGender = view.findViewById(R.id.personGender)
        personPic = view.findViewById(R.id.personImg)
        locationName = view.findViewById(R.id.locationName)
        locationType = view.findViewById(R.id.locationType)
        locationDimension = view.findViewById(R.id.locationDimension)

        activity?.let { activity ->
            loadingDialog = LoadingOverlayDialogController(activity, Runnable { })

            personName?.text = person?.name
            personGender?.text = person?.gender

            personPic?.let {
                Glide.with(activity)
                        .load(person?.image)
                        .override(1080, 1280)
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round)
                        .into(it)
            }

            personsRepo = PersonDetailRepoImpl(service)
            modelImpl = PersonDetailModelImpl(personsRepo)
            presenterImpl = PersonDetailPresenterImpl(model = modelImpl, controller = this@PersonDetailFragment)

            presenterImpl.initialize(person?.location?.url?.substringAfterLast("/")!!)
        }

        return view
    }

    override fun onLocationDataSuccess(locationRes: LocationResponse) {
        locationName?.text = getString(R.string.locationName, locationRes.name)
        locationType?.text = getString(R.string.locationType, locationRes.type)
        locationDimension?.text = getString(R.string.locationDimension, locationRes.dimension)
    }

    override fun onLocationDataFail(errorMsg: String) {
        locationName?.visibility = View.GONE
        locationType?.visibility = View.GONE
        locationDimension?.visibility = View.GONE

        activity?.let {
            errorDialog = ErrorDialog(it, getString(R.string.errorDialogTitle), errorMsg, Runnable { })
            errorDialog?.show()
        }
    }

    override fun onShowLoading(showLoading: Boolean) {
        if (showLoading) {
            loadingDialog?.show()
        } else {
            loadingDialog?.dismiss()
        }
    }

    companion object {
        fun newInstance(person: PersonData) =
                PersonDetailFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PERSON, person)
                    }
                }
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        isDetailVisible = menuVisible
    }

    fun isFragmentVisible(): Boolean {
        return isVisible
    }
}