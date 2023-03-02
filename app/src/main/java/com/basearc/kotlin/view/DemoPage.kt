package com.basearc.kotlin.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.basearc.kotlin.R
import com.basearc.kotlin.base.BaseActivity
import com.basearc.kotlin.databinding.ActivityDemoPageBinding
import com.basearc.kotlin.viewmodel.DemoPageViewModel

class DemoPage : BaseActivity<ActivityDemoPageBinding, DemoPageViewModel>() {

    override val layoutResourceId = R.layout.activity_demo_page
    override val viewModelClass = DemoPageViewModel::class.java

    companion object {
        const val PERMISSIONS_REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.attachBase(this)
    }

    override fun bindViewModel() {

        binding.btnAction.setOnClickListener {

             viewModel.callApi()

            //show loader
            //show_loader()


            //change language
           // changeLanguage(language = "fr")


            // permission request handle
//            requestPermissions(
//                arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO),
//                PERMISSIONS_REQUEST_CODE
//            ) { granted ->
//                if (granted) {
//                    toast("permission granted",false)
//                } else {
//                    toast("permission denied",false)
//                }
//            }

        }



        viewModel.text.observe(lifeCycleOwner()!!, Observer {
            binding.btnAction.setText("${it}")
        })

    }

}