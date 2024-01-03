package com.example.sqldb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context context;
    private List<MyDeatilModel> myDeatilModelList = new ArrayList<>();
    private Myselectclcikc myselectclcikc;
    private ArrayList<Integer> listOfItemsToDelete = new ArrayList<>(); // Declare here

    public DataAdapter(Context context, List<MyDeatilModel> myDeatilModelList, Myselectclcikc myselectclcikc) {
        this.context = context;
        this.myDeatilModelList = myDeatilModelList;
        this.myselectclcikc = myselectclcikc;
    }

    public void Updatelist(List<MyDeatilModel> myDeatilModelList) {
        this.myDeatilModelList = myDeatilModelList;
        listOfItemsToDelete.clear(); // Reset the list
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        MyDeatilModel model = myDeatilModelList.get(position);
        holder.fname.setText(myDeatilModelList.get(position).get_fname());
        holder.lname.setText(myDeatilModelList.get(position).get_lname());

        holder.checkbox.setOnCheckedChangeListener(null); // Remove listener temporarily

        // Set the checkbox state based on the list
        holder.checkbox.setChecked(listOfItemsToDelete.contains(model.get_id()));

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                myselectclcikc.onclickse(b, position, myDeatilModelList);
            }
        });
    }

    public interface Myselectclcikc {
        void onclickse(boolean b1, int position, List<MyDeatilModel> myDeatilModelList);
    }

    @Override
    public int getItemCount() {
        return myDeatilModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fname, lname;
        CheckBox checkbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            checkbox = itemView.findViewById(R.id.checkbox);
        }
    }
}
