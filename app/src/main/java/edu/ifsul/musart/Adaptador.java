package edu.ifsul.musart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musart.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Adaptador extends RecyclerView.Adapter<ModeloDeLinha> {

    // Declaração da lista dos ingressos

    private final List<Ingresso> lista;

    // Método construtor da classe Adaptador

    public Adaptador() {

        lista = new ArrayList<>();

    }

    // Declaração do evento de clique na linha

    public EventoDeCliqueCustomizado<Ingresso> eventoDeCliqueNaLinha = null;

    public void setEventoDeCliqueNaLinha(EventoDeCliqueCustomizado<Ingresso> evento) {

        this.eventoDeCliqueNaLinha = evento;

    }

    // Declaração do evento de clique no botão de exclusão

    public EventoDeCliqueCustomizado<Ingresso> eventoDeCliqueParaDeletar = null;

    public void setEventoDeCliqueParaDeletar(EventoDeCliqueCustomizado<Ingresso> evento) {

        this.eventoDeCliqueParaDeletar = evento;

    }

    // Método getLista

    public List<Ingresso> getLista() {

        return lista;

    }

    // Método onCreateViewHolder

    @NonNull
    public ModeloDeLinha onCreateViewHolder(ViewGroup parent, int viewType) {

        Context ativityEmExecucao = parent.getContext();

        ModeloDeLinha linhaExibida = new ModeloDeLinha(LayoutInflater.from(ativityEmExecucao)
                .inflate(R.layout.modelo_de_linha, parent, false));

        // Declaração das ações do evento de clique no botão de exclusão

        if (eventoDeCliqueParaDeletar != null) {

            linhaExibida.botaoExcluir.setOnClickListener(view -> {

                // Seleciona o ingresso que está sendo exibido

                Ingresso ingressoSelecionado = lista.get(linhaExibida.getAdapterPosition());

                // Execução do evento

                eventoDeCliqueParaDeletar.onItemClick(ingressoSelecionado);

            });

        }

        // Declaração das ações do evento de clique em um item da lista

        if (eventoDeCliqueNaLinha != null) {

            linhaExibida.itemView.setOnClickListener(view -> {

                // Seleciona o ingresso que está sendo exibido

                Ingresso ingressoSelecionado = lista.get(linhaExibida.getAdapterPosition());

                // Execução do evento

                eventoDeCliqueNaLinha.onItemClick(ingressoSelecionado);

            });


        }

        return linhaExibida;

    }

    // Método onBindViewHolder

    @Override
    public void onBindViewHolder(ModeloDeLinha linhaExibida, int posicao) {

        // Importação do ingresso e dos dados para os campos do modelo de linha

        Ingresso ingressoSelecionado = lista.get(posicao);

        linhaExibida.textoTituloExposicao.setText(ingressoSelecionado.titulo);
        linhaExibida.textoDataExposicao.setText(ingressoSelecionado.dataExposicao);
        linhaExibida.textoHoraExposicao.setText(ingressoSelecionado.horaExposicao);
        linhaExibida.textoDescricaoExposicao.setText(ingressoSelecionado.descricao);
        linhaExibida.textoMuseu.setText(ingressoSelecionado.museu);

        linhaExibida.textoPreco.setText(NumberFormat.getCurrencyInstance().
                format(ingressoSelecionado.preco));

        linhaExibida.textoTipoIngresso.setText(ingressoSelecionado.tipo);

    }

    // Método getItemCount

    @Override
    public int getItemCount() {

        return lista.size();

    }

}

