package com.antoniogottsfritz.desafio_android.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antoniogottsfritz.desafio_android.R;
import com.antoniogottsfritz.desafio_android.ViewModel.CharacterVM;
import com.bumptech.glide.Glide;

public class CharacterListFragment extends Fragment {
    private CharacterVM viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_character_list, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(CharacterVM.class);
        setupRecyclerView(view);
        /*viewModel.getCharactersPagedList().observe(getViewLifecycleOwner(), characters -> {

        });*/
        return view;
    }

    private void setupRecyclerView(View view) {
        RecyclerView charactersRecycler = view.findViewById(R.id.charactersRecycler);
        charactersRecycler.setHasFixedSize(true);

        CharactersAdapter adapter = new CharactersAdapter(Glide.with(this));
        viewModel.charactersPagedList.observe(getViewLifecycleOwner(), adapter::submitList);
        adapter.setOnCharacterSelectedListener((character, thumbnail) -> {
            Navigation.findNavController(view)
                    .navigate(CharacterListFragmentDirections.actionCharDetail(character));
        });
        charactersRecycler.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        charactersRecycler.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(charactersRecycler.getContext(),
                layoutManager.getOrientation());
        charactersRecycler.addItemDecoration(divider);
    }
}
