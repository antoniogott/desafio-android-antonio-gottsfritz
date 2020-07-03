package com.antoniogottsfritz.desafio_android.View;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.antoniogottsfritz.desafio_android.Model.MarvelCharacter;
import com.antoniogottsfritz.desafio_android.R;
import com.antoniogottsfritz.desafio_android.ViewModel.CharacterVM;
import com.bumptech.glide.Glide;

public class CharacterDetailFragment extends Fragment {
    private static final String ARG_CHARACTER = "character";
    private CharacterVM viewModel;
    private MarvelCharacter _character;

    public CharacterDetailFragment() {
    }

    public static CharacterDetailFragment newInstance(MarvelCharacter character) {
        CharacterDetailFragment fragment = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHARACTER, character);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            _character = (MarvelCharacter) getArguments().getSerializable(ARG_CHARACTER);
        }

        viewModel = new ViewModelProvider(requireActivity()).get(CharacterVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.character_detail, container, false);

        Button btn = view.findViewById(R.id.btn);

        ImageView heroChar = view.findViewById(R.id.heroChar);
        Glide.with(this)
                .load(_character.getThumbnail().getUrl("original"))
                .thumbnail(Glide.with(this)
                        .load(_character.getThumbnail().getUrl("standard_medium")))
                .into(heroChar);

        TextView titleChar = view.findViewById(R.id.titleChar);
        titleChar.setText(_character.getName());

        TextView descChar = view.findViewById(R.id.descChar);
        descChar.setText(_character.getDescription());

        if (_character.getComicList().getAvailable() > 0) {
            btn.setOnClickListener(v -> {
                Navigation.findNavController(view)
                        .navigate(CharacterDetailFragmentDirections
                                .actionComicDetail(_character.getId()));
            });
        } else {
            btn.setEnabled(false);
            btn.setText(getString(R.string.noComicMessage));
            btn.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getContext().getResources().getDimension(R.dimen.comic_button_small));
        }

        return view;
    }
}
