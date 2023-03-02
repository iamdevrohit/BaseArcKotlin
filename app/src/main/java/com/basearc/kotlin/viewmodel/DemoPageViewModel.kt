package com.basearc.kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import com.basearc.kotlin.base.Base
import com.basearc.kotlin.base.BaseViewModel

class DemoPageViewModel : BaseViewModel() {

    val text = MutableLiveData<String>("hello")

    var base: Base? = null
    fun attachBase(base: Base): Unit {
        this.base=base;
    }


    fun callApi(): Unit {
        base?.show_loader()
    }
}