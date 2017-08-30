package com.lnybrave.zzbook.ui.progress

import android.graphics.drawable.Drawable
import android.view.View

internal interface ProgressWidgetWrapper {

    /**
     * Hide all other states and show content
     */
    fun showContent()

    /**
     * Hide all other states and show content

     * @param skipIds Ids of views not to show
     */
    fun showContent(skipIds: List<Int>)

    /**
     * Hide content and show the progress bar
     */
    fun showLoading()

    /**
     * Hide content and show the progress bar

     * @param skipIds Ids of views to not hide
     */
    fun showLoading(skipIds: List<Int>)

    /**
     * Show empty view when there are not data to show

     * @param emptyImageDrawable Drawable to show
     * *
     * @param emptyTextTitle     Title of the empty view to show
     * *
     * @param emptyTextContent   Content of the empty view to show
     */
    fun showEmpty(emptyImageDrawable: Int, emptyTextTitle: String, emptyTextContent: String)

    /**
     * Show empty view when there are not data to show

     * @param emptyImageDrawable Drawable to show
     * *
     * @param emptyTextTitle     Title of the empty view to show
     * *
     * @param emptyTextContent   Content of the empty view to show
     */
    fun showEmpty(emptyImageDrawable: Drawable, emptyTextTitle: String, emptyTextContent: String)

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
    fun showEmpty(emptyImageDrawable: Int, emptyTextTitle: String, emptyTextContent: String, skipIds: List<Int>)

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
    fun showEmpty(emptyImageDrawable: Drawable, emptyTextTitle: String, emptyTextContent: String, skipIds: List<Int>)

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
    fun showError(errorImageDrawable: Int, errorTextTitle: String, errorTextContent: String, errorButtonText: String, onClickListener: View.OnClickListener)

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
    fun showError(errorImageDrawable: Drawable, errorTextTitle: String, errorTextContent: String, errorButtonText: String, onClickListener: View.OnClickListener)


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
    fun showError(errorImageDrawable: Int, errorTextTitle: String, errorTextContent: String, errorButtonText: String, onClickListener: View.OnClickListener, skipIds: List<Int>)

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
    fun showError(errorImageDrawable: Drawable, errorTextTitle: String, errorTextContent: String, errorButtonText: String, onClickListener: View.OnClickListener, skipIds: List<Int>)

    /**
     * Check if content is shown

     * @return boolean
     */
    fun isContent(): Boolean

    /**
     * Check if loading state is shown

     * @return boolean
     */
    fun isLoading(): Boolean

    /**
     * Check if empty state is shown

     * @return boolean
     */
    fun isEmpty(): Boolean

    /**
     * Check if error state is shown

     * @return boolean
     */
    fun isError(): Boolean
}
