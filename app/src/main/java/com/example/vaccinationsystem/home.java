package com.example.vaccinationsystem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vaccinationsystem-182a8-default-rtdb.firebaseio.com/");
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    Button b1,b2,b3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        b1 = view.findViewById(R.id.b1);
        b2 = view.findViewById(R.id.b2);
        b3 = view.findViewById(R.id.b3);
        Bundle bundle = getArguments();
        String a2 = bundle.getString("aadhaar");
        Toast.makeText(getContext(), a2, Toast.LENGTH_SHORT).show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("appointment").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(a2) && snapshot.hasChild("ap1")) {
                                Toast.makeText(getContext(), "Already Booked", Toast.LENGTH_SHORT).show();
                                b1.setEnabled(false);
                                b1.setAlpha(0.5F);
                        } else {
                            databaseReference.child("appointment").child(a2).child("ap1").setValue("COVID-19");
                            Toast.makeText(getContext(), "BOOKED", Toast.LENGTH_SHORT).show();
                           b1.setEnabled(false);
                           b1.setAlpha(0.5F);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("appointment").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(a2) && snapshot.hasChild("ap2")) {
                                    Toast.makeText(getContext(), "Already Booked", Toast.LENGTH_SHORT).show();
                                    b2.setEnabled(false);
                                    b2.setAlpha(0.5F);

                        } else {
                            databaseReference.child("appointment").child(a2).child("ap2").setValue("TYPHOID");
                            Toast.makeText(getContext(), "BOOKED", Toast.LENGTH_SHORT).show();
                            b2.setEnabled(false);
                            b2.setAlpha(0.5F);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("appointment").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(a2) && snapshot.hasChild("ap3")) {
                                    Toast.makeText(getContext(), "Already Booked", Toast.LENGTH_SHORT).show();
                                    b3.setEnabled(false);
                                    b3.setAlpha(0.5F);

                        } else {
                            databaseReference.child("appointment").child(a2).child("ap3").setValue("HIB");
                            Toast.makeText(getContext(), "BOOKED", Toast.LENGTH_SHORT).show();
                            b3.setEnabled(false);
                            b3.setAlpha(0.5F);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return view;
    }
}