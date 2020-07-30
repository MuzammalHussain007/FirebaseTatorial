package com.example.firebasetatorial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.firebasetatorial.Modal.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity".getClass().getName();
    private static final int GALLERY_IMAGE = 1;
    private static final int CAMERA_CAPTURE_CODE = 2;
    private StorageReference mStorageReference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mReference;
    private ProgressDialog mProgressDialog;
    private ImageView mImageView;
    private TextView rfirstname, rlastname, remail, rpassword, rphone, rpicture;
    private String imageHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Home Screen");
        conection();
        readValueFromFirebase();
        rfirstname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                LayoutInflater inflater =LayoutInflater.from(HomeActivity.this);
                v= inflater.inflate(R.layout.custom_user_update_view,null);
                final EditText editText = v.findViewById(R.id.updateUser);
                Button button = v.findViewById(R.id.updateButton);
                Button button1 = v.findViewById(R.id.deleteButton);
                builder.setView(v);
                final AlertDialog alertDialog=  builder.create();
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mReference.child(mAuth.getCurrentUser().getUid()).child("firstname").removeValue();
                        alertDialog.dismiss();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().toString()!=null)
                        {
                            String name=editText.getText().toString();
                            HashMap<String ,Object> updateUser = new HashMap();
                            updateUser.put("firstname",name);
                            mReference.child(mAuth.getCurrentUser().getUid()).updateChildren(updateUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(),"User Update Successfully",Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();
                                }
                            });
                        }
                    }
                });
                alertDialog.show();
                return true;
            }
        });


        rlastname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                LayoutInflater inflater =LayoutInflater.from(HomeActivity.this);
                v= inflater.inflate(R.layout.custom_user_update_view,null);
                final EditText editText = v.findViewById(R.id.updateUser);
                Button button = v.findViewById(R.id.updateButton);
                Button button1 = v.findViewById(R.id.deleteButton);
                builder.setView(v);
                final AlertDialog alertDialog=  builder.create();
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mReference.child(mAuth.getCurrentUser().getUid()).child("lastname").removeValue();
                        alertDialog.dismiss();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().toString()!=null)
                        {
                            String name=editText.getText().toString();
                            HashMap<String ,Object> updateUser = new HashMap();
                            updateUser.put("lastname",name);
                            mReference.child(mAuth.getCurrentUser().getUid()).updateChildren(updateUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(),"User Update Successfully",Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();
                                }
                            });
                        }
                    }
                });
                alertDialog.show();
                return true;
            }
        });

        remail.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                LayoutInflater inflater =LayoutInflater.from(HomeActivity.this);
                v= inflater.inflate(R.layout.custom_user_update_view,null);
                final EditText editText = v.findViewById(R.id.updateUser);
                Button button = v.findViewById(R.id.updateButton);
                Button button1 = v.findViewById(R.id.deleteButton);
                builder.setView(v);
                final AlertDialog alertDialog=  builder.create();
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mReference.child(mAuth.getCurrentUser().getUid()).child("email").removeValue();
                        alertDialog.dismiss();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().toString()!=null)
                        {
                            String name=editText.getText().toString();
                            HashMap<String ,Object> updateUser = new HashMap();
                            updateUser.put("email",name);
                            mReference.child(mAuth.getCurrentUser().getUid()).updateChildren(updateUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(),"User Update Successfully",Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();
                                }
                            });
                        }
                    }
                });
                alertDialog.show();
                return true;
            }
        });


        rphone.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                LayoutInflater inflater =LayoutInflater.from(HomeActivity.this);
                v= inflater.inflate(R.layout.custom_user_update_view,null);
                final EditText editText = v.findViewById(R.id.updateUser);
                Button button = v.findViewById(R.id.updateButton);
                Button button1 = v.findViewById(R.id.deleteButton);
                builder.setView(v);
                final AlertDialog alertDialog=  builder.create();
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mReference.child(mAuth.getCurrentUser().getUid()).child("phone").removeValue();
                        alertDialog.dismiss();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().toString()!=null)
                        {
                            String name=editText.getText().toString();
                            HashMap<String ,Object> updateUser = new HashMap();
                            updateUser.put("phone",name);
                            mReference.child(mAuth.getCurrentUser().getUid()).updateChildren(updateUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(),"User Update Successfully",Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();
                                }
                            });
                        }
                    }
                });
                alertDialog.show();
                return true;
            }
        });

           mImageView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   setCheckPermission();
                   AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                   String[] list = {"Camera","Gallery"};
                   builder.setItems(list, new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           switch (which)
                           {
                               case 0 :
                               {
                                   captureFromCamera();
                                   break;
                               }
                               case 1 :
                               {
                                   captureFromGallery();
                                   break;
                               }
                           }
                       }
                   });
                   builder.create().show();
                   return true;
               }
           });

    }

    private void setCheckPermission() {
        List <String > permission = new ArrayList<>();
          String Camera_Permission;
    }

    private void captureFromGallery() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture"), GALLERY_IMAGE);

    }

    private void captureFromCamera() {
//        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(camera,CAMERA_CAPTURE_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GALLERY_IMAGE)
        {
            if (resultCode==RESULT_OK && data !=null)
            {
                final Uri image =data.getData();
                mImageView.setImageURI(image);

                          mProgressDialog.show();
                 StorageReference reference = mStorageReference.child("Muzammal"+UUID.randomUUID().toString());
                 reference.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                     @Override
                     public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                         mProgressDialog.dismiss();
                         taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                             @Override
                             public void onSuccess(final Uri uri) {

                                HashMap<String,Object> hashMap = new HashMap<>();
                                hashMap.put("picture",uri.toString());
                                mReference.child(mAuth.getCurrentUser().getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                     if (task.isSuccessful())
                                     {

                                     }
                                     else
                                     {
                                       //  Toast.makeText(getApplicationContext(),""+task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                                     }
                                    }
                                });


                             }
                         });
                     }
                 });



            }
        }
        else if (requestCode == CAMERA_CAPTURE_CODE)
        {
            if (resultCode==RESULT_OK && data != null)
            {

            }

        }

    }


    private void readValueFromFirebase() {
        mReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User muser= snapshot.getValue(User.class);
                if (muser!=null)
                {

                    rfirstname.setText(muser.getFirstname());
                    rlastname.setText(muser.getLastname());
                    remail.setText(muser.getEmail());
                    rphone.setText(muser.getPhone());
                    imageHolder=muser.getPicture();
                  Glide.with(HomeActivity.this).load(Uri.parse(imageHolder)).into(mImageView);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    private void conection() {
        rfirstname=findViewById(R.id.firstname);
        rlastname=findViewById(R.id.lastname);
        remail=findViewById(R.id.emailaddress);
        rphone=findViewById(R.id.phonenumber);
        mImageView=findViewById(R.id.image);
        mStorageReference= FirebaseStorage.getInstance().getReference("/Images");

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mProgressDialog=new ProgressDialog(HomeActivity.this);
        mProgressDialog.setMessage("Uploading");

        mReference = FirebaseDatabase.getInstance().getReference("User");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menue_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout: {
                mAuth.signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void getUserHistory(View view) {
        startActivity(new Intent(HomeActivity.this,UserHistory.class));
        finish();
    }

    public void setSearch(View view) {
        startActivity(new Intent(HomeActivity.this,SearchActivity.class));
        finish();
    }
}