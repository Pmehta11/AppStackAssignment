package com.android.appstackassignment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.appstackassignment.R
import com.android.appstackassignment.databinding.FragmentForgotBinding
import com.android.appstackassignment.viewModel.ForgotViewModel
import kotlinx.android.synthetic.main.fragment_forgot.*

class ForgotFragment : Fragment(){
    private lateinit var binding: FragmentForgotBinding
    private lateinit var forgotViewModel: ForgotViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)

        btnForgot.setOnClickListener {
            if(forgotViewModel.validated(it)) {
                findNavController().navigate(R.id.action_forgotFragment_to_loginFragment)
            }
        }

    }

    private fun initView(view: View) {
        forgotViewModel = ForgotViewModel()
        binding.viewModel = forgotViewModel
    }

}