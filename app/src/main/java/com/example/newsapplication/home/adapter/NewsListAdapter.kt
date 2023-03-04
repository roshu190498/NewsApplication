package com.example.newsapplication.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.common.loadImage
import com.example.newsapplication.databinding.ItemNewsBinding
import com.example.newsapplication.home.model.Articles

class NewsListAdapter : ListAdapter<Articles, NewsListAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(itemDiffCallback).build()
) {
    private var callback : NewsItemCalls?=null
    private var localList : List<Articles>?=null
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

    fun setDataToNewsList(list:List<Articles>,callback:NewsItemCalls?,localList:List<Articles>?=null){
        this.submitList(list)
        this.callback=callback
        this.localList=localList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        view.root.layoutParams = ViewGroup.LayoutParams((parent.width * 0.8).toInt(),ViewGroup.LayoutParams.MATCH_PARENT)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val itemBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

            fun bind(article : Articles){
                val d = localList?.filter { it.title == article.title }
                itemBinding.tgLocal.isChecked = d != null
                itemBinding.tvNewsTittle.text = article.title
                itemBinding.tvNewsDesc.text = article.description
                itemBinding.ivNewsImage.loadImage(article.urlToImage)
                itemBinding.tvTime.text = itemBinding.root.context.getString(R.string.txt_time,article.publishedAt)
                itemBinding.tgLocal.setOnClickListener {
                    callback?.addtoLocal(article)
                }
                itemBinding.tvRedirect.setOnClickListener {
                    callback?.redirectToUrl(article.url)
                }
                itemBinding.tgLocal.setOnCheckedChangeListener { buttonView, isChecked ->
                    //do nothing
                }
            }
    }

    interface NewsItemCalls{
        fun addtoLocal(data:Articles)
        fun redirectToUrl(url:String?)
    }
}