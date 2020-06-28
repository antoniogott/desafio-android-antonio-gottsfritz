package com.antoniogottsfritz.desafio_android;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class CharacterListFragment extends Fragment {
    private RecyclerView charactersRecycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public CharacterListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_list, container, false);

        charactersRecycler = view.findViewById(R.id.charactersRecycler);
        charactersRecycler.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        charactersRecycler.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new CharactersAdapter(myDataset);
        charactersRecycler.setAdapter(adapter);

        return view;
    }
}
