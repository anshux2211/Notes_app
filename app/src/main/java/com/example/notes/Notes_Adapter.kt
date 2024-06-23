package com.example.notes

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notes.db.Note_Data

class Notes_Adapter(
    private val listOfNote:List<Note_Data>,
    private val onitemClick: ClickListener
):RecyclerView.Adapter<Notes_Adapter.XViewHolder>() {


    class XViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val txt_view:TextView=itemView.findViewById(R.id.NoteName)
        val img_view:ImageView=itemView.findViewById(R.id.DeleteNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_layout,parent,false)

        return XViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfNote.size
    }

    override fun onBindViewHolder(holder: XViewHolder, position: Int) {
        val currentNote=listOfNote[position]
        holder.txt_view.text=currentNote.Note_Name

        holder.itemView.setOnClickListener{
            onitemClick.updateNote(currentNote)
        }
        holder.img_view.setOnClickListener {
            onitemClick.deleteNote(currentNote)
        }

    }

    interface ClickListener{
        fun updateNote(Note:Note_Data)
        fun deleteNote(Note: Note_Data)
    }
}