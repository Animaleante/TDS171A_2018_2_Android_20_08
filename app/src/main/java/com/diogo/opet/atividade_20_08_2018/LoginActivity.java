package com.diogo.opet.atividade_20_08_2018;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by opet on 20/08/2018.
 */

public class LoginActivity extends Activity {
    private FirebaseAuth mAuth;

    private EditText editLogin;
    private EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editLogin = findViewById(R.id.editLogin);
        editSenha = findViewById(R.id.editPassword);

        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        mAuth.signInWithEmailAndPassword(editLogin.getText().toString(), editSenha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            //Intent intent = new Intent(LoginActivity.this, SuccessActivity.class);
                            //startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
