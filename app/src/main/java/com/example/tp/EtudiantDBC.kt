package com.example.tp

import android.provider.BaseColumns

class EtudiantConnect {
abstract class EtudiantDBC: BaseColumns {
    companion object {
        val TABLE_NAME = "etudiant"
        val COLUMN_NAME_NOM = "nom"
        val COLUMN_NAME_PRENOM = "prenom"
        val COLUMN_NAME_PHONE = "phone"
        val COLUMN_NAME_EMAIL = "email"
        val COLUMN_NAME_LOGIN = "login"
        val COLUMN_NAME_MDP = "mdp"
        private val TEXT_TYPE = " TEXT"
        private val INT_TYPE = " INTEGER"
        private val COMMA_SEP = ","
        public val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_NAME " +
                "(_ID INTEGER PRIMARY KEY $COMMA_SEP $COLUMN_NAME_NOM TEXT $COMMA_SEP " +
                "$COLUMN_NAME_PRENOM TEXT $COMMA_SEP $COLUMN_NAME_PHONE TEXT $COMMA_SEP " +
                "$COLUMN_NAME_EMAIL TEXT $COMMA_SEP $COLUMN_NAME_LOGIN TEXT $COMMA_SEP " +
                "$COLUMN_NAME_MDP TEXT)"
        public val SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS $TABLE_NAME";
    }

}
}