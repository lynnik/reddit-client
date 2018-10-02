package com.lynnik.redditclient.presentation.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynnik.redditclient.R
import com.lynnik.redditclient.domain.models.Child
import com.lynnik.redditclient.extensions.DEFAULT_THUMBNAIL_URL
import com.lynnik.redditclient.extensions.inflate
import com.lynnik.redditclient.extensions.loadFromUrl
import com.lynnik.redditclient.extensions.toHoursAgo
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter(
        private val listener: Listener
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val list = mutableListOf<Child?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
            MainViewHolder(parent.inflate(R.layout.item_main))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val child = list[position]
        child?.let { holder.onBind(it) }
    }

    fun addItems(list: MutableList<Child?>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onSelectedItem(item: Child)
    }

    inner class MainViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(item: Child) {
            view.apply {
                image_view.loadFromUrl(item.data?.thumbnail ?: DEFAULT_THUMBNAIL_URL)
                author_text_view.text = item.data?.author
                subreddit_text_view.text = item.data?.subreddit
                title_text_view.text = item.data?.title
                rating_text_view.text = context.resources.getString(
                        R.string.main_item_rating,
                        item.data?.score
                )
                comments_text_view.text = context.resources.getString(
                        R.string.main_item_comments,
                        item.data?.numComments
                )
                date_text_view.text = context.resources.getString(
                        R.string.main_item_date,
                        item.data?.createdUtc?.toHoursAgo()
                )
                setOnClickListener { listener.onSelectedItem(item) }
            }
        }
    }
}