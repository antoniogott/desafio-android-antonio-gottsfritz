package com.antoniogottsfritz.desafio_android.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.antoniogottsfritz.desafio_android.Model.MarvelCharacter;
import com.antoniogottsfritz.desafio_android.R;
import com.bumptech.glide.RequestManager;

class CharactersAdapter extends PagedListAdapter<MarvelCharacter, CharactersAdapter.CharacterViewHolder> {
    private final RequestManager glide;

    private onCharacterSelectedListener characterSelectedListener;

    private static DiffUtil.ItemCallback<MarvelCharacter> DiffCallback =
            new DiffUtil.ItemCallback<MarvelCharacter>() {
                @Override
                public boolean areItemsTheSame(MarvelCharacter oldItem, MarvelCharacter newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(MarvelCharacter oldItem, MarvelCharacter newItem) {
                    return oldItem.getId() == newItem.getId();
                }
            };

    CharactersAdapter(RequestManager glideReqMgr) {
        super(DiffCallback);
        glide = glideReqMgr;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_row, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        MarvelCharacter character = getItem(position);

        if (character != null) {
            holder.Character = character;

            holder.nameChar.setText(character.getName());

            ImageView thumbChar = holder.thumbChar;
            String imgUrl = character.getThumbnail().getUrl("standard_medium");
            glide.load(imgUrl)
                    .thumbnail(glide.load(R.drawable.loading))
                    .into(thumbChar);
        }
    }

    public void setOnCharacterSelectedListener(onCharacterSelectedListener listener) {
        this.characterSelectedListener = listener;
    }


    public interface onCharacterSelectedListener {
        void onCharacterSelected(MarvelCharacter character, ImageView thumbnail);
    }


    class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MarvelCharacter Character;
        TextView nameChar;
        ImageView thumbChar;

        CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            nameChar = itemView.findViewById((R.id.nameChar));
            thumbChar = itemView.findViewById(R.id.thumbChar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (characterSelectedListener != null)
                characterSelectedListener.onCharacterSelected(Character, thumbChar);
        }
    }
}
