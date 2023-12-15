package com.example.tp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nom = findViewById<EditText>(R.id.nom)
        val Prenom = findViewById<EditText>(R.id.Prenom)
        val tel = findViewById<EditText>(R.id.tel)
        val email = findViewById<EditText>(R.id.email)
        val login = findViewById<EditText>(R.id.login)
        val motpass = findViewById<EditText>(R.id.motpass)
        val connexion = findViewById<Button>(R.id.modifier)
        val Annuler = findViewById<Button>(R.id.annuler)
        Annuler.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.apply {
                setTitle("Confirmation")
                setMessage("Are you sure to cancel?")
                setPositiveButton("Yes") { dialog, _ ->
                    nom.text.clear()
                    Prenom.text.clear()
                    tel.text.clear()
                    email.text.clear()
                    login.text.clear()
                    motpass.text.clear()
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
        val values = ContentValues()
        values.put(EtudiantConnect.EtudiantDBC.COLUMN_NAME_NOM, nom.text.toString())
        values.put(EtudiantConnect.EtudiantDBC.COLUMN_NAME_PRENOM, Prenom.text.toString())
        values.put(EtudiantConnect.EtudiantDBC.COLUMN_NAME_PHONE, tel.text.toString())
        values.put(EtudiantConnect.EtudiantDBC.COLUMN_NAME_EMAIL, email.text.toString())
        values.put(EtudiantConnect.EtudiantDBC.COLUMN_NAME_LOGIN, login.text.toString())
        values.put(EtudiantConnect.EtudiantDBC.COLUMN_NAME_MDP, motpass.text.toString())

        val mDbHelper = DBHelper(applicationContext)
        val db = mDbHelper.writableDatabase
        var newRowId: Long = 0

        newRowId = db.insert(
            EtudiantConnect.EtudiantDBC.TABLE_NAME,
            null,
            values
        )
        db.close()
        mDbHelper.close()
            val intent = Intent(this, ListEtudiant::class.java)
            startActivity(intent)
      }
    }
}
