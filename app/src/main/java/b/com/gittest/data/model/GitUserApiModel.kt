package b.com.gittest.data.model

import com.google.gson.annotations.SerializedName

class GitUserApiModel {
    @SerializedName("items")
    var items: List<User>? = ArrayList()
    @SerializedName("incomplete_results")
    var incompleteResult: Boolean? = true
}