package com.antoniogottsfritz.desafio_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antoniogottsfritz.desafio_android.Model.Character;
import com.bumptech.glide.Glide;

class CharactersAdapter extends RecyclerView.Adapter {
    private Character[] characters;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_item, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Character character = characters[position];

        TextView nameChar = holder.itemView.findViewById((R.id.nameChar));
        nameChar.setText(character.getName());

        ImageView thumbChar = holder.itemView.findViewById(R.id.thumbChar);
        String imgUrl = character.getThumbnailUrl();
        Glide.with(holder.itemView).load(imgUrl).into(thumbChar);
    }

    @Override
    public int getItemCount() {
        return characters.length;
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {
        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
