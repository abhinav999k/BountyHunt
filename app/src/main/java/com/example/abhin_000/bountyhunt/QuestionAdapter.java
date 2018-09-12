package com.example.abhin_000.bountyhunt;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.vHolder> {

    List<QuestionFormat> data;
    Context context;

    public QuestionAdapter(List<QuestionFormat> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public vHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.question_item, parent, false);
        return new vHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vHolder holder, final int position) {
         holder.textViewScore.setText(Integer.toString(data.get(position).getScore()));
         String name = data.get(position).getAskedID();
         holder.textViewName.setText(name);
         holder.textViewQuestion.setText(data.get(position).getQuestion());
         holder.textViewQuestion.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(context, AnswerQuestion.class);
                 intent.putExtra("QuestionID",data.get(position).getQID());
                 context.startActivity(intent);
             }
         });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class vHolder extends RecyclerView.ViewHolder{
        TextView textViewScore;
        TextView textViewName;
        TextView textViewQuestion;
        public vHolder(View itemView) {
            super(itemView);
            textViewScore = itemView.findViewById(R.id.questionScore);
            textViewName = itemView.findViewById(R.id.PersonName);
            textViewQuestion = itemView.findViewById(R.id.Question);
        }
    }

}
