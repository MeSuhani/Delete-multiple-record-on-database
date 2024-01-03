package com.example.sqldb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqldb.DB.MyDatabase;

public class MainActivity extends AppCompatActivity {

    TextView firstname, lastname;
    TextView add, show;
    private static final int STORAGE_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        add = findViewById(R.id.add);
        show = findViewById(R.id.show);

        if ((ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) &&
                ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    100);
        } else {
            Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }

        add.setOnClickListener(view -> {
            storedata();
        });

        show.setOnClickListener(view -> {
            Log.e("HELLO","click");
            startActivity(new Intent(MainActivity.this,DisplayDataActivity.class));
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Read SMS Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Read SMS Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void storedata() {
        String fname = firstname.getText().toString();
        String lname = lastname.getText().toString();

        if(fname.isEmpty() && lname.isEmpty())
        {
            Toast.makeText(this, "Enter Data", Toast.LENGTH_SHORT).show();
        }
        MyDatabase myDatabase = new MyDatabase(MainActivity.this);
        myDatabase.addContact(new MyDeatilModel(fname, lname));
        ClearView();
    }

    private void ClearView() {
        firstname.setText("");
        lastname.setText("");
    }
}