package edu.ifsul.musart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.musart.R;

public class FirstFragment extends Fragment {

    // Declaração do adaptador da lista

    private Adaptador adaptador;

    // Declaração do Data Access Object (DAO) da tabela Ingresso

    private IngressoDAO ingressoDAO;

    // Método onCreate

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    // Método onOptionsItemSelected

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_about) {

            // Realização da navegação entre os fragmentos

            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_firstFragment_to_secondFragment);

            return true;

        }

        return super.onOptionsItemSelected(item);

    }

    // Método onCreateView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Declaração da tela

        View tela = inflater.inflate(R.layout.fragment_first, container, false);

        // Declaração do botão Comprar

        Button botaoComprar = tela.findViewById(R.id.botaoComprar);

        // Listener de clique no botão Comprar

        botaoComprar.setOnClickListener(view -> {

            // Realização da navegação entre os fragmentos

            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_firstFragment_to_thirdFragment);

        });

        // Declaração do RecyclerView

        RecyclerView recyclerView = tela.findViewById(R.id.listaIngressos);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity().getBaseContext()));

        // Declaração e vinculação do adaptador com o RecyclerView

        adaptador = new Adaptador();
        recyclerView.setAdapter(adaptador);

        // Declaração do Banco de Dados

        BancoDeDados BD = Room.databaseBuilder(requireActivity().getBaseContext(),
                BancoDeDados.class, "BD_MUSART").allowMainThreadQueries().build();

        // Atribuição do Data Access Object (DAO) da tabela Ingresso

        ingressoDAO = BD.ingressoDao();

        // Importação dos dados do Banco de Dados

        adaptador.getLista().addAll(ingressoDAO.listarTodos());

        // Declaração das ações do evento de clique na linha

        adaptador.setEventoDeCliqueNaLinha(ingressoClicado -> {

            // Criação e envio do Bundle para o terceiro fragmento

            Bundle pacoteDados = new Bundle();

            pacoteDados.putSerializable("ingressoSelecionado", ingressoClicado);

            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_firstFragment_to_thirdFragment, pacoteDados);

        });

        // Declaração das ações do evento de clique no botão de exclusão

        adaptador.setEventoDeCliqueParaDeletar(ingressoSelecionado -> {

            // Remoção do ingresso selecionado do Banco de Dados

            ingressoDAO.excluir(ingressoSelecionado);

            // Remoção do ingresso selecionado da lista

            adaptador.getLista().remove(ingressoSelecionado);

            // Aviso ao adaptador que a operação foi realizada com sucesso

            adaptador.notifyDataSetChanged();

            // Criação da mensagem de sucesso através do Toast

            Toast.makeText(getContext(), "Ingresso excluído com sucesso!",
                    Toast.LENGTH_SHORT).show();

        });

        return tela;

    }

}