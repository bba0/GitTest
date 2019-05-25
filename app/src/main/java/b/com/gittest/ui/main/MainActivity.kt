package b.com.gittest.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import b.com.gittest.R
import b.com.gittest.ext.replaceFragment
import b.com.gittest.ui.main.api.ApiFragment
import b.com.gittest.ui.main.local.LocalFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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
    }

    private fun setApiFragment() {
        var addStack = false
        val fragment = supportFragmentManager.findFragmentByTag(ApiFragment.API_FRAGMENT_TAG) ?: addStack.run {
            addStack = true
            ApiFragment()
        }
        replaceFragment(fragment_frame_layout.id, fragment, ApiFragment.API_FRAGMENT_TAG, addStack)
    }
    private fun setLocalFragment() {
        var addStack = false
        val fragment = supportFragmentManager.findFragmentByTag(LocalFragment.LOCAL_FRAGMENT_TAG) ?: addStack.run {
            addStack = true
            LocalFragment()
        }
        replaceFragment(fragment_frame_layout.id, fragment, LocalFragment.LOCAL_FRAGMENT_TAG, addStack)
    }
}
