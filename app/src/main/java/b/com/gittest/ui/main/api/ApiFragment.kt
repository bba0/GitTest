package b.com.gittest.ui.main.api

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import b.com.gittest.R
import b.com.gittest.data.model.User
import b.com.gittest.ui.main.UserAdapter
import kotlinx.android.synthetic.main.fragment_api.view.*

class ApiFragment : Fragment(), ApiContract.View {
    override lateinit var presenter: ApiContract.Presenter
    private val userAdapter: UserAdapter by lazy {
        UserAdapter {
            presenter.setLike(it)
        }
    }
    private val userLinearLayoutManager = LinearLayoutManager(activity)
    val reloadSize = 10

    companion object {
        const val API_FRAGMENT_TAG = "api_fragment_tag"
    }

    var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = rootView ?: run {
            inflater.inflate(R.layout.fragment_api, container, false).apply {
                api_recycler_view.run {
                    adapter = userAdapter
                    layoutManager = userLinearLayoutManager
                    addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            if (userLinearLayoutManager.findLastCompletelyVisibleItemPosition() == userAdapter.itemCount - reloadSize) {
                                presenter.moreData()
                            }
                        }
                    })
                }
            }
        }
        return rootView
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        presenter.pause()
    }

    override fun addUserData(userList: List<User>) {
        userAdapter.addUserData(userList)
    }

    override fun removeAllData() {
        userAdapter.clearData()
    }

    override fun update() {
        userAdapter.update()
    }

    override fun updateUserData(user: User) {
        userAdapter.updateData(user)
    }

}