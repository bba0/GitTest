package b.com.gittest.ui.main.api

import b.com.gittest.data.model.User
import b.com.gittest.ui.base.BaseContract

interface ApiContract {
    interface View: BaseContract.BaseView {
        fun removeAllData()
        fun addUserData(userList: List<User>)
    }

    interface Presenter: BaseContract.BasePresenter {
        fun search(query: String, isMore: Boolean)
    }
}