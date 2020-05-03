package com.example.demoappb3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MemoDAO {
    public List<Memo> getListMemo(Context context) {
        String[] projection = {
                BaseContrat.RessourcesContrat._ID,
                BaseContrat.RessourcesContrat.COLONNE_MESSAGE
        };

        String filter = BaseContrat.RessourcesContrat._ID +  " ASC ";

        MemoDatabaseHelper memoDatabaseHelper = new MemoDatabaseHelper(context, null, null, 1);
        SQLiteDatabase db = memoDatabaseHelper.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.query(
                BaseContrat.RessourcesContrat.TABLE_MEMOS,
                projection,
                null,
                null,
                null,
                null,
                filter,
                null);

        List<Memo> listMemos = new ArrayList<>();

        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    listMemos.add(new Memo(
                            cursor.getString(cursor.getColumnIndex(BaseContrat.RessourcesContrat.COLONNE_MESSAGE))));
                    cursor.moveToNext();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                cursor.close();
            }
        }

        return listMemos;
    }
}
