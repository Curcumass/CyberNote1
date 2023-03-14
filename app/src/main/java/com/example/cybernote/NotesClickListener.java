package com.example.cybernote;

import androidx.cardview.widget.CardView;

import com.example.cybernote.Models.Notes;

public interface NotesClickListener {

    void OnClick(Notes notes);
    void OnLongClick(Notes notes, CardView cardView);


}
