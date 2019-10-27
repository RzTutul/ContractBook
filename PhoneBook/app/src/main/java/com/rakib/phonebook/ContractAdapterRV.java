package com.rakib.phonebook;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContractAdapterRV extends RecyclerView.Adapter<ContractAdapterRV.ContractViewHolder> {

    private Context context;
    private List<ContractPojo> contractPojos;
    private String PhoneNumber = null;


    public ContractAdapterRV(Context context, List<ContractPojo> contractPojos) {
        this.context = context;
        this.contractPojos = contractPojos;
    }

    @NonNull
    @Override
    public ContractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contract_row, parent, false);
        return new ContractViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContractViewHolder holder,final int position) {

        holder.name.setText(contractPojos.get(position).getName());
        holder.phone.setText(contractPojos.get(position).getPhone());
        holder.email.setText(contractPojos.get(position).getEmail());

        PhoneNumber = contractPojos.get(position).getPhone();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Details");
                builder.setIcon(R.drawable.details);
                LayoutInflater inflater = LayoutInflater.from(context);

                View view1 = inflater.inflate(R.layout.contract_list_dilog, null);

                final Button Editbtn = view1.findViewById(R.id.editbtn);
                final Button Deletebtn = view1.findViewById(R.id.deletebtn);
                final TextView name = view1.findViewById(R.id.dilog_nameTV);


                name.setText(contractPojos.get(position).getName());

                builder.setView(view1);


                final AlertDialog dialog = builder.create();
                dialog.show();

                Editbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContractPojo myTodo = contractPojos.get(position);
                        int id = myTodo.getId();
                        Intent intent = new Intent(context,Add_Contarct.class);
                        intent.putExtra("id", id);
                        context.startActivity(intent);

                    }
                });
                Deletebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ContractPojo contract = contractPojos.get(position);
                        ContractDatabse.getInstance(context).getContratDao().DeleteContract(contract);
                        Intent intent = new Intent(context,MainActivity.class);
                        context.startActivity(intent);

                    }
                });
            }
        });
        holder.callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "call", Toast.LENGTH_SHORT).show();
            }
        });


        holder.SendMessagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:" + PhoneNumber));  // This ensures only SMS apps respond
                //intent.putExtra("sms_body", "Hello how are you");
                // intent.putExtra(Intent.EXTRA_STREAM, attachment);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return contractPojos.size();
    }

    public class ContractViewHolder extends RecyclerView.ViewHolder {

        TextView name, phone, email;
        Button callbtn, SendMessagebtn;

        public ContractViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameTV);
            phone = itemView.findViewById(R.id.phoneNumberTV);
            email = itemView.findViewById(R.id.emailTV);

            callbtn = itemView.findViewById(R.id.callbtn);
            SendMessagebtn = itemView.findViewById(R.id.messagebtn);

        }
    }


}
