package fastcampus.aos.part2.part2_chapter4.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val username: String,
)
