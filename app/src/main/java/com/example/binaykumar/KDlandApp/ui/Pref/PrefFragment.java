package com.example.binaykumar.KDlandApp.ui.Pref;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.binaykumar.KDlandApp.DatabaseOpenHelper;
import com.example.binaykumar.KDlandApp.R;
import com.example.binaykumar.KDlandApp.ui.Plot.PlotFragment;


public class PrefFragment extends Fragment  implements AdapterView.OnItemSelectedListener {


    PlotFragment.OnMessage onMessage;

    public  interface OnMessage {

        public  void onMessageSent ( String message);


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


    private PrefViewModel mViewModel;
    private Spinner projAct;
 public  String actProj ="";

    public static PrefFragment newInstance() {
        return new PrefFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View root= inflater.inflate(R.layout.pref_fragment, container, false);

        Spinner projAct = (Spinner) root.findViewById(R.id.spn4);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.projectAct, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        projAct.setAdapter(adapter);
        projAct.setOnItemSelectedListener(this);
       projAct.setPrompt(" select the project and act");
return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PrefViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    actProj= parent.getSelectedItem().toString();
        System.out.println(actProj+"jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        String message=actProj;
        onMessage.onMessageSent((String) message);
      //  this.=new DatabaseOpenHelper(Context);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
