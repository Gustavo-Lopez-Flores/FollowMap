package com.example.followmap.view.City;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.followmap.database.LocalDatabase;
import com.example.followmap.entities.Cidade;

import java.util.List;

public class CityViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Cidade>> cidades;
    private final LocalDatabase db;

    public CityViewModel(Application application) {
        super(application);
        db = LocalDatabase.getDatabase(application);
        cidades = new MutableLiveData<>();
        carregarCidades();
    }

    private void carregarCidades() {
        List<Cidade> cityList = db.cidadeDao().getAllCidades();
        cidades.setValue(cityList);
    }

    public LiveData<List<Cidade>> getCidades() {
        return cidades;
    }
}