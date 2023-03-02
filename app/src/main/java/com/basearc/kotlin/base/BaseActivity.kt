package com.basearc.kotlin.base

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.basearc.kotlin.util.LocaleHelper
import com.basearc.kotlin.view.dialog.MyCustomDialog
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),Base {

    protected lateinit var binding: B
    protected lateinit var viewModel: VM

    protected var activity: Activity? = null

    abstract val layoutResourceId: Int
    abstract val viewModelClass: Class<VM>

    private val permissionRequests = mutableMapOf<Int, (Boolean) -> Unit>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResourceId)
        viewModel = ViewModelProvider(this).get(viewModelClass)
        binding.lifecycleOwner = this
        activity = this;

        bindViewModel()
    }



    abstract fun bindViewModel()



     override fun baseActivity(): Activity? {
         return activity
     }



     override fun baseFragment(): Fragment? {
        return null
     }



     override fun lifeCycleOwner(): LifecycleOwner? {
        return this
     }



     override fun show_loader() {

         val dialog =  MyCustomDialog(this)
         dialog.setOnConfirmClickListener {
             dialog.hide()
         }
         dialog.show()
     }



     override fun show_loader(message: String?) {


     }



     override fun hide_loader() {

         val dialog =  MyCustomDialog(this)
         dialog.hide()
     }



     override fun toast(msg: String?, positive: Boolean) {

         val view: View = findViewById(android.R.id.content)
         val snackbar = Snackbar.make(view, msg!!, Snackbar.LENGTH_LONG)
         snackbar.show()

     }


     override fun changeLanguage(language: String?) {

         LocaleHelper.setLocale(this, language!!)
         recreate()
     }



    override fun toastWithAction(msg: String?,
                                 action: String?,
                                 positive: Boolean,
                                 positiveAction: () -> Unit,
                                 nagativeAction: () -> Unit) {

        val view: View = findViewById(android.R.id.content)
        val snackbar = Snackbar.make(view, msg!!, Snackbar.LENGTH_LONG)
        snackbar.setAction(action) {
          positiveAction()
        }
        snackbar.show()

    }






    //Handle Permission
    override fun requestPermissions(permissions: Array<String>, requestCode: Int, callback: (Boolean) -> Unit) {

        permissionRequests[requestCode] = callback

        val ungrantedPermissions = mutableListOf<String>()

        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ungrantedPermissions.add(permission)
            }
        }

        if (ungrantedPermissions.isEmpty()) {
            callback(true)
        } else {
            ActivityCompat.requestPermissions(this, ungrantedPermissions.toTypedArray(), requestCode)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val callback = permissionRequests[requestCode]

        if (callback != null) {
            val result = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            callback(result)
            permissionRequests.remove(requestCode)
        }
    }

}