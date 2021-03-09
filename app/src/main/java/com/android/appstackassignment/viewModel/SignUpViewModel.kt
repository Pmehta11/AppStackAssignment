package com.android.appstackassignment.viewModel

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.appstackassignment.R
import com.android.appstackassignment.databinding.FragmentLoginBinding
import com.android.appstackassignment.utils.isEmailValid
import com.android.appstackassignment.utils.showToast

class SignUpViewModel  : ViewModel(){

    private var TAG = "SignUp"
    var isLoading = MutableLiveData<Boolean>()
    var email = ObservableField<String>()
    var password = ObservableField<String>()

    init {
        email.set("")
        password.set("")
    }

    fun validated(view: View): Boolean {
        val loginBinding = DataBindingUtil.findBinding<FragmentLoginBinding>(view)
        bindTextChangeListener(loginBinding)

           if (email.get().isNullOrEmpty()) {
               loginBinding?.edtEmail?.error = view.context.getString(R.string.empty_email)
               view.requestFocus()
               return false
           } else if (!isEmailValid(email.get().toString())){
               loginBinding?.edtEmail?.error = view.context.getString(R.string.invalid_email)
               view.requestFocus()
               return false
           }
           else {
               loginBinding?.email?.isErrorEnabled = false
               view.clearFocus()
           }

        if (password.get().isNullOrEmpty()) {
            showToast(view.context,view.context.getString(R.string.empty_password))
            view.requestFocus()
            return false
        } else {
            view.clearFocus()
        }

        if(email.get().equals("priyanka@gmail.com")&& password.get().equals("qwerty")){
            return true
        }else{
            showToast(view.context,view.context.getString(R.string.invalid_credentials))
            return false

        }

        return true
    }

    private fun bindTextChangeListener(binding: FragmentLoginBinding?) {
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

        binding?.edtPassword?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (!password.get().isNullOrEmpty()) {
                    binding.password.isErrorEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }


}