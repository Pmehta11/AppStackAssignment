package com.android.appstackassignment.viewModel

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.appstackassignment.R
import com.android.appstackassignment.databinding.FragmentForgotBinding
import com.android.appstackassignment.utils.isEmailValid
import com.android.appstackassignment.utils.showToast

class ForgotViewModel  : ViewModel(){

    private var TAG = "Forgot"
    var isLoading = MutableLiveData<Boolean>()
    var email = ObservableField<String>()

    init {
        email.set("")
    }

    fun validated(view: View): Boolean {
        val forgotBinding = DataBindingUtil.findBinding<FragmentForgotBinding>(view)
        bindTextChangeListener(forgotBinding)

           if (email.get().isNullOrEmpty()) {
               forgotBinding?.edtEmail?.error = view.context.getString(R.string.empty_email)
               view.requestFocus()
               return false
           } else if (!isEmailValid(email.get().toString())){
               forgotBinding?.edtEmail?.error = view.context.getString(R.string.invalid_email)
               view.requestFocus()
               return false
           }
           else {
               forgotBinding?.email?.isErrorEnabled = false
               view.clearFocus()
           }


        if(email.get().equals("priyanka@gmail.com")){
            showToast(view.context,view.context.getString(R.string.recovery_mail))
            return true
        }else{
            showToast(view.context,view.context.getString(R.string.invalid_credentials))
            return false
        }

        return true
    }

    private fun bindTextChangeListener(binding: FragmentForgotBinding?) {
        binding?.edtEmail?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (!email.get().isNullOrEmpty()) {
                    binding.email.isErrorEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }


}