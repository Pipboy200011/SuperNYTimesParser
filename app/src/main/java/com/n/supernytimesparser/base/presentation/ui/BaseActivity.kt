package com.n.supernytimesparser.base.presentation.ui

import android.content.Context
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

/**
 * @author Pavel Apanovskii on 23/03/2021.
 */
open class BaseActivity : AppCompatActivity() {

    fun hideKeyboard() {
        val activity: FragmentActivity = this
        val imm: InputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Find the currently focused view, so we can grab the correct window token from it.
        val view: View? = activity.currentFocus
        // If no view currently has focus, create a new one, just so we can grab a window token from it
        var binder: IBinder? = null
        if (view != null) {
            binder = view.windowToken
        } else if (activity.window != null) {
            binder = activity.window.decorView.windowToken
        }
        if (binder == null) {
            return
        }
        try {
            imm.hideSoftInputFromWindow(binder, 0)
        } catch (ignored: Exception) {
            //do nothing
        }
    }
}