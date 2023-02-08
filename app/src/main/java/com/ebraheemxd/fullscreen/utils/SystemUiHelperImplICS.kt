package com.ebraheemxd.fullscreen.utils

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.view.View
import com.ebraheemxd.fullscreen.utils.SystemUiHelper.OnVisibilityChangeListener


@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
internal open class SystemUiHelperImplICS(
    activity: Activity?, level: Int, flags: Int,
    onVisibilityChangeListener: OnVisibilityChangeListener?
) : SystemUiHelperImplHC(activity!!, level, flags, onVisibilityChangeListener) {
    override fun createShowFlags(): Int {
        return View.SYSTEM_UI_FLAG_VISIBLE
    }

    override fun createTestFlags(): Int {
        return if (mLevel >= SystemUiHelper.LEVEL_LEAN_BACK) {
            // Intentionally override test flags.
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        } else View.SYSTEM_UI_FLAG_LOW_PROFILE
    }

    override fun createHideFlags(): Int {
        var flag = View.SYSTEM_UI_FLAG_LOW_PROFILE
        if (mLevel >= SystemUiHelper.LEVEL_LEAN_BACK) {
            flag = flag or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
        return flag
    }
}