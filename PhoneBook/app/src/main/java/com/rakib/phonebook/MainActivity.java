package com.rakib.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private ContractAdapterRV contractAdapterRV;
   private RecyclerView ContractRV;
   public List<ContractPojo> contractPojos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContractRV = findViewById(R.id.ContractListRV);


        contractPojos = ContractDatabse.getInstance(this).getContratDao().getAllContract();


        contractAdapterRV = new ContractAdapterRV(this,contractPojos);

        LinearLayoutManager lmm = new LinearLayoutManager(this);

        ContractRV.setLayoutManager(lmm);

        ContractRV.setAdapter(contractAdapterRV);



    }

    public void AddnewContarct(View view) {

        startActivity(new Intent(MainActivity.this,Add_Contarct.class));
    }
}
