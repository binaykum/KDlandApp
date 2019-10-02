package com.example.binaykumar.KDlandApp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


   // private EditText input_plot_no;
    private TextView village_name;
    private TextView plot_no;
    private Button search_plotNo;
    private TextView output;
    private Spinner village_list;
    public  Button payment_details;
    private Button detailed;
    private AutoCompleteTextView input_plot_no;
   //         ,back2,search1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"Developed by CBA team in-house", Toast.LENGTH_SHORT).show();

        // Toast.makeText(this,"    v1:"+"\n"+"upto 2017", Toast.LENGTH_SHORT).show();


       // input_plot_no=(EditText) findViewById(R.id.editText2);
        village_name=(TextView) findViewById(R.id.textView3);
        plot_no=(TextView)findViewById(R.id.textView4);
        output=(TextView)findViewById(R.id.textView5);
        search_plotNo=(Button)findViewById(R.id.btn1);
        village_list=(Spinner)findViewById(R.id.spn1);
        search_plotNo.setOnClickListener(this);
        village_list.setOnItemSelectedListener(this);
        payment_details=(Button)findViewById(R.id.btn2) ;
        payment_details.setOnClickListener(this);
        input_plot_no=(AutoCompleteTextView)findViewById(R.id.act1);

        output.setVisibility(View.INVISIBLE);

        detailed=(Button)findViewById(R.id.btn3);
        detailed.setOnClickListener(this);
     //   search1=(Button)findViewById(R.id.btn4);
       // back2=(Button)findViewById(R.id.btn5);

        payment_details.setVisibility(View.INVISIBLE);
        detailed.setVisibility(View.INVISIBLE);

        //spinner code

        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(this);
        databaseAccess.open();
        //System.out.println(databaseAccess.getVillages());
        List<String> villages = databaseAccess.getVillages();



        databaseAccess.close();

        //String[] villageList= {"Pagar","Jordag", "Chatti Bariatu" };
        ArrayAdapter villList= new ArrayAdapter(this,android.R.layout.simple_spinner_item,villages);
        villList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        village_list.setAdapter(villList);



        // Log.i("message to binay ","spinner successful");
        output.setMovementMethod((new ScrollingMovementMethod()));

    }

    @Override
    public void onClick(View v) {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        if (input_plot_no.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Pl enter plot no", Toast.LENGTH_SHORT).show();
        } else {
            output.setVisibility(View.VISIBLE);
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        Log.i("message to binay ", "database opening successful");
        switch (v.getId()) {
            case R.id.btn1:
                payment_details.setVisibility(View.INVISIBLE);
                detailed.setVisibility(View.INVISIBLE);
                output.setText((CharSequence) databaseAccess.res((String) village_list.getSelectedItem().toString(), input_plot_no.getText()));
                if (!databaseAccess.res((String) village_list.getSelectedItem().toString(), input_plot_no.getText()).contains("paidArea:-null")) {
                    payment_details.setVisibility(View.VISIBLE);
                    detailed.setVisibility(View.VISIBLE);
                }
                if (databaseAccess.res((String) village_list.getSelectedItem().toString(), input_plot_no.getText()).contains("Anil Kumar")) {
                    payment_details.setVisibility(View.INVISIBLE);
                    detailed.setVisibility(View.INVISIBLE);
                }
                databaseAccess.close();
                break;
            case R.id.btn2:
                output.setText((CharSequence) databaseAccess.res1((String) village_list.getSelectedItem().toString(), input_plot_no.getText()));


                //List<String> claimants = databaseAccess.getClaimants();

                databaseAccess.close();

                break;
            case R.id.btn3:

                List<String> CRnos = databaseAccess.getCRnos((String) village_list.getSelectedItem().toString(), input_plot_no.getText());
                List<String> claimants = databaseAccess.getClaimants((String) village_list.getSelectedItem().toString(), input_plot_no.getText());


                Intent intent;
                intent = new Intent(this, Main2Activity.class);
                intent.putStringArrayListExtra("CRnos", (ArrayList<String>) CRnos);
                intent.putStringArrayListExtra("claimants", (ArrayList<String>) claimants);
                startActivity(intent);
                break;
            default:
                setContentView(R.layout.activity_main);


        }
        databaseAccess.close();
     }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        input_plot_no.setText("");
       String item= parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), item+" village selected",Toast.LENGTH_LONG).show();
      //  Toast.makeText(parent.getContext(), " select village, then  input plot no",Toast.LENGTH_LONG).show();
        payment_details.setVisibility(View.INVISIBLE);detailed.setVisibility(View.INVISIBLE);

        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(this);
        databaseAccess.open();

        List<String> plots = databaseAccess.getPlots(item);
        ArrayAdapter plotList= new ArrayAdapter(this,  R.layout.support_simple_spinner_dropdown_item,plots);
        input_plot_no.setAdapter(plotList);
        input_plot_no.setThreshold(1);
        input_plot_no.setTextColor(Color.RED);

         databaseAccess.close();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
