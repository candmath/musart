package edu.ifsul.musart;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Ingresso.class}, version = 1, exportSchema = false)
public abstract class BancoDeDados extends RoomDatabase {

    public abstract IngressoDAO ingressoDao();

}
