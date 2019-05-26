package b.com.gittest.data.source.user

import b.com.gittest.data.model.GitUserApiModel
import b.com.gittest.data.model.User
import io.reactivex.Observable
import io.reactivex.Single

class UserRepository private constructor(private var remoteUserDataSource: UserDataSource) : UserDataSource {
    val userCacheMap = HashMap<Int, User>()
    override fun likeUser(id: Int) {
        userCacheMap[id]?.run {
            isLike = true
        }
    }

    override fun getUsers(query: String, page: Int): Single<GitUserApiModel> {
        return remoteUserDataSource.getUsers(query, page).map { gitUserApiModel ->
            gitUserApiModel.items?.forEach { user ->
                userCacheMap[user.userId]?.run {
                    user.isLike = isLike
                }
                userCacheMap[user.userId] = user
            }
            gitUserApiModel
        }
    }

    override fun getAllLikeUsers(): Single<List<User>> {
        return Single.just(userCacheMap.values)
            .toObservable()
            .flatMap {
                Observable.fromIterable(it)
            }
            .filter {
                it.isLike
            }
            .toList()
            .map {
                ArrayList(it)
            }
    }

    override fun unLikeUser(id: Int) {
        userCacheMap[id]?.run {
            isLike = false
        }
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