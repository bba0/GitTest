package b.com.gittest.ui.main.local

import b.com.gittest.ui.base.BaseContract

interface LocalContract {
    interface Presenter : BaseContract.BasePresenter {

    }

    interface LocalView : BaseContract.BaseView<Presenter> {

    }
}