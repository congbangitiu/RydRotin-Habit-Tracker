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

public class Login extends AppCompatActivity {
    EditText email, password;
    Button btn_login, btn_regis;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.textEmailAddress);
        password = (EditText) findViewById(R.id.textPassword);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_regis  = (Button) findViewById(R.id.btn_register);
        DB = new DBHelper(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = email.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Please enter all the fields!!", Toast.LENGTH_SHORT).show();
                else if(!Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
                    Toast.makeText(Login.this, "Please enter valid Email!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login.this, "Sign in successfull!!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Email or Password are Wrong!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });
    }
}