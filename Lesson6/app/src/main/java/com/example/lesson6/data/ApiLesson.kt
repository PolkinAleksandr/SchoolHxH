package com.example.lesson6.data

import com.example.lesson6.data.requestmodel.RequestLogin
import com.example.lesson6.data.responsemodel.BaseResponse
import com.example.lesson6.data.responsemodel.ResponseLogin
import retrofit2.http.Body
import retrofit2.http.PUT

interface ApiLesson {

    @PUT("user/signin")
    suspend fun login(
        @Body requestLogin: RequestLogin,
    ): BaseResponse<ResponseLogin>
}