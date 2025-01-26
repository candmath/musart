package edu.ifsul.musart;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musart.R;

public class ModeloDeLinha extends RecyclerView.ViewHolder {

    // Declaração dos elementos do modelo de linha

    public TextView textoTituloExposicao, textoDescricaoExposicao, textoMuseu,
            textoPreco, textoTipoIngresso, textoDataExposicao, textoHoraExposicao;
    public ImageView botaoExcluir;

    // Método construtor da classe ModeloDeLinha

    public ModeloDeLinha(View itemView) {

        super(itemView);

        textoTituloExposicao = itemView.findViewById(R.id.textoTituloExposicao);
        textoDataExposicao = itemView.findViewById(R.id.textoDataExposicao);
        textoHoraExposicao = itemView.findViewById(R.id.textoHoraExposicao);
        textoDescricaoExposicao = itemView.findViewById(R.id.textoDescricaoExposicao);
        textoMuseu = itemView.findViewById(R.id.textoMuseu);
        textoPreco = itemView.findViewById(R.id.textoPreco);
        textoTipoIngresso = itemView.findViewById(R.id.textoTipoIngresso);
        botaoExcluir = itemView.findViewById(R.id.botaoExcluir);

    }

}
