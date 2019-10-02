package com.example.binaykumar.landcb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    private Button back;
    private Spinner CRno;
    private Spinner claimant;
    private TextView payments1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Toast.makeText(this,"BINAY KUMAR, AGM (Mining)", Toast.LENGTH_LONG).show();
        Button back=(Button)findViewById(R.id.btn4);
        back.setOnClickListener(this);
        CRno= (Spinner)findViewById(R.id.spn2);
        claimant=(Spinner)findViewById(R.id.spn3);
        payments1=(TextView)findViewById(R.id.tv6);
        payments1.setMovementMethod(new ScrollingMovementMethod());

        Intent intent=getIntent();
        List<String> CRnos = intent.getStringArrayListExtra("CRnos");


        ArrayAdapter CRno1=new ArrayAdapter(this,android.R.layout.simple_spinner_item,CRnos);
        CRno1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CRno.setAdapter(CRno1);
        CRno.setOnItemSelectedListener(this);

         List<String>  claimants = intent.getStringArrayListExtra("claimants");
         ArrayAdapter claimants1=new ArrayAdapter(this,android.R.layout.simple_spinner_item,claimants);
         claimants1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         System.out.println("test printing" +claimants1);
         claimant.setAdapter(claimants1);

         claimant.setOnItemSelectedListener(this);





    }
    @Override
    public void onClick(View v) {

        Intent intent;
        intent=new Intent(this,MainActivity.class);
        startActivity(intent);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       String item= parent.getItemAtPosition(position).toString();

         switch (parent.getId()) {

           case R.id.spn2:

        com.example.binaykumar.landcb.DatabaseAccess databaseAccess1 = com.example.binaykumar.landcb.DatabaseAccess.getInstance(this);
        databaseAccess1.open();
        payments1.setText((CharSequence) databaseAccess1.res2((String) CRno.getSelectedItem().toString()));
        databaseAccess1.close();
               break;

           case R.id.spn3:
               com.example.binaykumar.landcb.DatabaseAccess databaseAccess2 = com.example.binaykumar.landcb.DatabaseAccess.getInstance(this);
               databaseAccess2.open();
               payments1.setText((CharSequence) databaseAccess2.res3((String) claimant.getSelectedItem().toString()));
               databaseAccess2.close();
              break;
         }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    }

