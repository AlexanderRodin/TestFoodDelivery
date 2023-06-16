package com.example.testfooddelivery.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testfooddelivery.R
import com.example.testfooddelivery.databinding.FragmentDesktopBinding

class DesktopFragment : Fragment() {

    private lateinit var binding: FragmentDesktopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            showFragment(MenuFragment())
        }
        binding.btnNavVew.setOnItemSelectedListener { getFragment(it.itemId) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDesktopBinding.inflate(inflater, container, false)
        return binding.root
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
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .commit()
    }

}