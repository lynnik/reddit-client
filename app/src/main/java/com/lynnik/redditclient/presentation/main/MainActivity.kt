package com.lynnik.redditclient.presentation.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lynnik.redditclient.R
import com.lynnik.redditclient.domain.models.Child
import com.lynnik.redditclient.domain.models.TopResponse
import com.lynnik.redditclient.extensions.visibleOrGone
import com.lynnik.redditclient.presentation.base.BaseActivity
import com.lynnik.redditclient.presentation.main.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity<MainViewModel>(), MainAdapter.Listener {

    private val adapter = MainAdapter(this)

    override fun layoutResId() = R.layout.activity_main

    override fun viewModelClass() = MainViewModel::class.java

    override fun onChangeProgressBarVisibility(isVisible: Boolean) {
        progress_bar?.apply { visibleOrGone(isVisible) }
    }

    override fun onShowError(message: String) {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.error)
            setMessage(message)
            setPositiveButton(R.string.main_alert_dialog_repeat) { _, _ -> viewModel.getTop() }
            create()
            show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
        viewModel.getTop()
    }

    override fun onBindLiveData() {
        super.onBindLiveData()
        observe(viewModel.itemFromTop, ::updateItemFromTop)
        observe(viewModel.detailedPreview, ::detailedPostPreview)
    }

    override fun onSelectedItem(item: Child) {
        viewModel.preview(item)
    }

    private fun setupUi() {
        recycler_view?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
            addOnScrollListener(onScrollListener)
        }
    }

    private fun updateItemFromTop(response: TopResponse) {
        val children = response.data?.children
        children?.let { adapter.addItems(it.toMutableList()) }
        Timber.d(response.toString())
    }

    private fun detailedPostPreview(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (recyclerView.canScrollVertically(CAN_SCROLL_VERTICALLY_DIRECTION).not()) {
                viewModel.getTop()
            }
        }
    }

    companion object {
        private const val CAN_SCROLL_VERTICALLY_DIRECTION = 1
    }
}
