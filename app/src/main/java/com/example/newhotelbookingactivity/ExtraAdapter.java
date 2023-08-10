package com.example.newhotelbookingactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ExtraAdapter extends RecyclerView.Adapter<ExtraAdapter.UserViewHolder> {


    private List<UserExtraClass> list1;

    //  private RoomsFragment fragment;

    private SumChangeListener sumChangeListener;


    private Context context;

    public ExtraAdapter(Context context,List<UserExtraClass> list1,SumChangeListener sumChangeListener) {
        this.list1 = list1;

        //  this.fragment = fragment;
        this.sumChangeListener=sumChangeListener;
        this.context=context;


        //  this.sumChangeListener = sumChangeListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.extra_row_items,parent,false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserExtraClass item = list1.get(position);
        holder.check_room.setText(item.getFacility());
        holder.check_room.setChecked(item.isSelected());
        holder.valuetextview.setText(String.valueOf(item.getCost()));

        // holder.check_room.setOnCheckedChangeListener((buttonView, isChecked) -> {
        //   item.setSelected(isChecked);
        //   fragment.onSumChanged(String.valueOf(calculateSum()));
        //   });
        holder.check_room.setOnCheckedChangeListener((buttonView, isChecked) ->{
            item.setSelected(isChecked);
            sumChangeListener.onSumChanged(Integer.parseInt(calculateSum()));
        } );
    }

    private String calculateSum() {
        int sum1=0;
        for (UserExtraClass item : list1) {
            if (item.isSelected()) {
                sum1 += Integer.parseInt(item.getCost());

            }
        }
        return String.valueOf(sum1);

    }


    @Override
    public int getItemCount() {
        return list1.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public CheckBox check_room;
        public TextView valuetextview;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            valuetextview=itemView.findViewById(R.id.txt_cost);
            check_room=itemView.findViewById(R.id.check_room);
        }
    }

    public interface SumChangeListener {
        void onSumChanged(int sum1);
    }
}


