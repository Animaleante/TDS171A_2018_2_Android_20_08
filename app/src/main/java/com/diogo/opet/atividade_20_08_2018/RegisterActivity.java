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

public class RegisterActivity extends Activity {
    private FirebaseAuth mAuth;

    private EditText editLogin;
    private EditText editSenha;
    private EditText editSenhaConfirma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editLogin = findViewById(R.id.editLogin);
        editSenha = findViewById(R.id.editPassword);
        editSenhaConfirma = findViewById(R.id.editPasswordConfirm);

        mAuth = FirebaseAuth.getInstance();
    }

    public void register(View view) {
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();
        String senhaConfirma = editSenhaConfirma.getText().toString();

        if(!senha.equals(senhaConfirma)) {
            Toast.makeText(RegisterActivity.this, "The passwords don't match! ", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(login, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Register failed! ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
