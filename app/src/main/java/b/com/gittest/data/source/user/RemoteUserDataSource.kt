package b.com.gittest.data.source.user

import b.com.gittest.data.model.GitUserApiModel
import b.com.gittest.data.model.User
import b.com.gittest.network.GitApi
import io.reactivex.Single

object RemoteUserDataSource : UserDataSource {
    override fun getUsers(query: String, page: Int): Single<GitUserApiModel> {
        return GitApi.searchUser(query, page)
    }

    override fun getAllLikeUsers(): Single<List<User>> {
        //서버에서 제공하는 경우 연결해서 사용
        return Single.just(ArrayList())
    }

    override fun findLikeUsers(query: String): Single<List<User>> {
        //서버에서 제공하는 경우 연결해서 사용
        return Single.just(ArrayList())
    }

    override fun unLikeUser(id: Int) {
        //서버에서 제공하는 경우 연결해서 사용
    }

    override fun likeUser(id: Int) {
        //서버에서 제공하는 경우 연결해서 사용
    }

}