package com.antoniogottsfritz.desafio_android.Service;

import com.antoniogottsfritz.desafio_android.Model.Character;
import com.antoniogottsfritz.desafio_android.Model.MarvelImage;

import java.util.ArrayList;
import java.util.List;

public class MarvelService {

    public List<Character> getCharacters() {
        ArrayList<Character> list = new ArrayList<>();

        Character ch = new Character();
        ch.setName("Abyss");
        MarvelImage img = new MarvelImage();
        img.setExtension("jpg");
        img.setPath("http://i.annihil.us/u/prod/marvel/i/mg/9/30/535feab462a64");
        ch.setThumbnail(img);
        list.add(ch);

        Character ch2 = new Character();
        ch2.setName("Abyss 2");
        MarvelImage img2 = new MarvelImage();
        img2.setExtension("jpg");
        img2.setPath("http://i.annihil.us/u/prod/marvel/i/mg/9/30/535feab462a64");
        ch2.setThumbnail(img2);
        list.add(ch2);


        return list;
    }
}
