package com.example.testfooddelivery.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.testfooddelivery.LoginViewModel
import com.example.testfooddelivery.databinding.FragmentProfileBinding
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showUser()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showUser() {
        binding.apply {
            loginViewModel.userFirstName.observe(viewLifecycleOwner) {
                tvFirstName.text = it
            }
            loginViewModel.userLastName.observe(viewLifecycleOwner) {
                tvLastName.text = it
            }
            loginViewModel.userImg.observe(viewLifecycleOwner) {
                Picasso.get().load(it).into(imgUser)
            }
        }
    }
}