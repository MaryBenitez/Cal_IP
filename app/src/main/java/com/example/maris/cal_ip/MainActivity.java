package com.example.maris.cal_ip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText  ip,mask,netid,broad,can_h,part_r,part_h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip = (EditText)findViewById(R.id.editIP);
        mask = (EditText)findViewById(R.id.editmask);
        netid = (EditText)findViewById(R.id.editNet);
        broad = (EditText)findViewById(R.id.editBroad);
        can_h = (EditText)findViewById(R.id.edithost);
        part_r = (EditText)findViewById(R.id.editred);
        part_h = (EditText)findViewById(R.id.editpart_host);
    }

    public void datosIP (View v){

        //octetos
        String[] cadena_ip = ip.getText().toString().split("\\.");

        //Variables para valores puestos por el usuario
        long num_ip = 0;
        long num_mask = 0;
        long mask_inv = 0;
        long val_mask = Integer.parseInt(mask.getText().toString());

        if(cadena_ip.length != 4){
            return;
        }

        //ip en binario
        for(int i=3; i>=0; i--){
            num_ip |= (Long.parseLong(cadena_ip[3-i])) << (i*8);
        }

        //mascara en binario
        for(int i=1; i<=val_mask; i++){
            num_mask |= 1L << (32-i);
        }

        //invirtiendo mascara
        mask_inv = ~num_mask;

        //colocando datos
        netid.setText(long_ip(num_ip & num_mask));
        broad.setText(long_ip(num_ip | mask_inv));
        can_h.setText((int)(Math.pow(2,(double)(32-val_mask)))-2+"");
        part_r.setText(val_mask+"");
        part_h.setText((32-val_mask)+"");

    }

    //metodo, convertir tipo long a string
    public String long_ip(long numero_ip){
        String str_num_ip="";
        for(int i=3; i>=0; i--){
            str_num_ip += (0b1111_1111 & (numero_ip >> (i*8) )) + (i!=0? ".": "");
        }
        return str_num_ip;
    }




}
