package com.gmtz.notesappandroid.domain.usescases

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import com.gmtz.notesappandroid.domain.TranslationService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LockNote {
    operator fun invoke(textToTranslate: String) {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.funtranslations.com/translate/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val translationApiService = retrofit.create(TranslationService::class.java)

        val call = translationApiService.translateToMorse(textToTranslate)
        val response = call.execute()

        if (response.isSuccessful) {
            val translationResponse = response.body()
            val translatedText = translationResponse?.contents?.translatedText ?: "Error in translation"
            Log.i("Translated", translatedText)
        } else {
            Log.i("Lock", "Note not locked")
        }


    }
}