package com.urban.androidhomework.dialog

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AlertDialog


class ErrorDialog(private val activity: Activity, private val title: String,
                  private val msg: String, private var runnable: Runnable?) {

    private var dialog: AlertDialog? = null

    val isShowing: Boolean get() = dialog?.isShowing ?: false

    init {
        configureDialog()
    }

    @SuppressLint("InflateParams")
    private fun configureDialog(): AlertDialog? {

        if (activity.isFinishing) return null

        val builder = AlertDialog.Builder(activity)
        dialog = builder.create()
        (dialog as AlertDialog).setCancelable(false)
        (dialog as AlertDialog).setTitle(title)
        (dialog as AlertDialog).setMessage(msg)
        (dialog as AlertDialog).setCanceledOnTouchOutside(false)
        (dialog as AlertDialog).setButton(AlertDialog.BUTTON_NEUTRAL, "Continue.."
        ) { dialog, which ->
            dialog.dismiss()
            runnable?.run()
        }

        return dialog as AlertDialog
    }

    fun show() = dialog?.show()

    fun dismiss() {
        runnable = null
        dialog?.dismiss()
    }
}