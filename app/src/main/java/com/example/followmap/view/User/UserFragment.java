package com.example.followmap.view.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.followmap.R;
import com.example.followmap.databinding.FragmentUserBinding;

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

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}