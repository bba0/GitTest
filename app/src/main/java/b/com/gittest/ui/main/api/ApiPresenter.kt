package b.com.gittest.ui.main.api

import android.util.Log
import b.com.gittest.data.model.User
import b.com.gittest.data.source.user.UserDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class ApiPresenter(private val view: ApiContract.View, private var userRepository: UserDataSource) : ApiContract.Presenter {
    init {
        view.presenter = this
    }
    private var page = 1
    private var query: String = ""
    private var incompleteResult = true
    private val searchResult = ArrayList<User>()

    override fun search(query: String, isMore: Boolean) {
        if (isMore) {
            page++
        } else {
            searchResult.clear()
            view.removeAllData()
        }
        this.query = query
        userRepository.getUsers(query, page)
            .map {
                incompleteResult = it.incompleteResult ?: true
                it.items ?: ArrayList()
            }
            .map {
                searchResult.addAll(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.addUserData(it)
        }, {
            Log.e("lol", "error", it)
        })
    }

    override fun setLike(id: Int) {
        searchResult.asSequence()
            .find {
                it.userId == id
            }?.run {
                if (isLike) {
                    userRepository.unLikeUser(id)
                } else {
                    userRepository.likeUser(id)
                }
                view.updateUserData(this)
            }
    }


    override fun resume() {
        view.update()
    }

    override fun pause() {
        Log.e("lol", "")
    }

}