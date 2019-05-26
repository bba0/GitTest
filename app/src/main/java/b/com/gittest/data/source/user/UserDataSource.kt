package b.com.gittest.data.source.user

import b.com.gittest.data.model.GitUserApiModel
import b.com.gittest.data.model.User
import io.reactivex.Single

interface UserDataSource {
    fun getUsers(query: String, page: Int): Single<GitUserApiModel>
    fun getAllLikeUsers(): Single<List<User>>
    fun unLikeUser(id: Int)
    fun likeUser(id: Int)
}