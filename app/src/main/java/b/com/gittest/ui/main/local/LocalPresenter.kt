package b.com.gittest.ui.main.local

import b.com.gittest.data.source.user.UserDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LocalPresenter(private val view: LocalContract.View, private var userRepository: UserDataSource) : LocalContract.Presenter {
    init {
        view.presenter = this
    }
    var query = ""
    private var mCompositeDisposable = CompositeDisposable()
    override fun resume() {
        findLikeUser(query)
    }

    override fun pause() {
        mCompositeDisposable.clear()
    }

    override fun setLike(id: Int) {
        userRepository.unLikeUser(id)
        view.removeData(id)
    }

    override fun findLikeUser(query: String) {
        this.query = query
        view.removeAllData()
        mCompositeDisposable.add(userRepository.getAllLikeUsers()
            .toObservable()
            .flatMap {
                Observable.fromIterable(it)
            }
            .filter {
                if (query.isNullOrEmpty()) {
                    return@filter true
                } else {
                    return@filter it.name.contains(query, ignoreCase = true)
                }
            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.addLikeUserData(it)
        }, {

        }))
    }

}