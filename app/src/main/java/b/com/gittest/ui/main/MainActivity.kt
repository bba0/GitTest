package b.com.gittest.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import b.com.gittest.R
import b.com.gittest.data.source.user.RemoteUserDataSource
import b.com.gittest.data.source.user.UserRepository
import b.com.gittest.ext.replaceFragment
import b.com.gittest.ext.toast
import b.com.gittest.ui.main.api.ApiContract
import b.com.gittest.ui.main.api.ApiFragment
import b.com.gittest.ui.main.api.ApiPresenter
import b.com.gittest.ui.main.local.LocalContract
import b.com.gittest.ui.main.local.LocalFragment
import b.com.gittest.ui.main.local.LocalPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var apiPresenter: ApiContract.Presenter
    lateinit var localPresenter: LocalContract.Presenter
    private val onClickChangeType = View.OnClickListener {
        when(it) {
            api_button -> {
                api_button.isEnabled = false
                local_button.isEnabled = true
                setApiFragment()
            }
            local_button -> {
                api_button.isEnabled = true
                local_button.isEnabled = false
                setLocalFragment()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        api_button.setOnClickListener(onClickChangeType)
        local_button.setOnClickListener(onClickChangeType)

        main_search_button.setOnClickListener {
            main_auto_complete_text_view.text.toString().run {
                if (isNullOrEmpty()) {
                    toast(R.string.search_text_input_please)
                } else {
                    if (api_button.isEnabled) {
                        localPresenter.findLikeUser(this)
                    } else {
                        apiPresenter.search(this, false)
                    }
                }
            }
        }
        api_button.isEnabled = false
        setApiFragment()
    }

    private fun setApiFragment() {
        var addStack = false
        val fragment = supportFragmentManager.findFragmentByTag(ApiFragment.API_FRAGMENT_TAG) ?: addStack.run {
            addStack = true
            var fragment = ApiFragment()
            apiPresenter = ApiPresenter(fragment as ApiFragment, UserRepository.getInstance(RemoteUserDataSource))
            fragment
        }
        replaceFragment(fragment_frame_layout.id, fragment, ApiFragment.API_FRAGMENT_TAG, addStack)
    }
    private fun setLocalFragment() {
        var addStack = false
        val fragment = supportFragmentManager.findFragmentByTag(LocalFragment.LOCAL_FRAGMENT_TAG) ?: addStack.run {
            addStack = true
            var fragment = LocalFragment()
            localPresenter = LocalPresenter(fragment as LocalFragment, UserRepository.getInstance(RemoteUserDataSource))
            fragment
        }
        replaceFragment(fragment_frame_layout.id, fragment, LocalFragment.LOCAL_FRAGMENT_TAG, addStack)
    }
}
