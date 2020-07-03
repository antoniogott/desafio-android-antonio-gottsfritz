package com.antoniogottsfritz.desafio_android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.antoniogottsfritz.desafio_android.Model.MarvelImage;
import com.antoniogottsfritz.desafio_android.View.CharacterDetailFragment;
import com.antoniogottsfritz.desafio_android.ViewModel.CharacterVM;
import com.bumptech.glide.Glide;


public class ComicDetailFragment extends Fragment {
    private static final String ARG_CHARACTER_ID = "characterId";
    private CharacterVM viewModel;
    private int _characterId;

    public ComicDetailFragment() {
    }

    public static CharacterDetailFragment newInstance(int characterId) {
        CharacterDetailFragment fragment = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CHARACTER_ID, characterId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            _characterId = getArguments().getInt(ARG_CHARACTER_ID);
        }

        viewModel = new ViewModelProvider(requireActivity()).get(CharacterVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comic_detail, container, false);

        ImageView heroComic = view.findViewById(R.id.heroComic);
        Glide.with(this)
                .load(R.drawable.loading)
                .into(heroComic);

        viewModel.getMostExpensiveComic(_characterId).observe(getViewLifecycleOwner(), comic -> {
            if (comic != null) {
                MarvelImage thumbnail = comic.getThumbnail();
                Glide.with(this)
                        .load(thumbnail.getUrl("original"))
                        .thumbnail(Glide.with(view)
                                .load(thumbnail.getUrl("portrait_small")))
                        .into(heroComic);

                TextView titleComic = view.findViewById(R.id.titleComic);
                titleComic.setText(comic.getTitle());

                TextView descComic = view.findViewById(R.id.descComic);
                descComic.setText(comic.getDescription());

                TextView priceComic = view.findViewById(R.id.priceComic);
                priceComic.setText(getString(R.string.comicPriceDisplay, comic.getHighestPrice()));
            } else Log.e("comic error", "comic is null");
        });

        return view;
    }
}
