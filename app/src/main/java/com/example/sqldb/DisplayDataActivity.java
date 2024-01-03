package com.example.sqldb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sqldb.DB.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class DisplayDataActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    ArrayList<Integer> listOfItemsToDelete = new ArrayList<>();
    Button delete;
    MyDatabase myDatabase;
    DataAdapter adapter;
    List<MyDeatilModel> myDeatilModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        recyclerview = findViewById(R.id.recyclerview);
        delete = findViewById(R.id.delete);

        myDatabase = new MyDatabase(DisplayDataActivity.this);
        myDeatilModelList = myDatabase.getAllDetail();

        adapter = new DataAdapter(DisplayDataActivity.this, myDeatilModelList, new DataAdapter.Myselectclcikc() {
            @Override
            public void onclickse(boolean b1, int position, List<MyDeatilModel> myDeatilModelList) {
                int itemid = myDeatilModelList.get(position).get_id();

                if (b1) {
                    if (!listOfItemsToDelete.contains(itemid)) {
                        listOfItemsToDelete.add(myDeatilModelList.get(position).get_id());
                        Log.e("HELLO", "true");
                    }
                } else {
                    listOfItemsToDelete.remove(Integer.valueOf(itemid));
                    Log.e("HELLO", "false");
                }
            }
        });
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);

        if (myDeatilModelList.size() > 0) {
            delete.setVisibility(View.VISIBLE);
            delteclick();
        } else {
            delete.setVisibility(View.INVISIBLE);
        }
    }

    private void delteclick() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Integer itemId : listOfItemsToDelete) {
                    myDatabase.deleteContact(itemId);
                }
                listOfItemsToDelete.clear();
                List<MyDeatilModel> updatedList = new ArrayList<>(myDatabase.getAllDetail());
                adapter.Updatelist(updatedList);
            }
        });
    }

}
