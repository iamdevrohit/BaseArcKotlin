package com.basearc.kotlin.view.dialog

import android.app.Dialog
import android.content.Context
import android.widget.Button
import com.basearc.kotlin.R

class MyCustomDialog (context: Context) : Dialog(context) {

    private var onConfirmClickListener: (() -> Unit)? = null

    init {
        this.setCanceledOnTouchOutside(false)
        this.setCancelable(false)
        setContentView(R.layout.dialog_custom)
        val btnConfirm = findViewById<Button>(R.id.btn_confirm)
        btnConfirm.setOnClickListener {
            onConfirmClickListener?.invoke()
            dismiss()
        }
    }

    fun setOnConfirmClickListener(listener: () -> Unit) {
        onConfirmClickListener = listener
    }
}