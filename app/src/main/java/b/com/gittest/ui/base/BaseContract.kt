package b.com.gittest.ui.base

interface BaseContract {
    interface BasePresenter {
        fun resume()
        fun pause()
        fun setLike(id: Int)
    }
    interface BaseView<T> {
        var presenter: T
        fun update()
        fun removeAllData()
    }
}