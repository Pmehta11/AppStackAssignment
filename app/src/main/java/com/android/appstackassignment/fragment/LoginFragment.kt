package com.android.appstackassignment.fragment

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.appstackassignment.R
import com.android.appstackassignment.databinding.FragmentLoginBinding
import com.android.appstackassignment.utils.showToast
import com.android.appstackassignment.viewModel.LoginViewModel
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(){

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    lateinit var callbackManager: CallbackManager
    private val EMAIL = "email"
    var TAG="Facebook"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)

        btnLogin.setOnClickListener {
            if(loginViewModel.validated(it)) {
                findNavController().navigate(R.id.action_loginFragment_to_galleryFragment)
            }
        }

        btnFacebook.setOnClickListener {
           // btnFacebook.setReadPermissions(listOf(EMAIL))

           var hasPackage = isPackageInstalled(view.context, "com.facebook.katana")

            if(hasPackage) {
                FacebookSdk.sdkInitialize(view.context)
                AppEventsLogger.activateApp(view.context, view.context.getString(R.string.facebook_app_id))
                FacebookSdk.setIsDebugEnabled(true)
                FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS)


                var accessToken = AccessToken.getCurrentAccessToken()
                if (accessToken != null && !accessToken.isExpired) {
                    LoginManager.getInstance().logOut()
                    btnFacebook.setReadPermissions(listOf(EMAIL))
                    callbackManager = CallbackManager.Factory.create()
                    btnFacebook.setFragment(this)
                }


                btnFacebook.registerCallback(
                    callbackManager,
                    object : FacebookCallback<LoginResult?> {
                        override fun onSuccess(loginResult: LoginResult?) {
                            Log.d(TAG, "Facebook token: " + loginResult!!.accessToken.token)
                            findNavController().navigate(R.id.action_loginFragment_to_galleryFragment)
                            showToast(it.context, "You have logged in successfully")
                        }

                        override fun onCancel() {
                            Log.d(TAG, "onCancel ")
                        }

                        override fun onError(exception: FacebookException) {
                            exception.printStackTrace()
                            Log.d(TAG, "exception " + exception)

                        }

                    })
            }else{
                showToast(view.context,"Facebook is not installed on your device!")
            }

        }


        txtForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }

        txtSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_RegisterFragment)

        }
    }

    private fun initView(view: View) {
        loginViewModel = LoginViewModel()
        binding.viewModel = loginViewModel
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun isPackageInstalled(c: Context, targetPackage: String?): Boolean {
        val pm: PackageManager = c.getPackageManager()
        try {
            val info = pm.getPackageInfo(targetPackage!!, PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
        return true
    }
}