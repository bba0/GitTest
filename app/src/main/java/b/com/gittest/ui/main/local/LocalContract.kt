package b.com.gittest.ui.main.local

import b.com.gittest.data.model.User
import b.com.gittest.ui.base.BaseContract

interface LocalContract {
    interface Presenter : BaseContract.BasePresenter {
        fun findLikeUser(query: String)
    }

    interface View : BaseContract.BaseView<Presenter> {
        fun addLikeUserData(userList: List<User>)
        fun updateUserData(user: User)
        fun removeData(id: Int)
    }
}