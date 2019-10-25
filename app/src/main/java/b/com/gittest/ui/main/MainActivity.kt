package b.com.gittest.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
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
    private var fragmentType = FragmentType.API

    private val onClickChangeType = View.OnClickListener {
        fragmentType = when (it) {
            local_button -> {
                FragmentType.LOCAL
            }
            else -> {
                FragmentType.API
            }
        }
        setButton()
        setFragment()
    }

    private fun setButton() {
        api_button.isEnabled = fragmentType != FragmentType.API
        local_button.isEnabled = fragmentType != FragmentType.LOCAL
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        api_button.setOnClickListener(onClickChangeType)
        local_button.setOnClickListener(onClickChangeType)

        main_search_button.setOnClickListener {
            main_auto_complete_text_view.text.toString().run {
                if (isNullOrEmpty() && fragmentType == FragmentType.API) {
                    toast(R.string.search_text_input_please)
                } else {
                    if (fragmentType == FragmentType.API) {
                        apiPresenter.search(this, false)
                    } else {
                        localPresenter.findLikeUser(this)
                    }
                }
            }
        }
        setButton()

        setFragment()
    }

    private fun setFragment() {
        var addStack = false
        val fragment: Fragment = supportFragmentManager.findFragmentByTag(fragmentType.tag) ?: run {
            addStack = true
            when (fragmentType) {
                FragmentType.API -> {
                    ApiFragment().apply {
                        apiPresenter = ApiPresenter(this, UserRepository.getInstance(RemoteUserDataSource))
                    }
                }
                FragmentType.LOCAL -> {
                    LocalFragment().apply {
                        localPresenter = LocalPresenter(this, UserRepository.getInstance(RemoteUserDataSource))
                    }
                }
            } as Fragment
        }
        replaceFragment(fragment_frame_layout.id, fragment, fragmentType.tag, addStack)
    }

    override fun onBackPressed() {
        finish()
    }

    enum class FragmentType(var tag: String) {
        API(ApiFragment.API_FRAGMENT_TAG), LOCAL(LocalFragment.LOCAL_FRAGMENT_TAG)
    }
}
