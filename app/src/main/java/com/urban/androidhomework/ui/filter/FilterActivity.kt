package com.urban.androidhomework.ui.filter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.checkbox.MaterialCheckBox
import com.urban.androidhomework.R


class FilterActivity : AppCompatActivity() {

    private lateinit var closeDialog: ImageView
    private lateinit var datesListView: LinearLayout

    private var selectedDates = ArrayList<String>()
    private var datesList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filter_dialog)

        if (intent.hasExtra("dates")) {
            datesList = intent.getSerializableExtra("dates") as ArrayList<String>
        }
        if (intent.hasExtra("selectedDates")) {
            selectedDates = intent.getStringArrayListExtra("selectedDates") as ArrayList<String>
        }

        closeDialog = findViewById(R.id.closeDialog)
        closeDialog.setOnClickListener {
            val intent = Intent()
            intent.putExtra("dates", selectedDates)
            setResult(2, intent)
            finish()
        }

        datesListView = findViewById(R.id.datesList)

        for (date: String in datesList) {
            val dateView = LayoutInflater.from(this@FilterActivity).inflate(R.layout.filter_by_date_view, null)

            val checkBoxDate: MaterialCheckBox = dateView.findViewById(R.id.checkBoxDate)
            checkBoxDate.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    if (!selectedDates.contains(date))
                        selectedDates.add(date)
                } else {
                    selectedDates.remove(date)
                }
            }
            checkBoxDate.text = date

            if (selectedDates.contains(date)) {
                checkBoxDate.isChecked = true
            }

            datesListView.addView(dateView)
        }
    }

    override fun onBackPressed() {
    }
}