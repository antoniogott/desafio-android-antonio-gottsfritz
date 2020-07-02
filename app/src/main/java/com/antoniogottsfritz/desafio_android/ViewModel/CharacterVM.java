package com.antoniogottsfritz.desafio_android.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;

import com.antoniogottsfritz.desafio_android.Model.CharacterDataSourceFactory;
import com.antoniogottsfritz.desafio_android.Model.MarvelCharacter;

public class CharacterVM extends ViewModel {
    private static final int PAGE_SIZE = 20;

    public LiveData<PagedList<MarvelCharacter>> charactersPagedList;
    LiveData<PositionalDataSource<MarvelCharacter>> liveDataSource;

    public CharacterVM() {
        CharacterDataSourceFactory characterSourceFactory = new CharacterDataSourceFactory();
        liveDataSource = characterSourceFactory.getSourceLiveData();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(20)
                        .setPageSize(PAGE_SIZE)
                        .build();

        //Building the paged list
        charactersPagedList = (new LivePagedListBuilder<>(characterSourceFactory, pagedListConfig))
                .build();
    }

    /*private List<MarvelCharacter> fetchCharacters() {
        List<MarvelCharacter> characters = marvelService.getCharacters();
        return characters;
    }*/
}
