package com.vazheninapps.gettingshedulefromweb.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;

import com.vazheninapps.gettingshedulefromweb.R;
import com.vazheninapps.gettingshedulefromweb.adapters.TimeTableItemAdapter;
import com.vazheninapps.gettingshedulefromweb.data.TimeTableItem;
import java.util.List;

public class TimeTableActivity extends AppCompatActivity {

    private TimeTableItemAdapter adapter;
    private RecyclerView recyclerViewTimeTable;
    private TimeTableViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        initItems();
        setTimeTable();
    }

    private void initItems() {
        //viewId
        recyclerViewTimeTable = findViewById(R.id.recyclerViewTimeTable);
        //set once
        adapter = new TimeTableItemAdapter();
        viewModel = new TimeTableViewModel(getApplication(), this);
        recyclerViewTimeTable.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerViewTimeTable.setAdapter(adapter);
        viewModel.getTimeTable().observe(this, new Observer<List<TimeTableItem>>() {
            @Override
            public void onChanged(List<TimeTableItem> timeTableItems) {
                adapter.setTimeTable(timeTableItems);
            }
        });
    }
    private void setTimeTable() {
       viewModel.loadData();
    }
    public void onClickRefreshTimeTable(View view) {
        setTimeTable();
    }
}
