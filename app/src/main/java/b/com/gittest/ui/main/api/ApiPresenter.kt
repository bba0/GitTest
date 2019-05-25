package b.com.gittest.ui.main.api

import android.util.Log
import b.com.gittest.data.source.user.UserDataSource

class ApiPresenter(private val view: ApiContract.View, private var userRepository: UserDataSource) : ApiContract.Presenter {
    private var page = 1
    private var query: String = ""
    override fun search(query: String, isMore: Boolean) {
        if (isMore) {
            page++
        }
        this.query = query
        userRepository.getUsers(query, page).subscribe({
            Log.e("lol", "$it")
        }, {
            Log.e("lol", "error", it)
        })
    }

    override fun resume() {

    }

    override fun pause() {

    }

}