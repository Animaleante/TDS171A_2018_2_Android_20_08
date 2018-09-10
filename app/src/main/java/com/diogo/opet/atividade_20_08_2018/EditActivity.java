package com.diogo.opet.atividade_20_08_2018;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditActivity extends Activity {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    
    private EditText editNome;
    private Spinner spinnerGraduacao;
    private TextView editNasc;
    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("usuarios");

        final Calendar nascDate = Calendar.getInstance();
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        editNome = findViewById(R.id.editNome);
        spinnerGraduacao = findViewById(R.id.comboGraduacao);
        editNasc = findViewById(R.id.editNasc);

        final DatePickerDialog.OnDateSetListener editNascListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                nascDate.set(Calendar.YEAR, year);
                nascDate.set(Calendar.MONTH, monthOfYear);
                nascDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                editNasc.setText(format.format(nascDate.getTime()));
            }
        };

        editNasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EditActivity.this, editNascListener,
                        nascDate.get(Calendar.YEAR),
                        nascDate.get(Calendar.MONTH),
                        nascDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        
        reference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuario = dataSnapshot.getValue(User.class);
                if(usuario != null) {
                    if (usuario.getNome() != null) editNome.setText(usuario.getNome());
                    spinnerGraduacao.setSelection(0);
                    editNasc.setText(usuario.getNascimento());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EditActivity.this, "Failed to read user data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void save(View view) {
        usuario.setNome(editNome.getText().toString());
        usuario.setGraduacao(spinnerGraduacao.getSelectedItem().toString());
        usuario.setNascimento(editNasc.getText().toString());

        reference.child(user.getUid()).setValue(usuario).addOnCompleteListener(EditActivity.this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(EditActivity.this, "User's data update successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(EditActivity.this, "Could not save user's data at this time. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
