package edu.ifsul.musart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import com.example.musart.R;
import com.example.musart.databinding.FragmentThirdBinding;

public class ThirdFragment extends Fragment {

    // Declaração do binding

    private FragmentThirdBinding binding;

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

            // Realiza a navegação entre os fragmentos

            NavHostFragment.findNavController(ThirdFragment.this)
                    .navigate(R.id.action_thirdFragment_to_secondFragment);

            return true;

        }

        return super.onOptionsItemSelected(item);

    }

    // Método onCreateView

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Atribuição do binding

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    // Método onViewCreated

    public void onViewCreated(@NonNull View viewDefault, Bundle savedInstanceState) {

        super.onViewCreated(viewDefault, savedInstanceState);

        // Listener de cliques no botão "finalizar compra"

        binding.botaoFinalizarCompra.setOnClickListener(view -> {

            // Verificação do campo do título da exposição

            if (binding.campoTituloExposicao.getText().toString().isEmpty()) {

                binding.campoTituloExposicao.setError("Este campo é obrigatório!");
                binding.campoTituloExposicao.requestFocus();

                return;

            }

            // Verificação do campo da descrição da exposição

            if (binding.campoDescricaoExposicao.getText().toString().isEmpty()) {

                binding.campoDescricaoExposicao.setError("Este campo é obrigatório!");
                binding.campoDescricaoExposicao.requestFocus();

                return;

            }

            // Verificação do campo do nome do museu

            if (binding.campoMuseu.getText().toString().isEmpty()) {

                binding.campoMuseu.setError("Este campo é obrigatório!");
                binding.campoMuseu.requestFocus();

                return;

            }

            // Verificação do campo do preço do ingresso

            if (binding.campoPreco.getText().toString().isEmpty()) {

                binding.campoPreco.setError("Este campo é obrigatório!");
                binding.campoPreco.requestFocus();

                return;

            }

            // Verificação do campo da data da exposição

            if (binding.campoDataExposicao.getText().toString().isEmpty()) {

                binding.campoDataExposicao.setError("Este campo é obrigatório!");
                binding.campoDataExposicao.requestFocus();

                return;

            }

            // Verificação do campo da hora da exposição

            if (binding.campoHoraExposicao.getText().toString().isEmpty()) {

                binding.campoHoraExposicao.setError("Este campo é obrigatório!");
                binding.campoHoraExposicao.requestFocus();

                return;

            }

            // Atribuição das informações digitadas

            String tituloDigitado = binding.campoTituloExposicao.getText().toString();
            String descricaoDigitada = binding.campoDescricaoExposicao.getText().toString();
            String museuDigitado = binding.campoMuseu.getText().toString();
            Double precoDigitado = Double.valueOf(binding.campoPreco.getText().toString());
            String dataExposicao = binding.campoDataExposicao.getText().toString();
            String horaExposicao = binding.campoHoraExposicao.getText().toString();
            String tipoIngresso;

            // Atribuição do checkbox do tipo de ingresso

            if (binding.checkboxMeiaEntrada.isChecked()) {

                tipoIngresso = "Meia-entrada";

            } else {

                tipoIngresso = "Inteira";

            }

            // Inserção do novo ingresso no Banco de Dados

            ingressoDAO.inserir(new Ingresso(tituloDigitado, descricaoDigitada, museuDigitado,
                    precoDigitado, tipoIngresso, dataExposicao, horaExposicao));

            // Criação da mensagem de sucesso através do Toast

            Toast.makeText(getContext(), "O ingresso foi cadastrado com sucesso!",
                    Toast.LENGTH_SHORT).show();

            // Realiza a navegação entre os fragmentos

            NavHostFragment.findNavController(ThirdFragment.this)
                    .navigate(R.id.action_thirdFragment_to_firstFragment);

        });

        // Declaração do Banco de Dados

        BancoDeDados BD = Room.databaseBuilder(requireActivity().getBaseContext(),
                BancoDeDados.class, "BD_MUSART").allowMainThreadQueries().build();

        // Atribuição do ingressoDAO

        ingressoDAO = BD.ingressoDao();

        // Criação do pacote de dados vindo do primeiro fragmento

        Bundle pacoteDados = getArguments();

        // Verificação do pacote de dados

        if (pacoteDados != null) {

            // Recupera o ingresso selecionado no primeiro fragmento

            Ingresso ingressoSelecionado = (Ingresso) pacoteDados.getSerializable("ingressoSelecionado");

            // Definição dos campos do formulário com os valores vindos do pacote de dados

            binding.campoTituloExposicao.setText(ingressoSelecionado.titulo);
            binding.campoDescricaoExposicao.setText(ingressoSelecionado.descricao);
            binding.campoMuseu.setText(ingressoSelecionado.museu);
            binding.campoPreco.setText(String.valueOf(ingressoSelecionado.preco));
            binding.campoDataExposicao.setText(ingressoSelecionado.dataExposicao);
            binding.campoHoraExposicao.setText(ingressoSelecionado.horaExposicao);
            binding.checkboxMeiaEntrada.setChecked(ingressoSelecionado.tipo.equals("Meia-entrada"));

            // Listener de cliques no botão "finalizar compra"

            binding.botaoFinalizarCompra.setOnClickListener(view -> {

                // Definição dos textos dos campos do formulário com as informações do ingresso

                String novoTituloDigitado = binding.campoTituloExposicao.getText().toString();
                String novaDescricaoDigitada = binding.campoDescricaoExposicao.getText().toString();
                String novoMuseuDigitado = binding.campoMuseu.getText().toString();
                Double novoPrecoDigitado = Double.parseDouble(binding.campoPreco.getText().toString());
                String novaDataExposicao = binding.campoDataExposicao.getText().toString();
                String novaHoraExposicao = binding.campoHoraExposicao.getText().toString();
                String novoTipoIngresso;

                // Verificação do campo do novo título da exposição

                if (binding.campoTituloExposicao.getText().toString().isEmpty()) {

                    binding.campoTituloExposicao.setError("Este campo é obrigatório!");
                    binding.campoTituloExposicao.requestFocus();

                    return;

                }

                // Verificação do campo da nova descrição da exposição

                if (binding.campoDescricaoExposicao.getText().toString().isEmpty()) {

                    binding.campoDescricaoExposicao.setError("Este campo é obrigatório!");
                    binding.campoDescricaoExposicao.requestFocus();

                    return;

                }

                // Verificação do campo do novo nome do museu

                if (binding.campoMuseu.getText().toString().isEmpty()) {

                    binding.campoMuseu.setError("Este campo é obrigatório!");
                    binding.campoMuseu.requestFocus();

                    return;

                }

                // Verificação do campo do novo preço do ingresso

                if (binding.campoPreco.getText().toString().isEmpty()) {

                    binding.campoPreco.setError("Este campo é obrigatório!");
                    binding.campoPreco.requestFocus();

                    return;

                }

                // Verificação do campo da nova data da exposição

                if (binding.campoDataExposicao.getText().toString().isEmpty()) {

                    binding.campoDataExposicao.setError("Este campo é obrigatório!");
                    binding.campoDataExposicao.requestFocus();

                    return;

                }

                // Verificação do campo do novo horário da exposição

                if (binding.campoHoraExposicao.getText().toString().isEmpty()) {

                    binding.campoHoraExposicao.setError("Este campo é obrigatório!");
                    binding.campoHoraExposicao.requestFocus();

                    return;

                }

                // Atribuição do checkbox do novo tipo de ingresso

                if (binding.checkboxMeiaEntrada.isChecked()) {

                    novoTipoIngresso = "Meia-entrada";

                } else {

                    novoTipoIngresso = "Inteira";

                }

                // Atribuição das informações digitadas no formulário

                ingressoSelecionado.titulo = novoTituloDigitado;
                ingressoSelecionado.descricao = novaDescricaoDigitada;
                ingressoSelecionado.museu = novoMuseuDigitado;
                ingressoSelecionado.preco = novoPrecoDigitado;
                ingressoSelecionado.tipo = novoTipoIngresso;
                ingressoSelecionado.dataExposicao = novaDataExposicao;
                ingressoSelecionado.horaExposicao = novaHoraExposicao;

                // Atualização do ingresso no Banco de Dados

                ingressoDAO.atualizar(ingressoSelecionado);

                // Criação da mensagem de sucesso através do Toast

                Toast.makeText(getContext(), "O ingresso selecionado " +
                        "foi editado com sucesso!", Toast.LENGTH_SHORT).show();

                // Realiza a navegação entre os fragmentos

                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_thirdFragment_to_firstFragment);

            });

        }

    }

    // Método onDestroyView

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        binding = null;

    }

}