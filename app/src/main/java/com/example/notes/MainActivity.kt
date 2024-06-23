package com.example.notes

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.db.Note_Data
import com.example.notes.db.Note_Database
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),Notes_Adapter.ClickListener {
    private lateinit var repo:Repo
    private lateinit var note_view_model:NotesViewModel
    private lateinit var note_view_model_factory:ViewModelFactory
    private lateinit var note_database:Note_Database
    private lateinit var notes_adapter: Notes_Adapter
    private lateinit var rv: RecyclerView
    private lateinit var btn_fab:FloatingActionButton
    private lateinit var dialog:Dialog
    private lateinit var txt_head:TextView
    private lateinit var edt_note_name:EditText
    private lateinit var edt_note_content:EditText
    private lateinit var btn_cancel:Button
    private lateinit var btn_save:Button
    private var clicked_note_data:Note_Data?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        note_view_model.getAllNotes().observe(this){
            Log.i("AllNote",it.toString())
            notes_adapter= Notes_Adapter(it,this)
            rv.adapter=notes_adapter
            rv.layoutManager=LinearLayoutManager(this)
        }

        btn_fab.setOnClickListener{

            showdialog(OpenFromFAB = true)
        }


    }

    private fun showdialog(OpenFromFAB:Boolean)
    {
        dialog= Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.fab_layout)


        txt_head=dialog.findViewById(R.id.txt_head)
        edt_note_name=dialog.findViewById(R.id.txt_note_name)
        edt_note_content=dialog.findViewById(R.id.txt_note_content)
        btn_cancel=dialog.findViewById(R.id.btn_cancel)
        btn_save=dialog.findViewById(R.id.btn_save)
        if(!OpenFromFAB)
            txt_head.text="Your Note"



        if(!OpenFromFAB && clicked_note_data!=null){
            edt_note_name.setText(clicked_note_data!!.Note_Name)
            edt_note_content.setText(clicked_note_data!!.Note_Content)
        }

        btn_save.setOnClickListener {

            if(OpenFromFAB)
            {
                val new_note=Note_Data(
                    Note_Name = edt_note_name.text.toString(),
                    Note_Content = edt_note_content.text.toString()
                )
                note_view_model.insert(new_note)
            }
            else{
                val new_note=Note_Data(
                    id =clicked_note_data!!.id,
                    Note_Name =edt_note_name.text.toString(),
                    Note_Content = edt_note_content.text.toString()
                )
                note_view_model.update(new_note)
            }
            dialog.dismiss()
        }
        dialog.show()
        btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun init(){
        note_database=Note_Database(this)
        repo=Repo(note_database.getNoteDao())
        note_view_model_factory=ViewModelFactory(repo)
        note_view_model=ViewModelProvider(this,note_view_model_factory).get(NotesViewModel::class.java)
        rv=findViewById(R.id.rv)
        btn_fab=findViewById(R.id.fab)

    }


    override fun updateNote(Note: Note_Data) {
        clicked_note_data=Note
        showdialog(OpenFromFAB = false)

    }

    override fun deleteNote(Note: Note_Data) {
        note_view_model.delete(Note)
    }
}