package com.example.firebasetatorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.firebasetatorial.Adapter.UserViewAdapter;
import com.example.firebasetatorial.Modal.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserHistory extends AppCompatActivity {
    private UserViewAdapter mUserViewAdapter,searchAdapter;
    private RecyclerView mRecyclerView;
    private User user;
    private List<User> mUsers=new ArrayList<>();
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private ImageView image;
    private SearchView mSearchView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);
        connection();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return true;
            }
        });
        mRecyclerView.setLayoutManager(new GridLayoutManager(UserHistory.this,1));

         mRecyclerView.setAdapter(mUserViewAdapter);
        readHistoryUser();
    }

    private void search(String newText) {
        List<User> search = new ArrayList<>();
        for (User searchitem :mUsers)
        {
            if(searchitem.getFirstname().toLowerCase().contains(newText.toLowerCase()))
            {
                search.add(searchitem);
            }
        }
        searchAdapter=new UserViewAdapter(search,UserHistory.this);
        mRecyclerView.setAdapter(searchAdapter);

    }

    private void readHistoryUser() {
        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> iterable =snapshot.getChildren();
                for (DataSnapshot dataSnapshot :iterable)
                {
                    User user= (User) dataSnapshot.getValue(User.class);
                    mUsers.add(user);
                }
                mUserViewAdapter.notifyDataSetChanged();


            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void connection() {
        mAuth=FirebaseAuth.getInstance();
        mRecyclerView= findViewById(R.id.recyclarView);
        mUser=mAuth.getCurrentUser();
        mSearchView=findViewById(R.id.SearhView);
        mReference=FirebaseDatabase.getInstance().getReference("User");
        mUserViewAdapter=new UserViewAdapter(mUsers,UserHistory.this);


    }
}