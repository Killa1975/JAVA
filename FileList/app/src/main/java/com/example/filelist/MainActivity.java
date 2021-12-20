package com.example.filelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
int  REQUEST_CODE_READ_FILES;
ListView listfiles;
ArrayList<String> files = new ArrayList<String>();
boolean ReadFiles_Granted = false;
String dirPath = Environment.getExternalStorageDirectory() + "/DCIM/Screenshots";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listfiles = findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, files);
        listfiles.setAdapter(adapter);

        int hasReadContactPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {ReadFiles_Granted = true;} else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_FILES);}
        if (ReadFiles_Granted) loadFiles();
    }


    @Override

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_READ_FILES) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ReadFiles_Granted = true;
            }
        }
        if (ReadFiles_Granted) {
            loadFiles();
        } else {
            Toast.makeText(this, "Требуется установить разрешения", Toast.LENGTH_LONG).show();
        }
    }
    public  void loadFiles()
    {
        File dir = new File(Environment.getExternalStoragePublicDirectory(
        "/DCIM/Camera").getPath());
        File fi = new File(dir.toString());
        File[] arrFiles = fi.listFiles();
        for (File f:arrFiles
             ) {
            files.add(f.getName());
        }
    }
}