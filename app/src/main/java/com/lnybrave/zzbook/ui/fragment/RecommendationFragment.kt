package com.lnybrave.zzbook.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayout
import android.support.v7.widget.GridLayoutManager
import android.view.*
import com.lnybrave.zzbook.Constants.TOPIC_SIMPLE
import com.lnybrave.zzbook.R
import com.lnybrave.zzbook.bean.*
import com.lnybrave.zzbook.databinding.FragmentRecommendationBinding
import com.lnybrave.zzbook.di.module.RecommendationModule
import com.lnybrave.zzbook.mvp.IPresenter
import com.lnybrave.zzbook.mvp.contract.RecommendationContract
import com.lnybrave.zzbook.mvp.presenter.RecommendationPresenter
import com.lnybrave.zzbook.ui.BaseBindingFragment
import com.lnybrave.zzbook.ui.activity.*
import com.lnybrave.zzbook.ui.multitype.BookComplexViewBinder
import com.lnybrave.zzbook.ui.multitype.BookSimpleViewBinder
import com.lnybrave.zzbook.ui.multitype.TopicTitleViewBinder
import com.lnybrave.zzbook.utils.BannerImageLoader
import com.lnybrave.zzbook.utils.ImageUtils
import com.malinskiy.materialicons.IconDrawable
import com.malinskiy.materialicons.Iconify
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

    private var offset: Int = 0


    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): FragmentRecommendationBinding {
        return FragmentRecommendationBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        initTitle()

        mAdapter = MultiTypeAdapter(mList)

        with(mBinding) {
            setupProgressWidget(progress)

            refreshLayout.setOnRefreshListener({ initData() })
            refreshLayout.setOnLoadmoreListener({ loadData() })

            banner.setImageLoader(BannerImageLoader())
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
            initData()
        } else {
            throw IllegalArgumentException("is not MainActivity")
        }
    }

    private fun initData() {
        mPresenter.initData()
    }

    private fun loadData() {
        mPresenter.getData(offset)
    }

    override fun onEmpty(presenter: IPresenter) {
        val emptyDrawable = IconDrawable(this.activity, Iconify.IconValue.zmdi_shopping_basket)
                .colorRes(android.R.color.white)

        showEmpty(emptyDrawable,
                "Empty Shopping Cart",
                "Please add things in the cart to continue.")
    }

    override fun onError(presenter: IPresenter, message: String?) {
        val errorDrawable = IconDrawable(this.activity, Iconify.IconValue.zmdi_wifi_off)
                .colorRes(android.R.color.white)

        showError(errorDrawable,
                "No Connection",
                "We could not establish a connection with our servers. Please try again when you are connected to the internet.",
                "重试",
                View.OnClickListener {
                    initData()
                })
    }

    override fun setBannerList(data: List<Banner>) {
        val titles = ArrayList<String>()
        data.mapTo(titles) { it.name }
        banner.update(data, titles)
    }

    override fun setMenuList(data: List<StackMenu>) {
        addSubjectViews(data)
    }

    override fun setData(page: APIPage<MixedBean>) {
        if (!page.hasPrev()) {
            mList.clear()
            offset = 0
        }
        refreshLayout.isEnableLoadmore = page.hasNext()

        addPage(page.results)
        offset += page.results.size

        mAdapter.notifyDataSetChanged()
    }

    private fun addPage(list: List<MixedBean>) {
        for ((book, topic) in list) {
            if (book != null) {
                mList.add(book)
            }
            if (topic != null) {
                if (topic.books != null) {
                    if (topic.books.isNotEmpty()) {
                        mList.add(topic)
                        for (b in topic.books) {
                            b.showType = if (topic.type == TOPIC_SIMPLE) 0 else 1
                        }
                        mList.addAll(topic.books)
                    }
                }
            }
        }
    }

    fun addSubjectViews(menus: List<StackMenu>) {
        gridLayout.removeAllViews()
        for ((index, subject) in menus.withIndex()) {
            val functionView: View = LayoutInflater.from(context).inflate(R.layout.item_subject, gridLayout, false)
            functionView.tvName.text = subject.name
            ImageUtils.loadBookCover(subject.icon, functionView.ivIcon)

            val rowSpec: GridLayout.Spec = GridLayout.spec(index / 5, 1f)
            val columnSpec: GridLayout.Spec = GridLayout.spec(index % 5, 1f)
            val layoutParams: GridLayout.LayoutParams = GridLayout.LayoutParams(rowSpec, columnSpec)
            gridLayout.addView(functionView, layoutParams)
            functionView.tag = subject

            functionView.setOnClickListener { v ->
                val subject = v?.tag as StackMenu
                when (subject.type) {
                    1 -> {
                        startActivity(Intent(context, ColumnActivity::class.java).putExtra("subject", subject))
                    }
                    2 -> {
                        startActivity(Intent(context, RankingActivity::class.java).putExtra("subject", subject))
                    }
                    3 -> {
                        startActivity(Intent(context, ClassificationActivity::class.java).putExtra("subject", subject))
                    }
                }
            }
        }
    }

    override fun onLoadStart(presenter: IPresenter) {
        if (!refreshLayout.isRefreshing
                && !refreshLayout.isLoading) {
            showLoading()
        }
    }

    override fun onLoadStop(presenter: IPresenter) {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadmore()
        showContent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscribe()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_search -> {
                startActivity(Intent(context, SearchActivity::class.java))
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
