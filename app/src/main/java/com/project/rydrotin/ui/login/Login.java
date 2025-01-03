package com.project.rydrotin.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.rydrotin.HabitsApp;
import com.project.rydrotin.R;
import com.project.rydrotin.ui.home.HomeActivity;

public class Login extends AppCompatActivity {
    EditText email, password;
    Button btn_login;
    DBHelper DB;
    TextView regis;

    HabitsApp userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.textEmailAddress);
        password = (EditText) findViewById(R.id.textPassword);
        btn_login = (Button) findViewById(R.id.btn_login);
        regis = (TextView) findViewById(R.id.textRegister);

        DB = new DBHelper(this);

        userName = ((HabitsApp)getApplicationContext());

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = email.getText().toString();
                String pass = password.getText().toString();

                if(user.isEmpty() || pass.isEmpty())
                    Toast.makeText(Login.this, "Please enter all the fields!!", Toast.LENGTH_SHORT).show();
                else if(!Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
                    Toast.makeText(Login.this, "Please enter valid Email!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean isValid = DB.checkValidateLogin(user, pass);
                    String name = DB.getUsername(user);
                    userName.setName(name);
                    if(isValid){
                        Toast.makeText(Login.this, "Sign in successfull!!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        intent.putExtra("name", userName.getName());
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Email or Password are Wrong!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });
    }
}