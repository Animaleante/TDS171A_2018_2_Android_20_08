package com.diogo.opet.atividade_20_08_2018;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditActivity extends Activity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    
    private EditText editNome;
    private Spinner spinnerGraduacao;
    private DatePicker editNasc;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("usuarios");
        
        editNome = findViewById(R.id.editNome);
        spinnerGraduacao = findViewById(R.id.comboGraduacao);
        editNasc = findViewById(R.id.editNasc);
        
        reference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void save(View view) {

        User usuario = new User(user.getEmail(), "", null, "");

        reference.child(user.getUid()).setValue(usuario).addOnCompleteListener(EditActivity.this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                    }
                });
    }
}
