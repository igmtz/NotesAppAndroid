package com.gmtz.notesappandroid.data.ktor

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val body: String,
    val title: String,
    val userId: Int,
    val id: Int
)
