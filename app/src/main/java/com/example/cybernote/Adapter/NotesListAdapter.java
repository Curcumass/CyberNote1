package com.example.cybernote.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cybernote.Models.Notes;
import com.example.cybernote.NotesClickListener;
import com.example.cybernote.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter <NotesViewFolder>{

    Context context;
    List<Notes> list;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewFolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewFolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewFolder holder, int position) {
        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_title.setSelected(true);

        holder.textView_notes.setText(list.get(position).getNotes());

        holder.textView_date.setText(list.get(position).getDate());
        holder.textView_date.setSelected(true);

        if(list.get(position).isPinned()){
            holder.imageView_pin.setImageResource(R.drawable.pin_icon);
        }
        else{
            holder.imageView_pin.setImageResource(0);
        }

        int colorCode = GetRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(colorCode, null));

        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.OnLongClick(list.get(holder.getAdapterPosition()), holder.notes_container);
                return true;
            }
        });
    }

    private int GetRandomColor(){
        List<Integer> colorCode = new ArrayList<>();

        colorCode.add(R.color.color4);

        Random random = new Random();
        int randomColor = random.nextInt(colorCode.size());
        return colorCode.get(randomColor);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<Notes> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }
}

class NotesViewFolder extends RecyclerView.ViewHolder {

    CardView notes_container;
    TextView textView_title;
    ImageView imageView_pin;
    TextView textView_notes;
    TextView textView_date;


    public NotesViewFolder(@NonNull View itemView) {
        super(itemView);
        notes_container = itemView.findViewById(R.id.notes_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        imageView_pin = itemView.findViewById(R.id.imageView_pin);
        textView_notes = itemView.findViewById(R.id.textView_notes);
        textView_date = itemView.findViewById(R.id.textView_date);
    }
}