package com.example.hoteladminactivitys;

import android.annotation.SuppressLint;
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

public class PromoRecyclerAdapter extends RecyclerView.Adapter<PromoRecyclerAdapter.ViewHolder>{

    Context context;
  ArrayList<UserPromoClass> UsersItemArrayList;

    DatabaseReference reference;

    public PromoRecyclerAdapter(Context context, ArrayList<UserPromoClass> usersItemArrayList, DatabaseReference reference) {
        this.context = context;
        UsersItemArrayList = usersItemArrayList;
        this.reference = reference;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.promo_items,parent,false);
        // LayoutInflater layoutInflater= LayoutInflater.from(context);
        // View view=layoutInflater.inflate(R.layout.,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        UserPromoClass promos=UsersItemArrayList.get(position);
        String key = promos.getPromokey();

        holder.promo_code.setText(promos.getPromocode());
        holder.promo_discount.setText(promos.getDiscount());

        holder.buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               ViewDialogUpdates viewDialogUpdates=new ViewDialogUpdates();
                Toast.makeText(context, promos.getPromokey(), Toast.LENGTH_SHORT).show();
                viewDialogUpdates.showDialog(context, promos.getPromokey(), promos.getPromocode(), promos.getDiscount());

            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialogConfirmDeletes viewDialogConfirmDeletes=new ViewDialogConfirmDeletes();
                viewDialogConfirmDeletes.showDialog(context,promos.getPromokey());

            }
        });




    }

    @Override
    public int getItemCount() {
        return UsersItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView promo_code;
        TextView promo_discount;

        Button buttonDelete,buttonupdate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            promo_code=itemView.findViewById(R.id.promocodeid);
           promo_discount=itemView.findViewById(R.id.promodiscountid);
            buttonDelete=itemView.findViewById(R.id.buttonDelete);
            buttonupdate=itemView.findViewById(R.id.buttonUpdate);

        }
    }


public class ViewDialogUpdates{
    public void showDialog(Context context, String id, String promo_code2, String promo_discount2)
    {

        final Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.promo_dialog_update);

        EditText textpromo=dialog.findViewById(R.id.promocode2);
        EditText textDiscount=dialog.findViewById(R.id.Discount2);


        textpromo.setText(promo_code2);
        textDiscount.setText(promo_discount2);




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

                String newcode=textpromo.getText().toString();
                String newDiscount=textDiscount.getText().toString();



                if(newcode.isEmpty()|| newDiscount.isEmpty()){
                    Toast.makeText(context, "please enter all data", Toast.LENGTH_SHORT).show();
                }
                else if(newcode.equals(promo_code2)&& newDiscount.equals(promo_discount2)){
                    Toast.makeText(context, "You Don't change anything", Toast.LENGTH_SHORT).show();
                }else{

                    reference.getRef().child("promos").child(id).setValue(new UserPromoClass(newcode,newDiscount)).addOnSuccessListener(new OnSuccessListener<Void>() {
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


public class ViewDialogConfirmDeletes{
    public void showDialog(Context context, String id)
    {

        final Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.promo_dialog_delete);


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



                reference.getRef().child("promos").child(id).removeValue();
                Toast.makeText(context, "User Deleted successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();





            }


        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


    }
}
}

