package com.example.firebasetatorial.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasetatorial.Modal.User;
import com.example.firebasetatorial.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UserViewAdapter extends RecyclerView.Adapter<UserViewAdapter.UserViewHolder> {
         private List<User> mUserList;
         private DatabaseReference mReference=FirebaseDatabase.getInstance().getReference("User");
         private FirebaseAuth mAuth=FirebaseAuth.getInstance();
         private Context mContext;



    public UserViewAdapter(List<User> userList,Context mcontext) {
        mUserList = userList;
        this.mContext=mcontext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View mview=inflater.inflate(R.layout.cardviewwlayout,null);
        return new UserViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user=mUserList.get(position);
        holder.adapterfirstname.setText(user.getFirstname());
        holder.adapterlastname.setText(user.getLastname());
        holder.adapteremail.setText(user.getEmail());
        holder.adapterphone.setText(user.getPhone());


    }


    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
    {
        private TextView adapterfirstname,adapterlastname,adapteremail,adapterphone;
        private Button delete;
        private ImageView mImageView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            adapterfirstname=itemView.findViewById(R.id.cardviewfirstname);
            adapterlastname=itemView.findViewById(R.id.cardviewlastname);
            adapteremail=itemView.findViewById(R.id.cardviewemail);
            adapterphone=itemView.findViewById(R.id.cardviewphonenumber);
            mImageView=itemView.findViewById(R.id.UserView);


        }
    }
}
