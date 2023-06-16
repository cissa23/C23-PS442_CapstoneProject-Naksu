package com.bangkit.naksu.retrofit

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

class ApiConfig {
    companion object{
        fun getApiService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://capstone-c23-ps442.et.r.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}

interface ApiService {

    @GET("data")
    fun getAllData(
    ): Call<ApiResponse>

    @GET("show/{id}")
    fun getDataById(@Path("id") id: String): Call<ApiResponse>

    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part image: MultipartBody.Part
    ): Call<ApiResponse>
}

data class ApiResponse(
    @SerializedName("data")
    val data: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)