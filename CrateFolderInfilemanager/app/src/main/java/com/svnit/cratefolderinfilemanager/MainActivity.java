package com.svnit.cratefolderinfilemanager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText editTextfolder,editTextfile,editTexttext;
    Button button;
    String storagePermission= Manifest.permission.WRITE_EXTERNAL_STORAGE;
    boolean success=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkPermission(storagePermission)) {
            Toast.makeText(getApplicationContext(), "Permission already granted.", Toast.LENGTH_LONG).show();
        }


        editTextfolder =(EditText)findViewById(R.id.editTextFoldername);
        editTextfile=(EditText)findViewById(R.id.editTextFilename);
        editTexttext=(EditText)findViewById(R.id.editTextText);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foldername= editTextfolder.getText().toString();
                String filename=editTextfile.getText().toString();
                String text=editTexttext.getText().toString();
                File file=new File(Environment.getExternalStorageDirectory()+"/"+foldername);

                //File file1=new File(Environment.getExternalStorageDirectory()+"/"+foldername+"/hi.txt");
                if(!file.exists()){
                    Toast.makeText(getApplicationContext(),"Directory not exist",Toast.LENGTH_LONG).show();
                    success=file.mkdir();

                }
                if(success) {
                    Toast.makeText(getApplicationContext(), "Directory created", Toast.LENGTH_LONG).show();
                    File textfile = new File(file, filename+".txt");
                    FileWriter writer = null;
                    try {
                        writer = new FileWriter(textfile);
                    writer.append(text);
                    writer.flush();
                    writer.close();
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                }

                //Toast.makeText(getApplicationContext(),"Folder is created"+foldername,Toast.LENGTH_SHORT).show();
            }
        });




    }

    private boolean checkPermission(String storagePermission) {
        if (Build.VERSION.SDK_INT >= 23) {
            int result = ContextCompat.checkSelfPermission(getApplicationContext(), storagePermission);
            if (result == PackageManager.PERMISSION_GRANTED){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
