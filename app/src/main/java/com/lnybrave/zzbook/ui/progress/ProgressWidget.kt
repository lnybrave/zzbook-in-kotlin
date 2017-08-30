package com.lnybrave.zzbook.ui.progress

import com.vlonjatg.progressactivity.ProgressFrameLayout
import com.vlonjatg.progressactivity.ProgressLinearLayout
import com.vlonjatg.progressactivity.ProgressRelativeLayout

internal object ProgressWidget {

    fun wrapper(progressActivity: ProgressFrameLayout): ProgressWidgetWrapper {
        return ProgressFrameLayoutWrapper(progressActivity)
    }

    fun wrapper(progressActivity: ProgressLinearLayout): ProgressWidgetWrapper {
        return ProgressLinearLayoutWrapper(progressActivity)
    }

    fun wrapper(progressActivity: ProgressRelativeLayout): ProgressWidgetWrapper {
        return ProgressRelativeLayoutWrapper(progressActivity)
    }

}
