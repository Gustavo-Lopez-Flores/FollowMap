package com.example.followmap.view.City;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.followmap.databinding.FragmentCityBinding;
import com.example.followmap.entities.Cidade;
import com.example.followmap.view.User.UserView;

import java.util.List;

public class CityFragment extends Fragment {

    private FragmentCityBinding binding;
    private ListView listViewCidades;
    private CityViewModel cityViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listViewCidades = binding.listViewCidades;

        binding.btnAddCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CityView.class);
                startActivity(intent);
            }
        });

        cityViewModel = new ViewModelProvider(this).get(CityViewModel.class);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        preencheCidades();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void preencheCidades() {
        cityViewModel.getCidades().observe(getViewLifecycleOwner(), new Observer<List<Cidade>>() {
            @Override
            public void onChanged(List<Cidade> cidades) {
                ArrayAdapter<Cidade> cidadesAdapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_list_item_1, cidades);
                listViewCidades.setAdapter(cidadesAdapter);

                listViewCidades.setOnItemClickListener((parent, view, position, id) -> {
                    Cidade cidadeSelecionada = cidades.get(position);
                    Intent intent = new Intent(getActivity(), CityView.class);
                    intent.putExtra("CIDADE_SELECIONADA_ID", cidadeSelecionada.getCidadeId());
                    startActivity(intent);
                });
            }
        });
    }
}