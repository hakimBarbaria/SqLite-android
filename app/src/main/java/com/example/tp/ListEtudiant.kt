package com.example.tp

import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListEtudiant : AppCompatActivity() {
    private var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_etudiant)

        dbHelper = DBHelper(this)
        val cursor: Cursor? = dbHelper?.readableDatabase?.query(
            "etudiant",
            arrayOf("_ID","nom", "prenom"),
            null,
            null,
            null,
            null,
            null
        )

        val recyclerView = findViewById<RecyclerView>(R.id.etudiants)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = cursor?.let { EtudiantAdapter(this, it) }
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper?.close()
    }
}
