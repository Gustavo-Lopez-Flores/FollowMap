package com.example.followmap.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

public class EnderecoComCidade {
    @Embedded
    public Endereco endereco;

    @Relation(
            parentColumn = "cidadeId",
            entityColumn = "cidadeId"
    )
    public Cidade cidade;
}
