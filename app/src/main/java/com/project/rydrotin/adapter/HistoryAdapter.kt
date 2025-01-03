package com.project.rydrotin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.rydrotin.R
import com.project.rydrotin.local.entities.History

class HistoryAdapter(private val list: MutableList<History>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {

        fun onDeleteClicked(history: History, position: Int)

    }

    fun setItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.history_item, parent, false), mListener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.habitName.text = list[position].habitName
        holder.date.text = list[position].date
        holder.habitCount.text = "${list[position].countDone} out of ${list[position].countPerDay}"
        holder.message.text = list[position].message
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun removeItem(position: Int){
        list.removeAt(position)
        notifyItemRemoved(position)
    }
    inner class ViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView){
        val habitName: TextView = itemView.findViewById(R.id.habit_name)
        val date: TextView = itemView.findViewById(R.id.date)
        val habitCount: TextView = itemView.findViewById(R.id.count)
        val message: TextView = itemView.findViewById(R.id.msg_txt)
        private val deleteBtn: ImageButton = itemView.findViewById(R.id.delete)

        init {
            deleteBtn.setOnClickListener {
                listener.onDeleteClicked(list[adapterPosition], adapterPosition)
            }
        }
    }
}