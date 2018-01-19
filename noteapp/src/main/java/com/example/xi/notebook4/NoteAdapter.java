package com.example.xi.notebook4;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xi on 2017/11/22.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private Context mContext;

    private List<Note> mNoteList;

    private OnItemClickListener onItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView noteTitle;
        TextView noteText;
        ImageView imageView;
        TextView noteTime;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            noteTitle = (TextView)view.findViewById(R.id.note_title);
            noteText = (TextView)view.findViewById(R.id.note_text);
            imageView = (ImageView)view.findViewById(R.id.note_image);
            noteTime = (TextView)view.findViewById(R.id.note_time);
        }
    }

    public interface OnItemClickListener{
        void OnClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this.onItemClickListener=onItemClickListener;
    }

    public NoteAdapter(List<Note> noteList){
        mNoteList = noteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.note_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Note note = mNoteList.get(position);
        if(onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnClick(position);
                }
            });
        }
        holder.noteTitle.setText(note.getTitle());
        holder.noteText.setText(note.getText());
        holder.imageView.setImageResource(note.getImageId());
        holder.noteTime.setText(note.getTime());
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

}

