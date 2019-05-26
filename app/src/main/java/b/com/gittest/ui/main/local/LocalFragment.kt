package b.com.gittest.ui.main.local

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import b.com.gittest.R
import b.com.gittest.data.model.User
import b.com.gittest.ui.main.UserAdapter
import kotlinx.android.synthetic.main.fragment_local.view.*

class LocalFragment : Fragment(), LocalContract.View {
    private val userAdapter: UserAdapter by lazy {
        UserAdapter {
            presenter.setLike(it)
        }
    }
    private val userLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(context)
    }

    override lateinit var presenter: LocalContract.Presenter

    companion object {
        const val LOCAL_FRAGMENT_TAG = "local_fragment_tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {
        rootView = rootView ?: run {
            inflater.inflate(R.layout.fragment_local, container, false).apply {
                local_recycler_view.run {
                    adapter = userAdapter
                    layoutManager = userLinearLayoutManager
                }
            }
        }
        return rootView
    }

    override fun update() {
        userAdapter.update()
    }

    override fun removeAllData() {
        userAdapter.clearData()
    }

    override fun addLikeUserData(userList: List<User>) {
        userAdapter.addUserData(userList)
    }

    override fun updateUserData(user: User) {
        userAdapter.updateData(user)
    }

    override fun removeData(id: Int) {
        userAdapter.removeUser(id)
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

}