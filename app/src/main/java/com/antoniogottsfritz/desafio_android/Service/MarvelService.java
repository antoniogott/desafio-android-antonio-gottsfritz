package com.antoniogottsfritz.desafio_android.Service;

import com.antoniogottsfritz.desafio_android.Model.MarvelCharacter;
import com.antoniogottsfritz.desafio_android.Model.MarvelImage;

import java.util.ArrayList;
import java.util.List;

public class MarvelService {

    public List<MarvelCharacter> getCharacters() {
        ArrayList<MarvelCharacter> list = new ArrayList<>();

        MarvelCharacter ch = new MarvelCharacter();
        ch.setName("Abyss");
        ch.setId(123);
        MarvelImage img = new MarvelImage();
        img.setExtension("jpg");
        img.setPath("http://i.annihil.us/u/prod/marvel/i/mg/9/30/535feab462a64");
        ch.setThumbnail(img);
        list.add(ch);

        MarvelCharacter ch2 = new MarvelCharacter();
        ch2.setName("3-D Man");
        ch2.setId(8957);
        MarvelImage img2 = new MarvelImage();
        img2.setExtension("jpg");
        img2.setPath("http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784");
        ch2.setThumbnail(img2);
        list.add(ch2);

        MarvelCharacter ch3 = new MarvelCharacter();
        ch3.setName("A-Bomb (HAS)");
        ch3.setId(6516);
        ch3.setDescription("Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ");
        MarvelImage img3 = new MarvelImage();
        img3.setExtension("jpg");
        img3.setPath("http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16");
        ch3.setThumbnail(img3);
        list.add(ch3);

//        list.add(ch);
//        list.add(ch2);
//        list.add(ch3);
//
//        list.add(ch);
//        list.add(ch2);
//        list.add(ch3);
//
//        list.add(ch);
//        list.add(ch2);
//        list.add(ch3);
//
//        list.add(ch);
//        list.add(ch2);
//        list.add(ch3);
//
//        list.add(ch);
//        list.add(ch2);
//        list.add(ch3);
//
//        list.add(ch);
//        list.add(ch2);
//        list.add(ch3);


        return list;
    }
}
