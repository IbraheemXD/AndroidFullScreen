package com.ebraheemxd.fullscreen.utils

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.View.OnSystemUiVisibilityChangeListener
import android.view.WindowManager
import com.ebraheemxd.fullscreen.utils.SystemUiHelper.OnVisibilityChangeListener
import com.ebraheemxd.fullscreen.utils.SystemUiHelper.SystemUiHelperImpl


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
internal open class SystemUiHelperImplHC(
    activity: Activity, level: Int, flags: Int,
    onVisibilityChangeListener: OnVisibilityChangeListener?
) : SystemUiHelperImpl(activity, level, flags, onVisibilityChangeListener),
    OnSystemUiVisibilityChangeListener {
    val mDecorView: View

    init {
        mDecorView = activity.window.decorView
        mDecorView.setOnSystemUiVisibilityChangeListener(this)
    }

    override fun show() {
        mDecorView.systemUiVisibility = createShowFlags()
    }

    override fun hide() {
        mDecorView.systemUiVisibility = createHideFlags()
    }

    override fun onSystemUiVisibilityChange(visibility: Int) {
        if (visibility and createTestFlags() != 0) {
            onSystemUiHidden()
        } else {
            onSystemUiShown()
        }
    }

    protected open fun onSystemUiShown() {
        val ab = mActivity.actionBar
        ab?.show()
        mActivity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setIsShowing(true)
    }

    protected open fun onSystemUiHidden() {
        val ab = mActivity.actionBar
        ab?.hide()
        mActivity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setIsShowing(false)
    }

    protected open fun createShowFlags(): Int {
        return View.STATUS_BAR_VISIBLE
    }

    protected open fun createHideFlags(): Int {
        return View.STATUS_BAR_HIDDEN
    }

    protected open fun createTestFlags(): Int {
        return View.STATUS_BAR_HIDDEN
    }
}