package com.example.followmap.view.Address;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.followmap.database.LocalDatabase;
import com.example.followmap.entities.Cidade;
import com.example.followmap.entities.Endereco;

import java.util.List;

public class AddressViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Endereco>> enderecos;
    private final LocalDatabase db;

    public AddressViewModel(Application application) {
        super(application);
        db = LocalDatabase.getDatabase(application);
        enderecos = new MutableLiveData<>();
        carregarEnderecos();
    }

    private void carregarEnderecos() {
        List<Endereco> addressList = db.enderecoDao().getAllEnderecos();
        enderecos.setValue(addressList);
    }

    public LiveData<List<Endereco>> getEnderecos() {
        return enderecos;
    }
}