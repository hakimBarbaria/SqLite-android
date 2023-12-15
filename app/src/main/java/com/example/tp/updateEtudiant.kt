package com.example.tp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class updateEtudiant : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val nom = intent.getStringExtra("nom")
        val prenom = intent.getStringExtra("prenom")

        val nomEditText = findViewById<EditText>(R.id.nom)
        val prenomEditText = findViewById<EditText>(R.id.Prenom)

        nomEditText.setText(nom)
        prenomEditText.setText(prenom)

        val connexion = findViewById<Button>(R.id.modifier)
        val Annuler = findViewById<Button>(R.id.annuler)
        connexion.setText("Update")
        Annuler.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.apply {
                setTitle("Confirmation")
                setMessage("Are you sure to cancel?")
                setPositiveButton("Yes") { dialog, _ ->
                    nomEditText.text.clear()
                    prenomEditText.text.clear()
                    dialog.dismiss()
                }
                setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
        connexion.setOnClickListener {
            val name = nom
            val values = ContentValues()
            values.put(EtudiantConnect.EtudiantDBC.COLUMN_NAME_NOM, nomEditText.text.toString())
            values.put(EtudiantConnect.EtudiantDBC.COLUMN_NAME_PRENOM, prenomEditText.text.toString())

            val mDbHelper = DBHelper(applicationContext)
            val db = mDbHelper.writableDatabase

            val selection = "${EtudiantConnect.EtudiantDBC.COLUMN_NAME_NOM} = ?"
            val selectionArgs = arrayOf(nom)

            val updatedRows = db.update(
                EtudiantConnect.EtudiantDBC.TABLE_NAME,
                values,
                selection,
                selectionArgs
            )

            db.close()
            mDbHelper.close()

            if (updatedRows > 0) {
                val intent = Intent(this, ListEtudiant::class.java)
                startActivity(intent)
            }
        }
    }
}