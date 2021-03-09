package com.android.appstackassignment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.appstackassignment.R
import com.android.appstackassignment.databinding.FragmentRegisterBinding
import com.android.appstackassignment.viewModel.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(){

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)

        btnRegister.setOnClickListener {
            if(signUpViewModel.validated(it)) {
                findNavController().navigate(R.id.action_registerFragment_to_galleryFragment)
            }
        }

        txtLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

    }

    private fun initView(view: View) {
        signUpViewModel = SignUpViewModel()
        binding.viewModel = signUpViewModel
    }


}