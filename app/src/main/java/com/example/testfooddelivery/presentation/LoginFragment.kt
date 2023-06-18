package com.example.testfooddelivery.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.testfooddelivery.LoginViewModel
import com.example.testfooddelivery.R
import com.example.testfooddelivery.databinding.FragmentLoginBinding
import com.example.testfooddelivery.domain.AuthRequest
import com.example.testfooddelivery.domain.Repository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment : Fragment() {

    private lateinit var repository: Repository
    private val loginViewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()
        binding.apply {
            nextButton.setOnClickListener {
                showNextFragment()
            }
            signIn.setOnClickListener {
                auth(
                    AuthRequest(
                        etLogin.text.toString(),
                        etPassword.text.toString()
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showNextFragment() {
        fragmentManager
            ?.beginTransaction()
            ?.setReorderingAllowed(true)
            ?.replace(R.id.container_activity, DesktopFragment())
            ?.commit()
    }

    private fun initRetrofit() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        repository = retrofit.create(Repository::class.java)
    }

    private fun auth(authRequest: AuthRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.auth(authRequest)
            val message = response.errorBody()?.string()?.let {
                JSONObject(it).getString("message")
            }
            requireActivity().runOnUiThread {
                Log.d("TAG", "$message")
                val user = response.body()
                if (user != null) {
                    Picasso.get().load(user.image).into(binding.iv)
                    binding.tvName.text = user.firstName
                    binding.nextButton.visibility = View.VISIBLE
                    loginViewModel.token.value = user.token
                    loginViewModel.userFirstName.value = user.firstName
                    loginViewModel.userLastName.value = user.lastName
                    loginViewModel.userImg.value = user.image
                }
            }

        }
    }
}