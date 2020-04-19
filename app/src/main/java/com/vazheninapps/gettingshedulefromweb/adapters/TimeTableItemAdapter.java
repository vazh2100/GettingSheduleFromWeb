package com.vazheninapps.gettingshedulefromweb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vazheninapps.gettingshedulefromweb.R;
import com.vazheninapps.gettingshedulefromweb.data.TimeTableItem;

import java.util.ArrayList;
import java.util.List;

public class TimeTableItemAdapter extends RecyclerView.Adapter<TimeTableItemAdapter.TimeTableItemViewHolder> {

    private List<TimeTableItem> timeTable;


    public TimeTableItemAdapter() {
        timeTable = new ArrayList<>();
    }

    @NonNull
    @Override
    public TimeTableItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_item, parent, false);
        return new TimeTableItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableItemViewHolder holder, int position) {
        TimeTableItem timeTableItem = timeTable.get(position);
        String url = timeTableItem.getTeacherImageUrl();
        Picasso.get().load(url).placeholder(android.R.drawable.ic_menu_gallery).into(holder.imageViewTeacher);
        holder.textViewTitle.setText(timeTableItem.getTitle());
        holder.textViewDayOfWeek.setText(dayOfWeekAsText(timeTableItem.getWeekDay()));
        holder.textViewTime.setText(String.format("%s - %s", timeTableItem.getTimeStart(), timeTableItem.getTimeEnd()));
        holder.textViewPlace.setText(timeTableItem.getPlace());
        holder.textViewTeacherName.setText(timeTableItem.getTeacherName());
        holder.textViewTeacherPosition.setText(timeTableItem.getTeacherPosition());
        holder.textViewDescription.setText(timeTableItem.getDescription());

    }

    @Override
    public int getItemCount() {
        return timeTable.size();
    }

    public void setTimeTable(List<TimeTableItem> timeTable) {
        this.timeTable = timeTable;
        notifyDataSetChanged();
    }


    private String dayOfWeekAsText(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return "Понедельник";
            case 2:
                return "Вторник";
            case 3:
                return "Среда";
            case 4:
                return "Четверг";
            case 5:
                return "Пятница";
            case 6:
                return "Суббота";
            case 7:
                return "Воскресенье";
            default:
                return "На сервере указали несуществующий день недели";
        }
    }


    static class TimeTableItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDayOfWeek;
        private TextView textViewTime;
        private TextView textViewPlace;
        private TextView textViewTeacherName;
        private TextView textViewTeacherPosition;
        private TextView textViewDescription;
        private ImageView imageViewTeacher;


        private TimeTableItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewPlace = itemView.findViewById(R.id.textViewPlace);
            textViewTeacherName = itemView.findViewById(R.id.textViewTeacherName);
            textViewTeacherPosition = itemView.findViewById(R.id.textViewTeacherPosition);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageViewTeacher = itemView.findViewById(R.id.imageViewTeacher);
        }
    }
}
