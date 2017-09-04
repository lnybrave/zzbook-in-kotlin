package com.lnybrave.zzbook

import android.app.Application
import com.lnybrave.zzbook.di.component.AppComponent
import com.lnybrave.zzbook.di.component.DaggerAppComponent
import com.lnybrave.zzbook.di.module.ApiModule
import com.lnybrave.zzbook.di.module.AppModule
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import javax.inject.Inject


class App : Application() {

    init {
        instance = this
    }

    @Inject lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        SmartRefreshLayout.setDefaultRefreshHeaderCreater(DefaultRefreshHeaderCreater { context, layout ->
            layout?.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)
            ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate)
        })

        SmartRefreshLayout.setDefaultRefreshFooterCreater(DefaultRefreshFooterCreater { context, layout ->
            ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate)
        })

        DaggerAppComponent.builder().appModule(AppModule(this)).apiModule(ApiModule()).build().inject(this)
    }

    companion object {
        lateinit var instance: App
    }
}