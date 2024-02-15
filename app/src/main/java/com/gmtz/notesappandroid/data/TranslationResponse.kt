package com.gmtz.notesappandroid.data

import com.google.gson.annotations.SerializedName

data class TranslationResponse(
    @SerializedName("contents")
    val contents: Contents
)

data class Contents(
    @SerializedName("translated")
    val translatedText: String
)

