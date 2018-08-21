package com.diogo.opet.atividade_20_08_2018;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by opet on 20/08/2018.
 */

public class SuccessActivity extends Activity {
    private FirebaseAuth mAuth;

    private TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        userEmail = findViewById(R.id.user_email);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        userEmail.setText(user.getEmail());
    }
}
