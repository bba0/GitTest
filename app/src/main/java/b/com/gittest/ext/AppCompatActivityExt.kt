package b.com.gittest.ext

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment, tag: String, addStack: Boolean) {
    supportFragmentManager.run {
        beginTransaction().run {
            replace(containerId, fragment, tag)
            if (addStack) {
                addToBackStack(tag)
            }
            commit()
        }
    }
}