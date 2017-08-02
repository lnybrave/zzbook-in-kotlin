package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayout
import android.support.v7.widget.GridLayoutManager
import android.view.*
import com.lnybrave.zzbook.Constants.TOPIC_SIMPLE
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.Book
import com.lnybrave.zzbook.bean.Recommendation
import com.lnybrave.zzbook.bean.Subject
import com.lnybrave.zzbook.bean.Topic
import com.lnybrave.zzbook.databinding.FragmentRecommendationBinding
import com.lnybrave.zzbook.di.component.RecommendationModule
import com.lnybrave.zzbook.mvp.contract.RecommendationContract
import com.lnybrave.zzbook.mvp.presenter.RecommendationPresenter
import com.lnybrave.zzbook.toast
import com.lnybrave.zzbook.ui.activity.MainActivity
import com.lnybrave.zzbook.ui.multitype.BookComplexViewBinder
import com.lnybrave.zzbook.ui.multitype.BookSimpleViewBinder
import com.lnybrave.zzbook.ui.multitype.TopicTitleViewBinder
import com.lnybrave.zzbook.ui.widget.GlideImageLoader
import com.lnybrave.zzbook.utils.loadBookCover
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_recommendation.*
import kotlinx.android.synthetic.main.item_subject.view.*
import me.drakeet.multitype.MultiTypeAdapter
import javax.inject.Inject


class RecommendationFragment : BaseBindingFragment<FragmentRecommendationBinding>(), RecommendationContract.View {

    private var images = ArrayList<Any>()

    private var mList = ArrayList<Any>()
    private lateinit var mAdapter: MultiTypeAdapter
    @Inject lateinit var mPresenter: RecommendationPresenter


    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): FragmentRecommendationBinding {
        return FragmentRecommendationBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        initTitle()

        mAdapter = MultiTypeAdapter(mList)

        with(mBinding) {
            banner.setImageLoader(GlideImageLoader())
            banner.isAutoPlay(true)
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            banner.setIndicatorGravity(BannerConfig.RIGHT)
            banner.setImages(images)

            recyclerView.adapter = mAdapter
            val layoutManager = GridLayoutManager(context, 3)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val obj = mList[position]
                    if (obj is Book) {
                        return if (obj.showType == 0) 1 else 3
                    }
                    return 3
                }
            }
            recyclerView.layoutManager = layoutManager

            mAdapter.register(Topic::class.java, TopicTitleViewBinder())
            mAdapter.register(Book::class.java)
                    .to(BookSimpleViewBinder(), BookComplexViewBinder())
                    .withLinker { book -> book.showType }
        }
    }

    private fun initTitle() {
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is MainActivity) {
            val a: MainActivity = activity as MainActivity
            a.mainComponent.plus(RecommendationModule(this)).inject(this)
            mPresenter.getData()
        } else {
            throw IllegalArgumentException("is not MainActivity")
        }
    }

    override fun setData(results: Recommendation) {
        var titles = ArrayList<String>()
        results.banners.mapTo(titles) { it.name }
        banner.update(results.banners, titles)

        addSubjectViews(results.subjects)

        mList.clear()
        for (topic in results.topics) {
            mList.add(topic)
            for (book in topic.books) {
                book.showType = if (topic.type == TOPIC_SIMPLE) 0 else 1
            }
            mList.addAll(topic.books)
        }
        if (results.books != null) {
            mList.addAll(results.books!!)
        }
        mAdapter.notifyDataSetChanged()
    }

    fun addSubjectViews(subjects: List<Subject>) {
        gridLayout.removeAllViews()
        for ((index, subject) in subjects.withIndex()) {
            val functionView: View = LayoutInflater.from(context).inflate(R.layout.item_subject, gridLayout, false)
            functionView.tvName.text = subject.name
            loadBookCover(subject.icon, functionView.ivIcon)

            val rowSpec: GridLayout.Spec = GridLayout.spec(index / 5, 1f)
            val columnSpec: GridLayout.Spec = GridLayout.spec(index % 5, 1f)
            val layoutParams: GridLayout.LayoutParams = GridLayout.LayoutParams(rowSpec, columnSpec)
            gridLayout.addView(functionView, layoutParams)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscribe()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_search -> {
                toast("search")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun newInstance(): RecommendationFragment {
            val fragment = RecommendationFragment()
            return fragment
        }
    }
}
