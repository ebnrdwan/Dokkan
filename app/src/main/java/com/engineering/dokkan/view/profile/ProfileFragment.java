package com.engineering.dokkan.view.profile;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends BaseFragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private StorageReference storageReference;
    private ProgressDialog loadingBar;
    Uri mImageUri;
    String url;

    private CircleImageView profileimg;
    private TextView username;
    private ImageView editname, editpic;
    private EditText namET;
    private Button ok, signout, done_img;
    private String currentUserID;


    public ProfileFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void initializeViews(View view) {
        storageReference = FirebaseStorage.getInstance().getReference();
        currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d(" current user ", " USER ID" + currentUserID);


        profileimg = view.findViewById(R.id.profile_image);
        username = view.findViewById(R.id.tv_name);
        editpic = view.findViewById(R.id.edit_photo);
        editname = view.findViewById(R.id.edit_name);

        namET = view.findViewById(R.id.et_name);
        ok = view.findViewById(R.id.ok);
        signout = view.findViewById(R.id.signout_btn);
        done_img = view.findViewById(R.id.done);


        ListView listView = view.findViewById(R.id.list);
        ArrayList<String> list = AddingOptionsToList();
        OptionAdapter adapter = new OptionAdapter(requireActivity(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onOptionClickListener);

        showInfo(currentUserID);

    }

    private void showInfo(String currentUserID) {
        final Query query = FirebaseDatabase.getInstance().getReference("Users")
                .orderByChild("uid").equalTo(currentUserID);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    if (snapshot.child("name").exists()) {
                        String n = snapshot.child("name").getValue(String.class);
                        username.setText(n);
                        Log.d("text view", "name" + n);
                    }
                    if (snapshot.child("userImage").exists()) {
                        String url = snapshot.child("userImage").getValue(String.class);
                        Picasso.get().load(url).into(profileimg);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private AdapterView.OnItemClickListener onOptionClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch ((position)) {
                case 0:
                    getNavController().navigate(R.id.action_profileFragment_to_ordersFragment);
                    break;
                case 1:
                    getNavController().navigate(R.id.action_profileFragment_to_chatFragement);
                    break;
                case 5:
                    getNavController().navigate(R.id.action_profileFragment_to_addressesfragment);

            }


        }
    };

    @Override
    public void setListeners() {

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                getNavController().navigate(R.id.action_profileFragment_to_fragment_Welcome);
            }
        });

        editpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editpic.setVisibility(View.INVISIBLE);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
                done_img.setVisibility(View.VISIBLE);
                done_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        uploadFile();
                        done_img.setVisibility(View.INVISIBLE);
                        editpic.setVisibility(View.VISIBLE);
                    }
                });

            }
        });

        editname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setVisibility(View.INVISIBLE);
                editname.setVisibility(View.INVISIBLE);

                namET.setVisibility(View.VISIBLE);
                ok.setVisibility(View.VISIBLE);
                namET.setText(username.getText());

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                                .child(currentUserID);
                        databaseReference.child("name").setValue(namET.getText().toString());
                        username.setVisibility(View.VISIBLE);
                        username.setText(namET.getText().toString());
                        namET.setVisibility(View.INVISIBLE);
                        ok.setVisibility(View.INVISIBLE);

                        editname.setVisibility(View.VISIBLE);
                    }
                });


            }
        });

    }

    private ArrayList<String> AddingOptionsToList() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("My Orders");
        list.add("Messages");
        list.add("Reviews");
        list.add("Help");
        list.add("Languages");
        list.add("My Address Book");
        return list;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(profileimg);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            final StorageReference fileReference = storageReference.child("UserImages/" + System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));
            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "Upload successful", Toast.LENGTH_LONG).show();
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    url = String.valueOf(uri);
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                                            .child(currentUserID);
                                    databaseReference.child("userImage").setValue(url);

                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(getContext(), "No file selected", Toast.LENGTH_SHORT).show();
        }
    }


}
