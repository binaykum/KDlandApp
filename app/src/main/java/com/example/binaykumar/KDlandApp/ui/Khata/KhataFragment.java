package com.example.binaykumar.KDlandApp.ui.Khata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;

import com.example.binaykumar.KDlandApp.DatabaseAccess;
import com.example.binaykumar.KDlandApp.Main2Activity;
import com.example.binaykumar.KDlandApp.R;
import com.example.binaykumar.KDlandApp.ui.Plot.PlotFragment;

import java.util.ArrayList;
import java.util.List;

public class KhataFragment extends Fragment  implements View.OnClickListener , AdapterView.OnItemSelectedListener{

    PlotFragment.OnMessage onMessage;
    public  interface OnMessage {
        public  void onMessageSent (String message);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity =(Activity) context;
        try {
            onMessage= (PlotFragment.OnMessage) activity;
        }catch ( ClassCastException e){
            throw new ClassCastException(activity.toString()+"message from onAttach method");


        }
    }

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



    private KhataViewModel khataViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        khataViewModel =
                ViewModelProviders.of(this).get(KhataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_khata, container, false);

        if (container != null)  {
            Toast.makeText(getActivity(),"Developed by CBA team in-house", Toast.LENGTH_LONG).show();

            // Toast.makeText(this,"    v1:"+"\n"+"upto 2017", Toast.LENGTH_SHORT).show();


            // input_plot_no=(EditText) findViewById(R.id.editText2);


            // input_plot_no=(EditText) findViewById(R.id.editText2);
            village_name=(TextView) root.findViewById(R.id.textView3);
            plot_no=(TextView)root.findViewById(R.id.textView4);
            output=(TextView)root.findViewById(R.id.textView5);
            search_plotNo=(Button)root.findViewById(R.id.btn1);
            village_list=(Spinner) root.findViewById(R.id.spn1);
            search_plotNo.setOnClickListener(this);
            village_list.setOnItemSelectedListener(this);
            payment_details=(Button)root.findViewById(R.id.btn2) ;
            payment_details.setOnClickListener(this);
            input_plot_no=(AutoCompleteTextView) root.findViewById(R.id.act1);

// TODO: Implement the ViewModel            output.setVisibility(View.INVISIBLE);

            detailed=(Button)root.findViewById(R.id.btn3);
            detailed.setOnClickListener(this);
            //   search1=(Button)findViewById(R.id.btn4);
            // back2=(Button)findViewById(R.id.btn5);

            payment_details.setVisibility(View.INVISIBLE);
            detailed.setVisibility(View.INVISIBLE);

            //spinner code

            DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getActivity());
            databaseAccess.open();
            //System.out.println(databaseAccess.getVillages());
            List<String> villages = databaseAccess.getVillages();



            databaseAccess.close();

            //String[] villageList= {"Pagar","Jordag", "Chatti Bariatu" };
            ArrayAdapter villList= new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,villages);
            villList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            village_list.setAdapter(villList);



            // Log.i("message to binay ","spinner successful");
            //TODO: Implement the ViewModel         output.setMovementMethod((new ScrollingMovementMethod()));
        };










        // below written originally for livedata viewModel.
        final TextView textView = root.findViewById(R.id.textView3);
        khataViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
             //   textView.setText(s);
            }
        });
        return root;
    }


    @Override
    public void onClick(View v) {
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        if (input_plot_no.getText().length() == 0) {
            Toast.makeText(getContext(), "Pl enter plot no", Toast.LENGTH_SHORT).show();
        } else {
            // TODO:  output.setVisibility(View.VISIBLE);
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
            databaseAccess.open();
        //    Log.i("message to binay ", "database opening successful");
            switch (v.getId()) {
                case R.id.btn1:
                    payment_details.setVisibility(View.INVISIBLE);
                    detailed.setVisibility(View.INVISIBLE);
                    CharSequence message =((CharSequence) databaseAccess.res4((String) village_list.getSelectedItem().toString(), input_plot_no.getText()));

                    //   String message ="binay kumar";
                    onMessage.onMessageSent((String) message);

                    // TODO:           output.setText((CharSequence) databaseAccess.res((String) village_list.getSelectedItem().toString(), input_plot_no.getText()));

                    if (!databaseAccess.res4((String) village_list.getSelectedItem().toString(), input_plot_no.getText()).contains("paidArea:-null")) {
                        payment_details.setVisibility(View.VISIBLE);
                        detailed.setVisibility(View.VISIBLE);
                    }
                    if (databaseAccess.res4((String) village_list.getSelectedItem().toString(), input_plot_no.getText()).contains("Anil Kumar")) {
                        payment_details.setVisibility(View.INVISIBLE);
                        detailed.setVisibility(View.INVISIBLE);
                    }
                    databaseAccess.close();
                    break;
                case R.id.btn2:
                    message=((CharSequence) databaseAccess.res5((String) village_list.getSelectedItem().toString(), input_plot_no.getText()));
                    onMessage.onMessageSent((String) message);

// TODO:      this output we have to take to resulttext in result fragment.
                    //List<String> claimants = databaseAccess.getClaimants();

                    databaseAccess.close();

                    break;
                case R.id.btn3:
//TODO this output should open new fragmetn again to be made. but it is going to new activity so let it go.
                    List<String> CRnos = databaseAccess.getCRnos1((String) village_list.getSelectedItem().toString(), input_plot_no.getText());
                    List<String> claimants = databaseAccess.getClaimants1((String) village_list.getSelectedItem().toString(), input_plot_no.getText());


                    Intent intent;
                    intent = new Intent(getActivity(), Main2Activity.class); // to change this main2Activity
                    intent.putStringArrayListExtra("CRnos", (ArrayList<String>) CRnos);
                    intent.putStringArrayListExtra("claimants", (ArrayList<String>) claimants);
                    startActivity(intent);
                    break;
                default:
                    //TODO   setContentView(R.layout.activity_main);

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

        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();

        List<String> plots = databaseAccess.getKhatas(item);
        ArrayAdapter plotList= new ArrayAdapter(getActivity(),  R.layout.support_simple_spinner_dropdown_item,plots);
        input_plot_no.setAdapter(plotList);
        input_plot_no.setThreshold(1);
        input_plot_no.setTextColor(Color.RED);

        databaseAccess.close();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}