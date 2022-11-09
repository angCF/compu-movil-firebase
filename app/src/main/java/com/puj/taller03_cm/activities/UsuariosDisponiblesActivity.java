package com.puj.taller03_cm.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.puj.taller03_cm.App;
import com.puj.taller03_cm.R;
import com.puj.taller03_cm.databinding.ActivityUsuariosDisponiblesBinding;
import com.puj.taller03_cm.models.DatabaseRoutes;
import com.puj.taller03_cm.models.UserInfo;
import com.puj.taller03_cm.services.CameraService;
import com.puj.taller03_cm.utils.PermissionHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class UsuariosDisponiblesActivity extends AuthenticatedActivity {
    public static final String TAG = UsuariosDisponiblesActivity.class.getName();
    ActivityUsuariosDisponiblesBinding binding;


    FirebaseDatabase mDatabase;
    DatabaseReference reference;
    ValueEventListener listener;
    ArrayAdapter adapter;
    private List<String> names = new ArrayList<>();
    private List<String> uuids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsuariosDisponiblesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference(DatabaseRoutes.USERS_PATH);
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getChildren().forEach(dataSnapshot -> {
                    UserInfo tmpUsr = dataSnapshot.getValue(UserInfo.class);
                    String uuid = dataSnapshot.getRef().getKey();
                    if(!uuids.contains(uuid)){
                        names.add(tmpUsr.getName());
                        uuids.add(uuid);
                    }
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled: ", error.toException());
            }
        };

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        binding.usersList.setAdapter(adapter);

        binding.usersList.setOnItemClickListener((adapterView, view, i, l) -> {
//            Intent intent = new Intent(UsuariosDisponiblesActivity.this, UserInfoActivity.class);
//            intent.putExtra("uuid", uuids.get(i));
//            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        reference.addValueEventListener(listener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        reference.removeEventListener(listener);
    }
}