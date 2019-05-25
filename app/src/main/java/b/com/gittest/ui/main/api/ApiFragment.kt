package b.com.gittest.ui.main.api

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import b.com.gittest.R
import b.com.gittest.data.model.User
import b.com.gittest.ui.main.UserAdapter
import kotlinx.android.synthetic.main.fragment_api.view.*

class ApiFragment : Fragment(), ApiContract.View {
    override fun updateUserData(user: User) {
        userAdapter.updateData(user)
    }

    override lateinit var presenter: ApiContract.Presenter
    private val userAdapter: UserAdapter by lazy {
        UserAdapter {
            presenter.setLike(it)
        }
    }
    private val userLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(context)
    }
    override fun addUserData(userList: List<User>) {
        userAdapter.addUserData(userList)
    }

    override fun removeAllData() {
        userAdapter.clearData()
    }

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
                }
            }
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}