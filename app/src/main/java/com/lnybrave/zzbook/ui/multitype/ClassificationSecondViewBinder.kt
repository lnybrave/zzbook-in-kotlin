package com.lnybrave.zzbook.ui.multitype

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Classification
import com.lnybrave.zzbook.ui.activity.ClassificationDetailActivity
import com.lnybrave.zzbook.utils.ImageUtils
import kotlinx.android.synthetic.main.item_classification_second.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by lny on 2017/7/29.
 */
class ClassificationSecondViewBinder : ItemViewBinder<Classification, ClassificationSecondViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(
            inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_classification_second, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, classification: Classification) {
        ImageUtils.displayIcon(classification.icon, holder.itemView.ivIcon)
        holder.itemView.tvName.text = classification.name
        holder.itemView.tvThirdList.text = classification.getChildrenList()
        holder.classification = classification
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var classification: Classification

        init {
            itemView.setOnClickListener {
                v: View?
                ->
                val intent = Intent(v?.context, ClassificationDetailActivity::class.java)
                intent.putExtra("classification", classification)
                v?.context?.startActivity(intent)
            }
        }
    }
}