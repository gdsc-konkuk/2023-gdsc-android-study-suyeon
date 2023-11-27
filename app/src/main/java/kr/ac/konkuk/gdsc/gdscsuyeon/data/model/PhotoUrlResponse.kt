package kr.ac.konkuk.gdsc.gdscsuyeon.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoUrlResponse(
    @SerialName("urls")
    val urls: PhotoUrl,
)

@Serializable
data class PhotoUrl(
    @SerialName("thumb")
    val thumb: String,
)
