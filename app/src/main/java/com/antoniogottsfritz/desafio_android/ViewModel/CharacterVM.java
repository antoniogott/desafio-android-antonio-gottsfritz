package com.antoniogottsfritz.desafio_android.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.antoniogottsfritz.desafio_android.Model.MarvelCharacter;
import com.antoniogottsfritz.desafio_android.Service.MarvelService;

import java.util.List;

public class CharacterVM extends ViewModel {
    private MarvelService marvelService;

    private MutableLiveData<List<MarvelCharacter>> characters;

    public LiveData<List<MarvelCharacter>> getCharacters() {
        if (characters == null) {
            characters = new MutableLiveData<>();
            characters.postValue(fetchCharacters());
        }
        return characters;
    }

    public CharacterVM() {
        marvelService = new MarvelService();
    }

    private List<MarvelCharacter> fetchCharacters() {
        List<MarvelCharacter> characters = marvelService.getCharacters();
        return characters;
    }
}
