package b.com.gittest.data.model

import com.google.gson.annotations.SerializedName

data class User (@SerializedName("id") private var _id: Int?) {
    @SerializedName("avatar_url")
    var avatarUrl: String? = null
    @SerializedName("login")
    private var login: String? = null

    var name: String = ""
    get() {
        return login ?: "이름 없음"
    }
    var userId: Int = -1
    get() {
        return _id ?: -1
    }
    var isLike = false
}