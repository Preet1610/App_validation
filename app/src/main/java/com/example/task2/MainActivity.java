package com.example.task2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.task2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

//    EditText et_name, et_mail, et_pass, et_passcfrm, et_phone;
//    RadioButton radio_male, radio_female;
//    RadioGroup gender;
//    Button btn_submit;
//    String MobilePattern = "[0-9]{10}";
//    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]";
    String name_value,pass_value, passc_value, phone_value, mail_value;


    //@SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//        btn_submit=findViewById(R.id.btn_submit);

//        radio_male=(RadioButton) findViewById(R.id.radio_male);
//        radio_female=(RadioButton) findViewById(R.id.radio_female);
//        gender=(RadioGroup) findViewById(R.id.gender);
//        et_name = findViewById(R.id.et_name);
//        et_mail = findViewById(R.id.et_mail);
//        et_pass = findViewById(R.id.et_pass);
//        et_passcfrm = findViewById(R.id.et_passcfrm);
//        et_phone = findViewById(R.id.et_phone);

        Intent i1 = getIntent();
        String str=i1.getStringExtra("name");
        String str1=i1.getStringExtra("mail");
        String str2=i1.getStringExtra("pass");
        String str3=i1.getStringExtra("phone");

        binding.etName.setText(str);
        binding.etMail.setText(str1);
        binding.etPass.setText(str2);
        binding.etPhone.setText(str3);

        binding.btnSubmit.setOnClickListener(view -> {

            name_value=binding.etName.getText().toString().trim();
            pass_value=binding.etPass.getText().toString().trim();
            passc_value=binding.etPasscfrm.getText().toString().trim();
            phone_value=binding.etPhone.getText().toString().trim();
            mail_value=binding.etMail.getText().toString().trim();

            if (Validate()){

                Intent i=new Intent(this,Logged_in.class);
                i.putExtra("name",name_value);
                i.putExtra("mail",mail_value);
                i.putExtra("pass",pass_value);
                i.putExtra("phone",phone_value);
                startActivity(i);
                Toastc.isShow(this,"Successful");
            }
        });
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

    private boolean Validate() {

        String MobilePattern = "[0-9]{10}";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+com";

        if (binding.etName.length() > 25) {

            binding.etName.setError("Enter less than 25 characters");
            return true;

        } else if (binding.etName.length() == 0 || binding.etMail.length() == 0 || binding.etPass.length() ==
                0 || binding.etPasscfrm.length() == 0 || binding.etPhone.length() == 0) {

            Toastc.isShow(this, "pls fill the empty fields");
            return false;

        } else if (!binding.etPass.getText().toString().equals(binding.etPasscfrm.getText().toString())){

            binding.etPasscfrm.setError("Passwords don't match");
            return false;

        } else if (binding.etPass.getText().toString().trim().equalsIgnoreCase("")){

            binding.etPass.setError("Enter valid password");
            return false;

        } else if (binding.gender.getCheckedRadioButtonId()==-1){

            Toastc.isShow(this,"Select gender");
            return false;

        } else if (!binding.etPhone.getText().toString().matches(MobilePattern)){

            binding.etPhone.setError("Enter valid mobile number");
            return false;

        } else if (!binding.etMail.getText().toString().matches(emailPattern)){

            binding.etMail.setError("Enter valid mail address");
            return false;

        }
        return true;
    }

}