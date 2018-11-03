package com.example.yolobob.testsqlite;
/**
 * @Description : Practicing with SQLite and lab 06
 * @author : Frédérick Gaudet - 8035208
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvID,tvUsername,tvPassword;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvID = (TextView)findViewById(R.id.tvID);
        tvUsername = (TextView)findViewById(R.id.tvUsername);
        tvPassword = (TextView)findViewById(R.id.tvPassword);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
    }

    public void insertProductClick(View view){

        reset();

        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        try {
            User user = new User(username, password);

            MyDBHandler dbHandler = new MyDBHandler(this);

            dbHandler.insertUser(user);
            Toast.makeText(getApplicationContext(), "User created!", Toast.LENGTH_LONG).show();
        }
        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
        }
    }

    public void getIDClick(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);

        reset();
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();

        try {
            User user = dbHandler.findUser(username, password);

            tvUsername.setText(user.getUsername());
            tvPassword.setText(user.getPassword());
            tvID.setText(user.getId());
        }
        catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Wrong username/password!", Toast.LENGTH_LONG).show();
        }
    }

    public void delete(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);
        reset();
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();

        boolean deleted = dbHandler.deleteUser(username, password);
        if(deleted)
            Toast.makeText(getApplicationContext(), "User deleted!", Toast.LENGTH_LONG).show();
    }

    public void reset(){
        tvUsername.setText("Username");
        tvPassword.setText("Password");
        tvID.setText("N/A");
    }
}
