package b.com.gittest.ui.main.local

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import b.com.gittest.R

class LocalFragment : Fragment() {
    companion object {
        const val LOCAL_FRAGMENT_TAG = "local_fragment_tag"
    }

    var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {
        rootView = rootView ?: inflater.inflate(R.layout.fragment_local, container, false)
        return rootView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}