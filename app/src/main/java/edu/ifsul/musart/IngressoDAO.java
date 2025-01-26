package edu.ifsul.musart;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IngressoDAO {

    // Consultar lista de ingressos

    @Query("SELECT * FROM ingresso")
    List<Ingresso> listarTodos();

    // Atualizar ingresso do Banco de Dados

    @Update
    void atualizar(Ingresso ingresso);

    // Inserir ingresso no Banco de Dados

    @Insert
    void inserir(Ingresso ingresso);

    // Excluir ingresso do Banco de Dados

    @Delete
    void excluir(Ingresso ingresso);

}

