package com.example.followmap.view.User;

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

import com.example.followmap.databinding.FragmentUserBinding;
import com.example.followmap.entities.Usuario;
import com.example.followmap.view.MainActivity;

import java.util.List;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    private ListView listViewUsuarios;
    private UserViewModel userViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listViewUsuarios = binding.listViewUsuarios;

        binding.btnAddUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserView.class);
                startActivity(intent);
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        preencheUsuarios();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void preencheUsuarios() {
        userViewModel.getUsuarios().observe(getViewLifecycleOwner(), new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {
                ArrayAdapter<Usuario> usuariosAdapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_list_item_1, usuarios);
                listViewUsuarios.setAdapter(usuariosAdapter);

                listViewUsuarios.setOnItemClickListener((parent, view, position, id) -> {
                    Usuario usuarioSelecionado = usuarios.get(position);
                    Intent intent = new Intent(getActivity(), UserView.class);
                    intent.putExtra("USUARIO_SELECIONADO_ID", usuarioSelecionado.getUsuarioId());
                    startActivity(intent);
                });
            }
        });
    }
}