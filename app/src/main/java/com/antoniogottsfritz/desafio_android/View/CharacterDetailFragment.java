package com.antoniogottsfritz.desafio_android.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.antoniogottsfritz.desafio_android.Model.MarvelCharacter;
import com.antoniogottsfritz.desafio_android.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class CharacterDetailFragment extends Fragment {
    private static final String ARG_CHARACTER_ID = "character";

    private MarvelCharacter _character;

    public CharacterDetailFragment() {
    }

    public static CharacterDetailFragment newInstance(MarvelCharacter character) {
        CharacterDetailFragment fragment = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHARACTER_ID, character);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            _character = (MarvelCharacter) getArguments().getSerializable(ARG_CHARACTER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.character_detail, container, false);

        ImageView heroChar = view.findViewById(R.id.heroChar);
        Glide.with(this)
                .load(_character.getThumbnail().getUrl("landscape_amazing"))
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(heroChar);

        TextView titleChar = view.findViewById(R.id.titleChar);
        titleChar.setText(_character.getName());

        TextView descChar = view.findViewById(R.id.descChar);
        descChar.setText(_character.getDescription());

        return view;
    }
}
