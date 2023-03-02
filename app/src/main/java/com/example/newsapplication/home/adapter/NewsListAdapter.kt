package com.example.newsapplication.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.databinding.ItemNewsBinding
import com.example.newsapplication.home.model.Articles

class NewsListAdapter : ListAdapter<Articles, NewsListAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(itemDiffCallback).build()
) {
    private var callback : NewsItemCalls?=null
    companion object{
        val itemDiffCallback = object : DiffUtil.ItemCallback<Articles>(){
            override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem==newItem
            }

        }
    }

    fun setDataToNewsList(list:List<Articles>,callback:NewsItemCalls){
        this.submitList(list)
        this.callback=callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val itemBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

            fun bind(article : Articles){
                itemBinding.tvNewsTittle.text = article.title
                itemBinding.tvNewsDesc.text = article.description

                itemBinding.ivNewsImage.setOnClickListener {

                }
            }
    }

    interface NewsItemCalls{
        fun addtoLocal(data:Articles)
        fun redirectToUrl(url:String)
    }
}