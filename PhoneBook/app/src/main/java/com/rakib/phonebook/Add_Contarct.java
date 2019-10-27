package com.rakib.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Contarct extends AppCompatActivity {

    private EditText nameET,phoneET,emailET;
    private ImageView imageView;
    private String imageLocation;
    private int id =0;
    Button savebtn,updatebtn;

    String email =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__contarct);
        nameET = findViewById(R.id.nameId);
        phoneET = findViewById(R.id.phoneNumberID);
        emailET = findViewById(R.id.emailID);
        savebtn = findViewById(R.id.SavebtnID);
        updatebtn= findViewById(R.id.UpdateBtniD);

        updatebtn.setVisibility(View.GONE);

        imageView = findViewById(R.id.imageView);
        id = getIntent().getIntExtra("id", -1);
        if (id > 0){
            ContractPojo contractPojo = ContractDatabse.getInstance(this)
                    .getContratDao().getTodoID(id);
            nameET.setText(contractPojo.getName());
            phoneET.setText(contractPojo.getPhone());
           emailET.setText(contractPojo.getEmail());
            savebtn.setVisibility(View.GONE);
            updatebtn.setVisibility(View.VISIBLE);
        }

    }

    public void takePhoto(View view) {
    }

    public void savebtn(View view) {

        String name = nameET.getText().toString();
        String phone = phoneET.getText().toString();
        email = emailET.getText().toString();
        imageLocation = "";


        if (name.isEmpty())
        {
            nameET.setError("Give a name");
        }
        if (phone.isEmpty())
        {
            phoneET.setError("Give Phone Number");
        }
        else
        {
            ContractPojo contract = new ContractPojo(name,phone,email,imageLocation);


            final long insertRow = ContractDatabse.getInstance(this).getContratDao().AddNewContract(contract);

            if (insertRow>0)
            {
                startActivity(new Intent(Add_Contarct.this, MainActivity.class));
            }

        }






    }

    public void Updated(View view) {
        String name = nameET.getText().toString();
        String phone = phoneET.getText().toString();
        email = emailET.getText().toString();
        imageView = null;


        if (name.isEmpty())
        {
            nameET.setError("Give a name");
        }
        if (phone.isEmpty())
        {
            phoneET.setError("Give Phone Number");
        }

        ContractPojo contractPojo = new ContractPojo(id, name, phone, email,"");
        int updatedRow = ContractDatabse.getInstance(this)
                .getContratDao()
                .UpadteContract(contractPojo);
        if (updatedRow > 0){
            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
