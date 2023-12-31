package com.example.mg.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mg.R
import com.example.mg.data.MyTask

class ListFragmentAdapter(private val listener: OnItemClickListener) : ListAdapter<MyTask, ListFragmentAdapter.ArticleHolder>(DIFF_CALLBACK) {

    internal fun getItemAtPosition(position: Int): MyTask {
        return getItem(position)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ArticleHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.main_activity_list_item, viewGroup, false)
        return ArticleHolder(itemView)
    }



    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val currentTask = getItemAtPosition(position)

        holder.apply {
            tvName.text = currentTask.description

            itemView.setOnClickListener {
                listener.onItemClick(currentTask)
            }
            itemView.setOnLongClickListener {
                listener.onItemLongClick(currentTask)
            }

        }

    }


    interface OnItemClickListener {
        fun onItemClick(task: MyTask)
        fun onItemLongClick(task: MyTask): Boolean
    }

    inner class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val tvName: TextView = itemView.findViewById(R.id.text_view_title)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MyTask>() {
            override fun areItemsTheSame(oldNote: MyTask, newNote: MyTask): Boolean {
                return oldNote.id == newNote.id
            }

            override fun areContentsTheSame(oldNote: MyTask, newNote: MyTask): Boolean {
                return oldNote.description == newNote.description && oldNote.status == newNote.status
            }
        }
    }
}
