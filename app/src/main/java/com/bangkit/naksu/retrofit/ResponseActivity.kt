package com.bangkit.naksu.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.naksu.R
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)

        getDataById("2A1BA")
        getAllData()
    }

    private fun getDataById(id: String) {
        val gson = Gson()
        val client = ApiConfig.getApiService().getDataById(id)
        client.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val data = apiResponse?.data

                    if (data is Map<*, *>) {
                        val foto = data["foto"] as String
                    }

                    Log.i(TAG, "onResponse: ${apiResponse?.message}")
                } else {
                    val errorBody = response.errorBody()?.string()
                    if (errorBody != null) {
                        try {
                            val message: ApiResponse =
                                gson.fromJson(errorBody, ApiResponse::class.java)
                            Toast.makeText(
                                this@ResponseActivity,
                                "Failed: ${message.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (e: JsonSyntaxException) {
                            Toast.makeText(
                                this@ResponseActivity, "Failed: $errorBody", Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e(TAG, "OnFailure : ${t.message}")
                Toast.makeText(
                    this@ResponseActivity, "Failed: ${t.message}", Toast.LENGTH_LONG
                ).show()

            }
        })
    }

    private fun getAllData() {
        val gson = Gson()
        val client = ApiConfig.getApiService().getAllData()
        client.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val dataList = apiResponse?.data

                    if (dataList is List<*>) {
                        val data = dataList.filterIsInstance<Map<*, *>>()
                        for (datum in data) {
                            val foto =
                                datum["data"]?.let { it as? Map<*, *> }?.get("foto") as? String
                            foto?.let {
                                Log.d(TAG, "Foto: $foto")
                            }
                        }
                    }
                    Log.i(TAG, "onResponse: ${response.message()}")
                } else {
                    val errorBody = response.errorBody()?.string()
                    if (errorBody != null) {
                        try {
                            val message: ApiResponse =
                                gson.fromJson(errorBody, ApiResponse::class.java)
                            Toast.makeText(
                                this@ResponseActivity,
                                "Failed: ${message.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (e: JsonSyntaxException) {
                            Toast.makeText(
                                this@ResponseActivity,
                                "Failed: $errorBody",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e(TAG, "OnFailure : ${t.message}")
                Toast.makeText(
                    this@ResponseActivity, "Failed: ${t.message}", Toast.LENGTH_LONG
                ).show()

            }
        })
    }

    companion object {
        private val TAG = ResponseActivity::class.java.simpleName
    }
}