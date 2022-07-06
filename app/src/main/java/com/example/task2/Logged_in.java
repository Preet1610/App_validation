package com.example.task2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task2.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

public class Logged_in extends AppCompatActivity {

    EditText et_name,et_mail,et_phone;
    TextInputEditText et_pass;
    ImageView back_img;
    String name_value,pass_value, phone_value, mail_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        et_name=findViewById(R.id.et_name);
        et_mail=findViewById(R.id.et_mail);
        et_pass=findViewById(R.id.et_pass);
        et_phone=findViewById(R.id.et_phone);
        back_img=findViewById(R.id.back_img);

        Intent i = getIntent();
        String str=i.getStringExtra("name");
        String str1=i.getStringExtra("mail");
        String str2=i.getStringExtra("pass");
        String str3=i.getStringExtra("phone");

        et_name.setText(str);
        et_mail.setText(str1);
        et_pass.setText(str2);
        et_phone.setText(str3);

        back_img.setOnClickListener(view -> {
            startActivity(new Intent(this,MainActivity.class));

            name_value=et_name.getText().toString().trim();
            pass_value=et_pass.getText().toString().trim();
            phone_value=et_phone.getText().toString().trim();
            mail_value=et_mail.getText().toString().trim();

            if (Validate()) {

                Intent i1 = new Intent(this, MainActivity.class);
                i1.putExtra("name", name_value);
                i1.putExtra("mail", mail_value);
                i1.putExtra("pass", pass_value);
                i1.putExtra("phone", phone_value);
                startActivity(i1);
                Toastc.isShow(this, "Data sent Successfully");
            }
        });
    }

    private boolean Validate() {

        String MobilePattern = "[0-9]{10}";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+com";

        if (et_name.length() > 25) {

            et_name.setError("Enter less than 25 characters");
            return true;

        } else if (et_name.length() == 0 || et_phone.length() == 0 || et_mail.length() ==
                0 || et_pass.length() == 0) {

            Toastc.isShow(this, "pls fill the empty fields");
            return false;

        } else if (et_pass.getText().toString().trim().equalsIgnoreCase("")){

            et_pass.setError("Enter valid password");
            return false;

        } else if (!et_phone.getText().toString().matches(MobilePattern)){

            et_phone.setError("Enter valid mobile number");
            return false;

        } else if (!et_mail.getText().toString().matches(emailPattern)){

            et_mail.setError("Enter valid mail address");
            return false;

        }
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}