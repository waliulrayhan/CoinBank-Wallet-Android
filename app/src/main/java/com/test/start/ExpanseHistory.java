package com.test.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExpanseHistory extends AppCompatActivity {

    private ListView listView2;
    DatabaseReference databaseReference2;
    private List<AddUserDetails> ExpanseDataList;
    private ExpanseHistoryCustomAdapter ExpanseHistoryCustom_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanse_history);


        getSupportActionBar().setTitle("Expense History");
        //Notification Color
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.TitleBarColor)));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        listView2 = findViewById(R.id.DetailsHistoryListView2);


        databaseReference2 = FirebaseDatabase.getInstance().getReference("Add Expanse User Data");
        ExpanseDataList = new ArrayList<>();
        ExpanseHistoryCustom_adapter = new ExpanseHistoryCustomAdapter(ExpanseHistory.this, ExpanseDataList);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onStart() {
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ExpanseDataList.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    AddUserDetails UserData = dataSnapshot1.getValue(AddUserDetails.class);
                    ExpanseDataList.add(UserData);
                }
                listView2.setAdapter(ExpanseHistoryCustom_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        super.onStart();
    }

}