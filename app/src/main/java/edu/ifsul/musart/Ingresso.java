package edu.ifsul.musart;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Ingresso implements Serializable {

    // Declaração dos campos da tabela Ingresso

    @PrimaryKey(autoGenerate = true)
    public Long id;
    public String titulo, descricao, museu, tipo, dataExposicao, horaExposicao;
    public Double preco;

    // Método construtor da classe Ingresso

    public Ingresso (String titulo, String descricao, String museu,
                     Double preco, String tipo, String dataExposicao,
                     String horaExposicao){

        this.titulo = titulo;
        this.descricao = descricao;
        this.museu = museu;
        this.preco = preco;
        this.tipo = tipo;
        this.dataExposicao = dataExposicao;
        this.horaExposicao = horaExposicao;

    }

}
