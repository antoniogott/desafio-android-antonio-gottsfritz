package com.antoniogottsfritz.desafio_android.View;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antoniogottsfritz.desafio_android.Model.Character;
import com.antoniogottsfritz.desafio_android.R;
import com.bumptech.glide.RequestManager;

import java.net.URI;
import java.util.List;

class CharactersAdapter extends RecyclerView.Adapter {
    private List<Character> characters;
    private final RequestManager glide;

    private onCharacterSelectedListener characterSelectedListener;

    public CharactersAdapter(List<Character> characters, RequestManager glideReqMgr) {
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
        Character character = characters.get(position);

        if (holder instanceof CharacterViewHolder) {
            ((CharacterViewHolder) holder).CharacterId = character.getId();

            TextView nameChar = holder.itemView.findViewById((R.id.nameChar));
            nameChar.setText(character.getName());

            ImageView thumbChar = holder.itemView.findViewById(R.id.thumbChar);
            String imgUrl = character.getThumbnailUrl("landscape_medium");
            glide.load(imgUrl).into(thumbChar);
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
        public void onCharacterSelected(int id);
    }


    class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int CharacterId;
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
            int id = CharacterId;

        }
    }
}
