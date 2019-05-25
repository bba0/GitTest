package b.com.gittest.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import b.com.gittest.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val onClickChangeType = View.OnClickListener {
        when(it) {
            api_button -> {
                api_button.isEnabled = false
                local_button.isEnabled = true
            }
            local_button -> {
                api_button.isEnabled = true
                local_button.isEnabled = false
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
