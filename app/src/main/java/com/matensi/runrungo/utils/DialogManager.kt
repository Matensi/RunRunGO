package com.matensi.runrungo.utils

import android.app.AlertDialog
import android.content.Context
import com.matensi.runrungo.R

object DialogManager {
    fun showLocEnableDialog(context: Context, listener: Listener){
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()
        dialog.setTitle(R.string.location_disabled)
        dialog.setMessage(context.getString(R.string.location_dialog_message))
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Да"){
            _,_ -> listener.onClick()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Нет"){
                _,_ -> dialog.dismiss()
        }
        dialog.show()
    }
    interface Listener{
        fun onClick()
    }
}
