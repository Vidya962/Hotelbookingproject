package com.example.hoteladminactivitys;

import android.app.Dialog;
import android.content.Context;
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

public class ExtraRecyclerAdapter extends RecyclerView.Adapter<ExtraRecyclerAdapter.ViewHolder>{

    Context context;
    ArrayList<UserExtraClass> extraItemsArrayList;

    DatabaseReference reference;

    public ExtraRecyclerAdapter(Context context, ArrayList<UserExtraClass> extraItemsArrayList, DatabaseReference reference) {
        this.context = context;
        this.extraItemsArrayList = extraItemsArrayList;
        this.reference = reference;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.extra_item,parent,false);
        // LayoutInflater layoutInflater= LayoutInflater.from(context);
        // View view=layoutInflater.inflate(R.layout.,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserExtraClass extras=extraItemsArrayList.get(position);

        holder.extra_facility.setText(extras.getFacility());
        holder.extra_cost.setText(extras.getCost());


        holder.buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               ViewDialogUpdatee viewDialogUpdatee=new ViewDialogUpdatee();
                Toast.makeText(context, extras.getExtrakey(), Toast.LENGTH_SHORT).show();
                viewDialogUpdatee.showDialog(context, extras.getExtrakey(), extras.getFacility(), extras.getCost());

            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialogConfirmDeletee viewDialogConfirmDeletee=new ViewDialogConfirmDeletee();
                viewDialogConfirmDeletee.showDialog(context, extras.getExtrakey());

            }
        });



    }

    @Override
    public int getItemCount() {
        return extraItemsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView extra_facility;
        TextView extra_cost;

        Button buttonDelete,buttonupdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            extra_facility=itemView.findViewById(R.id.facilityid);
           extra_cost=itemView.findViewById(R.id.costid);
            buttonDelete=itemView.findViewById(R.id.buttonDelete);
            buttonupdate=itemView.findViewById(R.id.buttonUpdate);
        }
    }
    public class ViewDialogUpdatee{
        public void showDialog(Context context, String id, String extra_facility2, String extra_cost2)
        {

            final Dialog dialog=new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.extra_dialog_update);

            EditText textextra=dialog.findViewById(R.id.extra2);
            EditText textcost=dialog.findViewById(R.id.cost2);


            textextra.setText(extra_facility2);
            textcost.setText(extra_cost2);




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

                    String newextra=textextra.getText().toString();
                    String newcost=textcost.getText().toString();



                    if(newextra.isEmpty()|| newcost.isEmpty()){
                        Toast.makeText(context, "please enter all data", Toast.LENGTH_SHORT).show();
                    }
                    else if(newextra.equals(extra_facility2)&& newcost.equals(extra_cost2)){
                        Toast.makeText(context, "You Don't change anything", Toast.LENGTH_SHORT).show();
                    }else{

                        reference.getRef().child("extras").child(id).setValue(new UserExtraClass(newextra,newcost)).addOnSuccessListener(new OnSuccessListener<Void>() {
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


    public class ViewDialogConfirmDeletee{
        public void showDialog(Context context, String id)
        {

            final Dialog dialog=new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.extra_dialog_delete);


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



                    reference.getRef().child("extras").child(id).removeValue();
                    Toast.makeText(context, "User Deleted successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();





                }


            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();


        }
    }
}
