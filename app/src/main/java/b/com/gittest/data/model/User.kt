package b.com.gittest.data.model

import com.google.gson.annotations.SerializedName

data class User (@SerializedName("id")var id: Int?) {
    @SerializedName("avatar_url")
    var avatarUrl: String? = null
    @SerializedName("login")
    private var login: String? = null

    var name = ""
    get() {
        return login ?: "이름 없음"
    }
    var isLike = false
}