package com.example.followmap.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "enderecos",
        foreignKeys = @ForeignKey(entity = Cidade.class,
                parentColumns = "cidadeId",
                childColumns = "cidadeId",
                onDelete = ForeignKey.CASCADE))
public class Endereco {

    @PrimaryKey(autoGenerate = true)
    private int enderecoId;
    private String descricao;
    private double latitude;
    private double longitude;
    private int cidadeId;
    @Ignore
    private String cidadeNome;

    public Endereco(String descricao, double latitude, double longitude, int cidadeId) {
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cidadeId = cidadeId;
    }

    public String getCidadeNome() {
        return cidadeNome;
    }

    public void setCidadeNome(String cidadeNome) {
        this.cidadeNome = cidadeNome;
    }

    public int getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(int cidadeId) {
        this.cidadeId = cidadeId;
    }

    @Override
    public String toString() {
        return descricao + " - " + getCidadeNome();
    }
}