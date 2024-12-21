package com.zkrallah.z_habits.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zkrallah.z_habits.R;
import com.zkrallah.z_habits.ui.home.HomeActivity;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    EditText email, password, repassword;
    Button register, login;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.textEmail);
        password = (EditText) findViewById(R.id.textResPassword);
        repassword = (EditText) findViewById(R.id.textResRepassword);
        register = (Button) findViewById(R.id.btn_register);
        login = (Button) findViewById(R.id.btn_login2);
        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Registration.this, "Please enter all the fields!!", Toast.LENGTH_SHORT).show();
                else if(!Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
                    Toast.makeText(Registration.this, "Please enter valid Email!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass.equals(repass)) {
                        Boolean userCheck = DB.checkusername(user);
                        if (userCheck == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(Registration.this, "Registered successfully!!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Registration.this, "Registration failed!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Registration.this, "Email already exists!!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Registration.this, "Passwords not matching!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}