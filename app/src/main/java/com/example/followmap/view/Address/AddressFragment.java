package com.example.followmap.view.Address;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.followmap.databinding.FragmentAddressBinding;
import com.example.followmap.databinding.FragmentCityBinding;
import com.example.followmap.entities.Endereco;
import com.example.followmap.entities.EnderecoComCidade;
import com.example.followmap.view.City.CityViewModel;
import com.example.followmap.view.User.UserView;

import java.util.List;

public class AddressFragment extends Fragment {

    private FragmentAddressBinding binding;
    private ListView listViewEnderecos;
    private AddressViewModel addressViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddressBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listViewEnderecos = binding.listViewEnderecos;

        binding.btnAddEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddressView.class);
                startActivity(intent);
            }
        });

        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        preencheEnderecos();
    }

    private void preencheEnderecos() {
        addressViewModel.getEnderecos().observe(getViewLifecycleOwner(), new Observer<List<EnderecoComCidade>>() {
            @Override
            public void onChanged(List<EnderecoComCidade> enderecos) {
                ArrayAdapter<EnderecoComCidade> enderecosAdapter = new ArrayAdapter<EnderecoComCidade>(getContext(), android.R.layout.simple_list_item_1, enderecos) {
                    @NonNull
                    @Override
                    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        EnderecoComCidade endereco = enderecos.get(position);
                        String textoParaExibir = endereco.endereco.getDescricao() + " - " + endereco.cidade.getCidade();
                        textView.setText(textoParaExibir);
                        return view;
                    }
                };
                listViewEnderecos.setAdapter(enderecosAdapter);

                listViewEnderecos.setOnItemClickListener((parent, view, position, id) -> {
                    EnderecoComCidade enderecoSelecionado = enderecos.get(position);
                    Intent intent = new Intent(getActivity(), AddressView.class);
                    intent.putExtra("ENDERECO_SELECIONADO_ID", enderecoSelecionado.endereco.getEnderecoId());
                    startActivity(intent);
                });
            }
        });
    }
}