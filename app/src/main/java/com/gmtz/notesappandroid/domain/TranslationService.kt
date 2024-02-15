package com.gmtz.notesappandroid.domain

import com.gmtz.notesappandroid.data.TranslationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslationService {
    @GET("morse.json")
    fun translateToMorse(@Query("text") text: String): Call<TranslationResponse>
}