package com.memighty.loginsignupusingsqllite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button login,signup;
    EditText email,password;
    SQLiteDatabase db;
    DBhelper dBhelper=new DBhelper(this);
    private String TAG="Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button)findViewById(R.id.buttonLogin);
        signup=(Button)findViewById(R.id.buttonSignUp);
        email=(EditText)findViewById(R.id.editTextEmail);
        password=(EditText)findViewById(R.id.editTextPassword);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                String semail=email.getText().toString();
                String spassword=password.getText().toString().trim();
                String []Projaction={"Email","Password"};
                db=dBhelper.getReadableDatabase();

                String []args={semail};
                Cursor cursor;
                cursor=db.query("person",Projaction,"Email=?",args,null,null,null);
                ///cursor=db.query("person",Projaction,"Email=?",new String[]{semail},null,null,null);
                //Log.d(TAG, "onClick: "+cursor.getCount());
                if(cursor.getCount()<=0){
                    Toast.makeText(getApplicationContext(),"user is invalid",Toast.LENGTH_LONG).show();
                }
                else {
                    cursor.moveToFirst();
                    if(spassword.equals(cursor.getString(1)))
                    {
                        Intent intent = new Intent(this, Complate.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"password is invalid",Toast.LENGTH_LONG).show();
                    }

                    //Log.d(TAG, "onClick: "+cursor.getString(0)+cursor.getString(1));


                }
                break;
            case R.id.buttonSignUp:
                Intent signup=new Intent(this,SignUp.class);
                startActivity(signup);
        }

    }


}
