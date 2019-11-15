package com.example.classactivity1117;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextZipCode;
    TextView textViewBirdName, textViewPersonName;
    Button buttonSearch, buttonGoToReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextZipCode = findViewById(R.id.editTextZipCode);
        textViewBirdName = findViewById(R.id.textViewBirdName);
        textViewPersonName = findViewById(R.id.textViewPersonName);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonGoToReport = findViewById(R.id.buttonGoToReport);

        buttonSearch.setOnClickListener(this);
        buttonGoToReport.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Bird");

        if(v == buttonSearch){
            String findZip = editTextZipCode.getText().toString();
            int intFindZip = new Integer(findZip);
            myRef.orderByChild("zipCode").equalTo(intFindZip).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String findKey = dataSnapshot.getKey();
                    Bird foundBird = dataSnapshot.getValue(Bird.class);
                    String findBird = foundBird.birdName;
                    String findPerson = foundBird.personName;

                    textViewBirdName.setText(findBird);
                    textViewPersonName.setText(findPerson);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else if(v == buttonGoToReport){
            Intent reportIntent = new Intent(this, MainActivity.class);
            startActivity(reportIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemGoToReport){
            Intent reportIntent = new Intent(this, MainActivity.class);
            startActivity(reportIntent);
        }else if (item.getItemId() == R.id.itemGoToSearch){
            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
