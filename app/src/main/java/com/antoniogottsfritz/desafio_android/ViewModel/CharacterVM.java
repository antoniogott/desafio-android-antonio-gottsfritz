package com.antoniogottsfritz.desafio_android.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;

import com.antoniogottsfritz.desafio_android.Model.CharacterDataSourceFactory;
import com.antoniogottsfritz.desafio_android.Model.Comic;
import com.antoniogottsfritz.desafio_android.Model.MarvelCharacter;
import com.antoniogottsfritz.desafio_android.Service.MarvelService;

public class CharacterVM extends ViewModel {
    private static final int PAGE_SIZE = 20;

    public LiveData<PagedList<MarvelCharacter>> charactersPagedList;
    private MarvelService _service;
    private LiveData<PositionalDataSource<MarvelCharacter>> liveCharacterDataSource;

    public CharacterVM() {
        _service = MarvelService.getInstance();

        CharacterDataSourceFactory characterSourceFactory = new CharacterDataSourceFactory();
        liveCharacterDataSource = characterSourceFactory.getSourceLiveData();

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

    public LiveData<Comic> getMostExpensiveComic(int characterId) {
//        Comic mostExpensiveComic = MarvelService.getInstance().getMostExpensiveComic(characterId);
//        comic.postValue(mostExpensiveComic);
        return _service.getMostExpensiveComic(characterId);
    }
}
