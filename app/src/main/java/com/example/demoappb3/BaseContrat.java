package com.example.demoappb3;

import android.provider.BaseColumns;

public class BaseContrat {

    /**
     * Constructeur privé afin de ne pas instancier la classe.
     */
    private BaseContrat() {
    }

    public static class RessourcesContrat implements BaseColumns {
        /**
         * Contenu de la table "memos"
         */
        public static final String TABLE_MEMOS = "memos";
        public static final String COLONNE_MESSAGE = "message";
    }
}
