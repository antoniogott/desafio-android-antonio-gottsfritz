package com.antoniogottsfritz.desafio_android.Model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PositionalDataSource;

import com.antoniogottsfritz.desafio_android.Service.MarvelService;

public class CharacterDataSourceFactory extends DataSource.Factory<Integer, MarvelCharacter> {
    private MutableLiveData<PositionalDataSource<MarvelCharacter>> sourceLiveData = new MutableLiveData<>();
    private MarvelService _service;

    public CharacterDataSourceFactory() {
        super();
        _service = MarvelService.getInstance();
    }

    @NonNull
    @Override
    public DataSource<Integer, MarvelCharacter> create() {
        PositionalDataSource source = new CharacterDataSource(_service);
        sourceLiveData.postValue(source);
        return source;
    }

    public MutableLiveData<PositionalDataSource<MarvelCharacter>> getSourceLiveData() {
        return sourceLiveData;
    }
}
