package com.example.classactivity1117;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText editTextBirdName, editTextZipCode, editTextPersonName;
    Button buttonSubmit, buttonGoToSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextZipCode = findViewById(R.id.editTextZipCode);

        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonGoToSearch = findViewById(R.id.buttonGoToSearch);

        buttonSubmit.setOnClickListener(this);
        buttonGoToSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Bird");

        if(v == buttonSubmit){
            String createBirdName = editTextBirdName.getText().toString();
            String createZipCode = editTextZipCode.getText().toString();
            int intZipCode = new Integer(createZipCode).intValue();
            String createPersonName = editTextPersonName.getText().toString();

            Bird myBird = new Bird(createBirdName,intZipCode,createPersonName);
            myRef.push().setValue(myBird);

        }else if (v == buttonGoToSearch){
            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);
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

        if(item.getItemId() == R.id.itemGoToSearch){
            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);
        }else if (item.getItemId() == R.id.itemGoToReport){
            Intent reportIntent = new Intent(this, MainActivity.class);
            startActivity(reportIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
