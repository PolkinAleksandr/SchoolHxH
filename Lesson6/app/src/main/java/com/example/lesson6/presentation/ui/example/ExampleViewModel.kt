package com.example.lesson6.presentation.ui.example

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson6.data.repository.PreferenceStorage
import com.example.lesson6.data.responsemodel.ResponseLogin
import com.example.lesson6.data.responsemodel.ResponseStates
import com.example.lesson6.domain.usecase.LoginUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

class ExampleViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _exampleLiveData = MutableLiveData<ResponseStates<ResponseLogin>>()
    val exampleLiveData: LiveData<ResponseStates<ResponseLogin>> = _exampleLiveData

    fun login() {
        viewModelScope.launch {
            _exampleLiveData.value = ResponseStates.Loading()
            try {
                _exampleLiveData.value = ResponseStates.Success(
                    loginUseCase.execute("me@coldmail.org", "passWORD123")
                )
            } catch (e: Exception) {
                _exampleLiveData.value = ResponseStates.Failure(
                    e
                )
            }
        }
    }
}