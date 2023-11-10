package com.example.lesson6.presentation.ui.example

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson6.data.repository.PreferenceStorage
import com.example.lesson6.data.responsemodel.ResponseLogin
import com.example.lesson6.domain.usecase.LoginUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

class ExampleViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _exampleLiveData = MutableLiveData<ResponseLogin>()
    val exampleLiveData: LiveData<ResponseLogin> = _exampleLiveData

    private val _errorLiveData = MutableLiveData<Exception>()
    val errorLiveData: LiveData<Exception> = _errorLiveData

    fun login() {
        viewModelScope.launch {
            try {
                _exampleLiveData.value = loginUseCase.execute("me@coldmail.org", "passWORD123?")
            } catch (e: Exception) {
                _errorLiveData.value = e
            }
        }
    }
}