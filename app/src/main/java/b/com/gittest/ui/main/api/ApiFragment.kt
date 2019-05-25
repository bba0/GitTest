package b.com.gittest.ui.main.api

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import b.com.gittest.R
import b.com.gittest.data.model.User

class ApiFragment : Fragment(), ApiContract.View {
    lateinit var presenter: ApiContract.Presenter

    override fun addUserData(userList: List<User>) {
    }

    override fun removeAllData() {
    }

    companion object {
        const val API_FRAGMENT_TAG = "api_fragment_tag"
    }

    var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = rootView ?: inflater.inflate(R.layout.fragment_api, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}