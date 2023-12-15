package com.example.tp

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CursorAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp.R

class EtudiantAdapter(private val context: Context, private var cursor: Cursor?) :
    RecyclerView.Adapter<EtudiantAdapter.EtudiantViewHolder>() {

    inner class EtudiantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val prenomTextView: TextView = itemView.findViewById(R.id.prenom)
        val deleteItem: Button = itemView.findViewById(R.id.delete)
        val updateItem: Button = itemView.findViewById(R.id.update)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EtudiantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_etud, parent, false)
        return EtudiantViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

    override fun onBindViewHolder(holder: EtudiantViewHolder, position: Int) {
        cursor?.moveToPosition(position)
        val nomIndex = cursor?.getColumnIndexOrThrow("nom")
        val prenomIndex = cursor?.getColumnIndexOrThrow("prenom")
        val idIndex = cursor?.getColumnIndexOrThrow("_ID")

        val nom = cursor?.getString(nomIndex ?: -1)
        val prenom = cursor?.getString(prenomIndex ?: -1)
        val id = cursor?.getInt(idIndex ?:-1)
        holder.nameTextView.text = nom
        holder.prenomTextView.text = prenom
        holder.deleteItem.setOnClickListener {
            val database = DBHelper(context).writableDatabase
            val selection = "nom = ?"
            val selectionArgs = arrayOf(nom.toString())
            Log.d("index","${idIndex}")
            val deletedRows = database.delete("etudiant", selection, selectionArgs)
            database.close()

            if (deletedRows > 0) {
                notifyDataSetChanged()
                val intent = Intent(context, ListEtudiant::class.java)
                context.startActivity(intent)
            }
        }
        holder.updateItem.setOnClickListener {
            val intent = Intent(context, updateEtudiant::class.java)
            intent.putExtra("nom", nom)
            intent.putExtra("prenom", prenom)
            context.startActivity(intent)
        }

    }

    fun changeCursor(newCursor: Cursor?) {
        cursor?.close()
        cursor = newCursor
        notifyDataSetChanged()
    }
}
