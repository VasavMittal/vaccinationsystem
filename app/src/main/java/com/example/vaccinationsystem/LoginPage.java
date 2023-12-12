package com.example.vaccinationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    TextView t1,t2,t3,t4;
    Button b1;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vaccinationsystem-182a8-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        t1 = findViewById(R.id.signupText);
        t2 = findViewById(R.id.aadhaar);
        t3 = findViewById(R.id.username);
        t4 = findViewById(R.id.password);
        b1 = findViewById(R.id.loginbutton);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), RegisterPage.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String aadha,un,ps;
                aadha = String.valueOf(t2.getText());
                un = String.valueOf(t3.getText());
                ps = String.valueOf(t4.getText());
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(aadha))
                        {

                            final String pass = snapshot.child(aadha).child("password").getValue(String.class);
                            final String uname = snapshot.child(aadha).child("name").getValue(String.class);
                            Log.i("password",pass);
                            if(ps.equals(pass) && un.equals(uname))
                            {
                                SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putBoolean("flag",true);
                                editor.apply();
                                Intent i = new Intent(getBaseContext(),MainActivity2.class);
                                i.putExtra("name",un);
                                i.putExtra("aadhaar",aadha);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LoginPage.this,"User name/password Wrong",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(LoginPage.this,"Aadhaar number don't Exist",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getBaseContext(),RegisterPage.class);
                            startActivity(i);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}