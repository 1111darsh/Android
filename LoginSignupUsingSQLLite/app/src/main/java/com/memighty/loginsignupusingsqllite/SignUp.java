package com.memighty.loginsignupusingsqllite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    Button signup;
    EditText user,email,password,repassword,number;
    SQLiteDatabase db;
    DBhelper dBhelper =new DBhelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        user=(EditText)findViewById(R.id.editTextUsername);
        email=(EditText)findViewById(R.id.editTextEmail);
        password=(EditText)findViewById(R.id.editTextPassword);
        repassword=(EditText)findViewById(R.id.editTextRePassword);
        number=(EditText)findViewById(R.id.editTextNumber);
        signup=(Button)findViewById(R.id.buttonSignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suser=user.getText().toString().trim();
                String semail=email.getText().toString().trim();
                String spassword=password.getText().toString().trim();
                String repass=repassword.getText().toString().trim();
                String snumber=number.getText().toString().trim();
                if(spassword.equals(repass))
                {
                    db=dBhelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("User", suser);
                    values.put("Email", semail);
                    values.put("Password", spassword);
                    values.put("Number", snumber);
                    db.insert("person",null,values);
                    Intent intent = new Intent(SignUp.this,MainActivity.class);
                    startActivity(intent);
                }






            }
        });

    }
}
