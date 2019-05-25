package b.com.gittest.data.source.user

import b.com.gittest.data.model.GitUserApiModel
import b.com.gittest.data.model.User
import io.reactivex.Single

class UserRepository private constructor(private var remoteUserDataSource: UserDataSource) : UserDataSource {
    val cacheMap = HashMap<Int, User>()

    override fun likeUser(id: Int) {

    }

    override fun getUsers(query: String, page: Int): Single<GitUserApiModel> {
        return remoteUserDataSource.getUsers(query, page)
    }

    override fun getAllLikeUsers(): Single<List<User>> {
        return Single.just(ArrayList<User>())
    }

    override fun findLikeUsers(query: String): Single<List<User>> {
        return Single.just(ArrayList<User>())
    }

    override fun unLikeUser(id: Int) {

    }

    companion object {
        private var INSTANCE: UserRepository? = null
        @JvmStatic
        fun getInstance(remoteUserDataSource: UserDataSource): UserRepository {
            if (INSTANCE == null) {
                synchronized(UserRepository::class.java) {
                    INSTANCE = UserRepository(remoteUserDataSource)
                }
            }
            return INSTANCE!!
        }
    }
}