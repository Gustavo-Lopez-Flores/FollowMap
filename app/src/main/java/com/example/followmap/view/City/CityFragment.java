package com.example.followmap.view.City;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.followmap.databinding.FragmentCityBinding;

public class CityFragment extends Fragment {

    private FragmentCityBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CityViewModel homeViewModel =
                new ViewModelProvider(this).get(CityViewModel.class);

        binding = FragmentCityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCity;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}