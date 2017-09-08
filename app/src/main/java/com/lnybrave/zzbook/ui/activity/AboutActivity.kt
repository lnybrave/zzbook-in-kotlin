package com.lnybrave.zzbook.ui.activity

import android.widget.ImageView
import android.widget.TextView
import com.lnybrave.zzbook.BuildConfig
import com.lnybrave.zzbook.R
import me.drakeet.multitype.Items
import me.drakeet.support.about.*


class AboutActivity : AbsAboutActivity() {

    override fun onCreateHeader(icon: ImageView, slogan: TextView, version: TextView) {
        icon.setImageResource(R.mipmap.ic_launcher)
        slogan.text = "About Page By drakeet"
        version.text = "v" + BuildConfig.VERSION_NAME
    }

    override fun onItemsCreated(items: Items) {
        items.add(Category("介绍与帮助"))
        items.add(Card(getString(R.string.card_content), "分享"))
        items.add(Line())

        items.add(Category("Developers"))
        items.add(Contributor(R.drawable.avatar_drakeet, "drakeet", "Developer & designer", "http://weibo.com/drak11t"))
        items.add(Contributor(R.drawable.avatar_drakeet, "黑猫酱", "Developer", "https://drakeet.me"))
        items.add(Contributor(R.drawable.avatar_drakeet, "小艾大人", "Developer"))
        items.add(Line())

        items.add(Category("Open Source Licenses"))
        items.add(License("MultiType", "drakeet", License.APACHE_2, "https://github.com/drakeet/MultiType"))
        items.add(License("about-page", "drakeet", License.APACHE_2, "https://github.com/drakeet/about-page"))
    }

}
