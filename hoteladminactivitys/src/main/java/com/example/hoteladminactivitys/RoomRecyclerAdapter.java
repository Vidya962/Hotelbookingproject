package com.example.hoteladminactivitys;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class RoomRecyclerAdapter extends  RecyclerView.Adapter<RoomRecyclerAdapter.ViewHolder>{



private  android.content.Context context;
    private  ArrayList<UserRoomClass> UsersItemArrayList;

    private  DatabaseReference reference;



    public RoomRecyclerAdapter(android.content.Context context, ArrayList<UserRoomClass> usersItemArrayList, DatabaseReference reference) {
        this.context = context;
        UsersItemArrayList = usersItemArrayList;
        this.reference = reference;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.user_item,parent,false);
       // LayoutInflater layoutInflater= LayoutInflater.from(context);
       // View view=layoutInflater.inflate(R.layout.,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserRoomClass users=UsersItemArrayList.get(position);

        holder.Room_Type.setText(users.getRoom_type());
        holder.Cost.setText(users.getCost());
        holder.Discount.setText(users.getDiscount());

        holder.buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewDialogUpdate viewDialogUpdate=new ViewDialogUpdate();
                Toast.makeText(context, users.getRoomkey(), Toast.LENGTH_SHORT).show();
                viewDialogUpdate.showDialog(context, users.getRoomkey(), users.getRoom_type(), users.getCost(), users.getDiscount());

            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialogConfirmDelete viewDialogConfirmDelete=new ViewDialogConfirmDelete();
                viewDialogConfirmDelete.showDialog(context,users.getRoomkey());

            }
        });

    }

    @Override
    public int getItemCount() {

        return UsersItemArrayList.size();


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView Room_Type;
        TextView Cost;
        TextView Discount;

        Button buttonDelete,buttonupdate;
        

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           Room_Type=itemView.findViewById(R.id.roomtypes);
            Cost=itemView.findViewById(R.id.roomcosts);
            Discount=itemView.findViewById(R.id.discountss);
            buttonDelete=itemView.findViewById(R.id.buttonDelete);
            buttonupdate=itemView.findViewById(R.id.buttonUpdate);
            
            //

            
            //
        }
    }


    public class ViewDialogUpdate{
        public void showDialog(android.content.Context context,String id,String room_type2,String room_cost2,String room_discount2)
        {

            final Dialog dialog=new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.room_dialog_update);

            EditText textroom=dialog.findViewById(R.id.text_room2);
            EditText textcost=dialog.findViewById(R.id.text_cost2);
            EditText textdiscount=dialog.findViewById(R.id.text_discount2);

            textroom.setText(room_type2);
            textcost.setText(room_cost2);
            textdiscount.setText(room_discount2);



            Button buttonupdate=dialog.findViewById(R.id.buttonUpdate);
            Button buttonCancel=dialog.findViewById(R.id.buttonCancel);



            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


            buttonupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String newroom=textroom.getText().toString();
                    String newcost=textcost.getText().toString();
                    String newdiscount=textdiscount.getText().toString();


                    if(newroom.isEmpty()|| newcost.isEmpty()||newdiscount.isEmpty()){
                        Toast.makeText(context, "please enter all data", Toast.LENGTH_SHORT).show();
                    }
                    else if(newroom.equals(room_type2)&& newcost.equals(room_cost2)&& newdiscount.equals(room_discount2)){
                        Toast.makeText(context, "You Don't change anything", Toast.LENGTH_SHORT).show();
                    }else{

                       reference.getRef().child(id).setValue(new UserRoomClass(newroom,newcost,newdiscount)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "User Update successfully", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "cannot update data", Toast.LENGTH_SHORT).show();

                            }
                        });



                    }


                }


            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();


        }
    }


    public class ViewDialogConfirmDelete{
        public void showDialog(android.content.Context context,String id)
        {

            final Dialog dialog=new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.room_dialog_delete);


            Button buttonDelete=dialog.findViewById(R.id.buttonDelete);
            Button buttonCancel=dialog.findViewById(R.id.buttonCancel);



            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                  //  reference.getRef().child("rooms").child(id).removeValue();
                    reference.getRef().child(id).removeValue();
                    Toast.makeText(context, "User Deleted successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();





                }


            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();


        }
    }
}
