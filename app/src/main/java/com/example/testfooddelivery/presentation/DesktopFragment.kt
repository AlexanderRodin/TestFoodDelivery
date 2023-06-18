package com.example.testfooddelivery.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testfooddelivery.R
import com.example.testfooddelivery.databinding.FragmentDesktopBinding

class DesktopFragment : Fragment() {

    private var _binding: FragmentDesktopBinding? = null
    private val binding: FragmentDesktopBinding
        get() = _binding ?: throw RuntimeException("FragmentDesktopBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDesktopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFragment(MenuFragment())
        binding.btnNavVew.setOnItemSelectedListener { getFragment(it.itemId) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun getFragment(id: Int): Boolean {
        when (id) {
            R.id.menu_home -> {
                showFragment(MenuFragment())
            }

            R.id.menu_basket -> {
                showFragment(BasketFragment())
            }

            R.id.menu_account -> {
                showFragment(ProfileFragment())
            }
        }
        return true
    }

    private fun showFragment(fragment: Fragment) {
        fragmentManager
            ?.beginTransaction()
            ?.setReorderingAllowed(true)
            ?.replace(R.id.container_fragment, fragment)
            ?.commit()
    }

}