package com.lnybrave.zzbook.ui

import android.graphics.drawable.Drawable
import android.view.View
import com.lnybrave.zzbook.ui.progress.ProgressWidget
import com.lnybrave.zzbook.ui.progress.ProgressWidgetWrapper
import com.vlonjatg.progressactivity.ProgressFrameLayout
import com.vlonjatg.progressactivity.ProgressLinearLayout
import com.vlonjatg.progressactivity.ProgressRelativeLayout

abstract class ProgressActivity : BaseActivity(), ProgressWidgetWrapper {

    private var progressWidgetWrapper: ProgressWidgetWrapper? = null

    protected fun setProgressActivity(progressFrameLayout: ProgressFrameLayout) {
        this.progressWidgetWrapper = ProgressWidget.wrapper(progressFrameLayout)
    }

    protected fun setProgressActivity(progressLinearLayout: ProgressLinearLayout) {
        this.progressWidgetWrapper = ProgressWidget.wrapper(progressLinearLayout)
    }

    protected fun setProgressActivity(progressRelativeLayout: ProgressRelativeLayout) {
        this.progressWidgetWrapper = ProgressWidget.wrapper(progressRelativeLayout)
    }

    override fun showContent() {
        checkProgressWidgetWrapper()
        progressWidgetWrapper!!.showContent()
    }

    override fun showContent(skipIds: List<Int>) {
        checkProgressWidgetWrapper()
        progressWidgetWrapper!!.showContent(skipIds)
    }

    override fun showLoading() {
        checkProgressWidgetWrapper()
        progressWidgetWrapper!!.showLoading()
    }

    override fun showLoading(skipIds: List<Int>) {
        checkProgressWidgetWrapper()
        progressWidgetWrapper!!.showLoading(skipIds)
    }

    override fun showEmpty(emptyImageDrawable: Int, emptyTextTitle: String, emptyTextContent: String) {
        checkProgressWidgetWrapper()
        progressWidgetWrapper!!.showEmpty(emptyImageDrawable, emptyTextTitle, emptyTextContent)
    }

    override fun showEmpty(emptyImageDrawable: Drawable, emptyTextTitle: String, emptyTextContent: String) {
        checkProgressWidgetWrapper()
        progressWidgetWrapper!!.showEmpty(emptyImageDrawable, emptyTextTitle, emptyTextContent)
    }

    override fun showEmpty(emptyImageDrawable: Int, emptyTextTitle: String, emptyTextContent: String, skipIds: List<Int>) {
        checkProgressWidgetWrapper()
        progressWidgetWrapper!!.showEmpty(emptyImageDrawable, emptyTextTitle, emptyTextContent, skipIds)
    }

    override fun showEmpty(emptyImageDrawable: Drawable, emptyTextTitle: String, emptyTextContent: String, skipIds: List<Int>) {
        checkProgressWidgetWrapper()
        progressWidgetWrapper!!.showEmpty(emptyImageDrawable, emptyTextTitle, emptyTextContent, skipIds)
    }

    override fun showError(errorImageDrawable: Int, errorTextTitle: String, errorTextContent: String, errorButtonText: String, onClickListener: View.OnClickListener) {
        checkProgressWidgetWrapper()
        progressWidgetWrapper!!.showError(errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener)
    }

    override fun showError(errorImageDrawable: Drawable, errorTextTitle: String, errorTextContent: String, errorButtonText: String, onClickListener: View.OnClickListener) {
        checkProgressWidgetWrapper()
        progressWidgetWrapper!!.showError(errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener)
    }

    override fun showError(errorImageDrawable: Int, errorTextTitle: String, errorTextContent: String, errorButtonText: String, onClickListener: View.OnClickListener, skipIds: List<Int>) {
        checkProgressWidgetWrapper()
        progressWidgetWrapper!!.showError(errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, skipIds)
    }

    override fun showError(errorImageDrawable: Drawable, errorTextTitle: String, errorTextContent: String, errorButtonText: String, onClickListener: View.OnClickListener, skipIds: List<Int>) {
        checkProgressWidgetWrapper()
        progressWidgetWrapper!!.showError(errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, skipIds)
    }

    override fun isContent(): Boolean {
        return progressWidgetWrapper!!.isContent()
    }

    override fun isLoading(): Boolean {
        return progressWidgetWrapper!!.isLoading()
    }

    override fun isEmpty(): Boolean {
        return progressWidgetWrapper!!.isEmpty()
    }

    override fun isError(): Boolean {
        return progressWidgetWrapper!!.isError()
    }

    private fun checkProgressWidgetWrapper() {
        if (progressWidgetWrapper == null) {
            throw NullPointerException("progressWidgetWrapper is null")
        }
    }

}