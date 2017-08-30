package com.lnybrave.zzbook.ui.progress

import android.graphics.drawable.Drawable
import android.view.View

import com.vlonjatg.progressactivity.ProgressLinearLayout

import java.util.Collections

internal class ProgressLinearLayoutWrapper(private val progressActivity: ProgressLinearLayout) : ProgressWidgetWrapper {

    /**
     * Hide all other states and show content
     */
    override fun showContent() {
        progressActivity.showContent()
    }

    /**
     * Hide all other states and show content

     * @param skipIds Ids of views not to show
     */
    override fun showContent(skipIds: List<Int>) {
        progressActivity.showContent(skipIds)
    }

    /**
     * Hide content and show the progress bar
     */
    override fun showLoading() {
        progressActivity.showLoading()
    }

    /**
     * Hide content and show the progress bar

     * @param skipIds Ids of views to not hide
     */
    override fun showLoading(skipIds: List<Int>) {
        progressActivity.showLoading(skipIds)
    }

    /**
     * Show empty view when there are not data to show

     * @param emptyImageDrawable Drawable to show
     * *
     * @param emptyTextTitle     Title of the empty view to show
     * *
     * @param emptyTextContent   Content of the empty view to show
     */
    override fun showEmpty(emptyImageDrawable: Int, emptyTextTitle: String, emptyTextContent: String) {
        progressActivity.showEmpty(emptyImageDrawable, emptyTextTitle, emptyTextContent)
    }

    /**
     * Show empty view when there are not data to show

     * @param emptyImageDrawable Drawable to show
     * *
     * @param emptyTextTitle     Title of the empty view to show
     * *
     * @param emptyTextContent   Content of the empty view to show
     */
    override fun showEmpty(emptyImageDrawable: Drawable, emptyTextTitle: String, emptyTextContent: String) {
        progressActivity.showEmpty(emptyImageDrawable, emptyTextTitle, emptyTextContent)
    }

    /**
     * Show empty view when there are not data to show

     * @param emptyImageDrawable Drawable to show
     * *
     * @param emptyTextTitle     Title of the empty view to show
     * *
     * @param emptyTextContent   Content of the empty view to show
     * *
     * @param skipIds            Ids of views to not hide
     */
    override fun showEmpty(emptyImageDrawable: Int, emptyTextTitle: String, emptyTextContent: String, skipIds: List<Int>) {
        progressActivity.showEmpty(emptyImageDrawable, emptyTextTitle, emptyTextContent, skipIds)
    }

    /**
     * Show empty view when there are not data to show

     * @param emptyImageDrawable Drawable to show
     * *
     * @param emptyTextTitle     Title of the empty view to show
     * *
     * @param emptyTextContent   Content of the empty view to show
     * *
     * @param skipIds            Ids of views to not hide
     */
    override fun showEmpty(emptyImageDrawable: Drawable, emptyTextTitle: String, emptyTextContent: String, skipIds: List<Int>) {
        progressActivity.showEmpty(emptyImageDrawable, emptyTextTitle, emptyTextContent, skipIds)
    }

    /**
     * Show error view with a button when something goes wrong and prompting the user to try again

     * @param errorImageDrawable Drawable to show
     * *
     * @param errorTextTitle     Title of the error view to show
     * *
     * @param errorTextContent   Content of the error view to show
     * *
     * @param errorButtonText    Text on the error view button to show
     * *
     * @param onClickListener    Listener of the error view button
     */
    override fun showError(errorImageDrawable: Int, errorTextTitle: String, errorTextContent: String, errorButtonText: String, onClickListener: View.OnClickListener) {
        progressActivity.showError(errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, emptyList<Int>())
    }

    /**
     * Show error view with a button when something goes wrong and prompting the user to try again

     * @param errorImageDrawable Drawable to show
     * *
     * @param errorTextTitle     Title of the error view to show
     * *
     * @param errorTextContent   Content of the error view to show
     * *
     * @param errorButtonText    Text on the error view button to show
     * *
     * @param onClickListener    Listener of the error view button
     */
    override fun showError(errorImageDrawable: Drawable, errorTextTitle: String, errorTextContent: String, errorButtonText: String, onClickListener: View.OnClickListener) {
        progressActivity.showError(errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, emptyList<Int>())
    }


    /**
     * Show error view with a button when something goes wrong and prompting the user to try again

     * @param errorImageDrawable Drawable to show
     * *
     * @param errorTextTitle     Title of the error view to show
     * *
     * @param errorTextContent   Content of the error view to show
     * *
     * @param errorButtonText    Text on the error view button to show
     * *
     * @param onClickListener    Listener of the error view button
     * *
     * @param skipIds            Ids of views to not hide
     */
    override fun showError(errorImageDrawable: Int, errorTextTitle: String, errorTextContent: String, errorButtonText: String, onClickListener: View.OnClickListener, skipIds: List<Int>) {
        progressActivity.showError(errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, skipIds)
    }

    /**
     * Show error view with a button when something goes wrong and prompting the user to try again

     * @param errorImageDrawable Drawable to show
     * *
     * @param errorTextTitle     Title of the error view to show
     * *
     * @param errorTextContent   Content of the error view to show
     * *
     * @param errorButtonText    Text on the error view button to show
     * *
     * @param onClickListener    Listener of the error view button
     * *
     * @param skipIds            Ids of views to not hide
     */
    override fun showError(errorImageDrawable: Drawable, errorTextTitle: String, errorTextContent: String, errorButtonText: String, onClickListener: View.OnClickListener, skipIds: List<Int>) {
        progressActivity.showError(errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, skipIds)
    }

    /**
     * Check if content is shown

     * @return boolean
     */
    override fun isContent(): Boolean {
        return progressActivity.isContent
    }

    /**
     * Check if loading state is shown

     * @return boolean
     */
    override fun isLoading(): Boolean {
        return progressActivity.isLoading
    }

    /**
     * Check if empty state is shown

     * @return boolean
     */
    override fun isEmpty(): Boolean {
        return progressActivity.isEmpty
    }

    /**
     * Check if error state is shown

     * @return boolean
     */
    override fun isError(): Boolean {
        return progressActivity.isError
    }

}
