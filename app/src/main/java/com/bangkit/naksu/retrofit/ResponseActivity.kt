package com.bangkit.naksu.retrofit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.naksu.R
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ResponseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)

        getDataById("2A1BA")
        getAllData()
        openGallery()
    }

    private val image =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri: Uri? = result.data?.data
                if (uri != null) {
                    uploadImage(uri)
                }
            }
        }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        image.launch(intent)
    }

    private fun uploadImage(uri: Uri) {
        val file = uri.path?.let { File(it) }
        val requestFile = file?.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imagePart = requestFile?.let {
            MultipartBody.Part.createFormData(
                "image", file.name,
                it
            )
        }

        imagePart?.let { ApiConfig.getApiService().uploadImage(it) }
            ?.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val apiResponse = response.body()
                        Log.i(TAG, "uploadImage onResponse: ${apiResponse?.message}")
                    } else {
                        val apiResponse = response.body()
                        Log.i(TAG, "uploadImage onResponse: ${apiResponse?.message}")
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.e(TAG, "uploadImage OnFailure : ${t.message}")
                }
            })
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
                            Log.i(TAG, "onResponse: ${message.message}")
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
                            Log.i(TAG, "onResponse: ${message.message}")
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

            }
        })
    }

    companion object {
        private val TAG = ResponseActivity::class.java.simpleName
    }
}