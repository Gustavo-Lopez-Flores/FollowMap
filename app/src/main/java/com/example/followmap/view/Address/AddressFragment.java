package com.example.followmap.view.Address;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.followmap.databinding.FragmentAddressBinding;

public class AddressFragment extends Fragment {

    private FragmentAddressBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddressViewModel dashboardViewModel =
                new ViewModelProvider(this).get(AddressViewModel.class);

        binding = FragmentAddressBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textAddress;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}