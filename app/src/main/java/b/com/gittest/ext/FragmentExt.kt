package b.com.gittest.ext

import android.support.v4.app.Fragment
import android.widget.Toast

fun Fragment.toast(msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

fun Fragment.toast(resId: Int) {
    toast(getString(resId))
}