package com.antoniogottsfritz.desafio_android.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antoniogottsfritz.desafio_android.Model.MarvelCharacter;
import com.antoniogottsfritz.desafio_android.R;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

class CharactersAdapter extends RecyclerView.Adapter {
    private List<MarvelCharacter> characters;
    private final RequestManager glide;

    private onCharacterSelectedListener characterSelectedListener;

    public CharactersAdapter(List<MarvelCharacter> characters, RequestManager glideReqMgr) {
        this.characters = characters;
        glide = glideReqMgr;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_item, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MarvelCharacter character = characters.get(position);

        if (holder instanceof CharacterViewHolder) {
            ((CharacterViewHolder) holder).Character = character;

            TextView nameChar = holder.itemView.findViewById((R.id.nameChar));
            nameChar.setText(character.getName());

            ImageView thumbChar = holder.itemView.findViewById(R.id.thumbChar);
            String imgUrl = character.getThumbnail().getUrl("landscape_medium");
            glide.load(imgUrl).into(thumbChar);
            glide.downloadOnly().diskCacheStrategy(DiskCacheStrategy.DATA).load(character.getThumbnail().getUrl("landscape_amazing")).submit();
        }
    }

    @Override
    public int getItemCount() {
        return characters.size();
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
