package com.ebraheemxd.fullscreen.utils

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.view.View
import com.ebraheemxd.fullscreen.utils.SystemUiHelper.OnVisibilityChangeListener


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
internal open class SystemUiHelperImplJB(
    activity: Activity?, level: Int, flags: Int,
    onVisibilityChangeListener: OnVisibilityChangeListener?
) : SystemUiHelperImplICS(activity, level, flags, onVisibilityChangeListener) {
    override fun createShowFlags(): Int {
        var flag = super.createShowFlags()
        if (mLevel >= SystemUiHelper.LEVEL_HIDE_STATUS_BAR) {
            flag =
                flag or (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            if (mLevel >= SystemUiHelper.LEVEL_LEAN_BACK) {
                flag = flag or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            }
        }
        return flag
    }

    override fun createHideFlags(): Int {
        var flag = super.createHideFlags()
        if (mLevel >= SystemUiHelper.LEVEL_HIDE_STATUS_BAR) {
            flag = flag or (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
            if (mLevel >= SystemUiHelper.LEVEL_LEAN_BACK) {
                flag = flag or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            }
        }
        return flag
    }

    override fun onSystemUiShown() {
        if (mLevel == SystemUiHelper.LEVEL_LOW_PROFILE) {
            // Manually show the action bar when in low profile mode.
            val ab = mActivity.actionBar
            ab?.show()
        }
        setIsShowing(true)
    }

    override fun onSystemUiHidden() {
        if (mLevel == SystemUiHelper.LEVEL_LOW_PROFILE) {
            // Manually hide the action bar when in low profile mode.
            val ab = mActivity.actionBar
            ab?.hide()
        }
        setIsShowing(false)
    }
}