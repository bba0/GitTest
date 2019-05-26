package b.com.gittest.ui.main.api

import b.com.gittest.data.model.User
import b.com.gittest.ui.base.BaseContract

interface ApiContract {
    interface View: BaseContract.BaseView<Presenter> {
        fun addUserData(userList: List<User>)
        fun updateUserData(user: User)
    }

    interface Presenter: BaseContract.BasePresenter {
        fun search(query: String, isMore: Boolean)
    }
}