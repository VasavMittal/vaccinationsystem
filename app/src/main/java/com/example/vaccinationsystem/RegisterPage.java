package com.example.vaccinationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vaccinationsystem-182a8-default-rtdb.firebaseio.com/");
    Switch s1;
    private EditText name,password,cpassword,age,aadhaar;
    private RadioButton r1,r2,r3;
    private Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        age = findViewById(R.id.AGE);
        aadhaar = findViewById(R.id.aadhaar);
        r1 = findViewById(R.id.male);
        r2 = findViewById(R.id.female);
        r3 = findViewById(R.id.other);

        b1 = findViewById(R.id.register);
        s1 = findViewById(R.id.bj);
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String n1, p1, p2, a1, a2, radio, text;
                String ck1 = " ";
                n1 = String.valueOf(name.getText());
                p1 = String.valueOf(password.getText());
                p2 = String.valueOf(cpassword.getText());
                a1 = String.valueOf(age.getText());
                a2 = String.valueOf(aadhaar.getText());
                if (r1.isChecked()) {
                    radio = "male";
                } else if (r2.isChecked()) {
                    radio = "female";
                } else {
                    radio = "other";
                }
                if (n1.isEmpty() || a1.isEmpty() || a2.isEmpty() || radio.isEmpty() || ck1.isEmpty()) {
                    Toast.makeText(RegisterPage.this, "Please fill all field", Toast.LENGTH_SHORT).show();
                } else if (!p1.equals(p2)) {
                    Toast.makeText(RegisterPage.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterPage.this, "clicked", Toast.LENGTH_SHORT).show();
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(a2)) {
                                Toast.makeText(RegisterPage.this, "user is already register", Toast.LENGTH_SHORT).show();
                            } else {
                                databaseReference.child("users").child(a2).child("name").setValue(n1);
                                databaseReference.child("users").child(a2).child("age").setValue(a1);
                                databaseReference.child("users").child(a2).child("gender").setValue(radio);
                                databaseReference.child("users").child(a2).child("password").setValue(p1);
                                Toast.makeText(RegisterPage.this, "User registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getBaseContext(), LoginPage.class);
                                startActivity(i);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}