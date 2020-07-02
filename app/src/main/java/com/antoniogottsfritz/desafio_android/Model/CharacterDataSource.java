package com.antoniogottsfritz.desafio_android.Model;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.antoniogottsfritz.desafio_android.Model.API.ResultContainer;
import com.antoniogottsfritz.desafio_android.Service.MarvelService;

public class CharacterDataSource extends PositionalDataSource<MarvelCharacter> {
    private MarvelService _service;

    public CharacterDataSource(MarvelService service) {
        super();
        this._service = service;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<MarvelCharacter> callback) {
        int offset = params.requestedStartPosition;
        int limit = params.requestedLoadSize;
        ResultContainer<MarvelCharacter> container = _service.getCharacters(offset, limit);
        callback.onResult(container.getResults(), container.getOffset(), container.getTotal());
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<MarvelCharacter> callback) {
        int offset = params.startPosition;
        int limit = params.loadSize;
        ResultContainer<MarvelCharacter> container = _service.getCharacters(offset, limit);
        callback.onResult(container.getResults());
    }
}
