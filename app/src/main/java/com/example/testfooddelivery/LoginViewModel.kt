package com.example.testfooddelivery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val token = MutableLiveData<String>()
    val userLastName = MutableLiveData<String>()
    val userFirstName = MutableLiveData<String>()
    val userImg = MutableLiveData<String>()
}