package com.basearc.kotlin.base

import androidx.lifecycle.LifecycleOwner
import android.app.Activity
import androidx.fragment.app.Fragment

interface Base {

    fun baseActivity(): Activity?

    fun baseFragment(): Fragment?

    fun lifeCycleOwner(): LifecycleOwner?

    fun show_loader()

    fun hide_loader()

    fun show_loader(message: String?)

    fun toast(msg: String?, positive: Boolean)

    fun toastWithAction(msg: String?,
                        action: String?,
                        positive: Boolean,
                        positiveAction: () -> Unit,
                        nagativeAction: () -> Unit)

    fun requestPermissions(permissions: Array<String>,
                           requestCode: Int,
                           callback: (Boolean) -> Unit)

    fun changeLanguage(language: String?)
}