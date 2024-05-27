package com.example.followmap.view.Address;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.followmap.database.LocalDatabase;
import com.example.followmap.entities.Cidade;
import com.example.followmap.entities.Endereco;
import com.example.followmap.entities.EnderecoComCidade;

import java.util.List;

public class AddressViewModel extends AndroidViewModel {

    private final MutableLiveData<List<EnderecoComCidade>> enderecos;
    private final LocalDatabase db;

    public AddressViewModel(Application application) {
        super(application);
        db = LocalDatabase.getDatabase(application);
        enderecos = new MutableLiveData<>();
        carregarEnderecos();
    }

    private void carregarEnderecos() {
        List<EnderecoComCidade> addressList = db.enderecoDao().getEnderecosComCidade();
        enderecos.setValue(addressList);
    }

    public LiveData<List<EnderecoComCidade>> getEnderecos() {
        return enderecos;
    }
}