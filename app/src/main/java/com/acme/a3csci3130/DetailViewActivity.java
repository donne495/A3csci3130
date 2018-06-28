package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DetailViewActivity extends Activity {

    private EditText bNumField, nameField, addressField;
    private Spinner pBField, provinceField;
    private MyApplicationData appState;
    Contact receivedPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());

        //Populate dropdown menus
        final Spinner primaryB = (Spinner) findViewById(R.id.primaryBusiness);
        ArrayAdapter<CharSequence> primaryBAdapter = ArrayAdapter.createFromResource(this, R.array.business_array, android.R.layout.simple_spinner_item);
        primaryBAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        primaryB.setAdapter(primaryBAdapter);

        final Spinner provSpinner = (Spinner) findViewById(R.id.province);
        ArrayAdapter<CharSequence> provAdapter = ArrayAdapter.createFromResource(this, R.array.province_array, android.R.layout.simple_spinner_item);
        provAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provSpinner.setAdapter(provAdapter);

        bNumField = (EditText) findViewById(R.id.businessNumber);
        nameField = (EditText) findViewById(R.id.name);
        pBField = (Spinner) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (Spinner) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            int pBNum = matchPrimarySelection(receivedPersonInfo.primaryBusiness);
            int provinceNum = matchProvinceSelection(receivedPersonInfo.province);

            bNumField.setText(receivedPersonInfo.businessNumber);
            nameField.setText(receivedPersonInfo.name);
            pBField.setSelection(pBNum);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setSelection(provinceNum);
        }
    }

    public void updateContact(View v){
        String existingID = receivedPersonInfo.uid;
        String businessNum = bNumField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = pBField.getSelectedItem().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getSelectedItem().toString();

        Contact person = new Contact(existingID, businessNum, name, primaryBusiness, address, province);

        appState.firebaseReference.child(existingID).setValue(person);

        finish();
    }

    public void eraseContact(View v)
    {
        String existingID = receivedPersonInfo.uid;
        appState.firebaseReference.child(existingID).removeValue();
        finish();
    }

    public static int matchPrimarySelection(String selection){
        int result;
        if(selection.equals("Fisher")){
            result = 1;
        }
        else if(selection.equals("Distributor")){
            result = 2;
        }
        else if(selection.equals("Processor")){
            result = 3;
        }
        else if(selection.equals("Fish Monger")){
            result = 4;
        }
        else{
            result = 0;
        }
        return result;
    }

    public static int matchProvinceSelection(String selection){
        int result;
        if(selection.equals("AB")){
            result = 1;
        }
        else if(selection.equals("BC")){
            result = 2;
        }
        else if(selection.equals("MB")){
            result = 3;
        }
        else if(selection.equals("NB")){
            result = 4;
        }
        else if(selection.equals("NL")){
            result = 5;
        }
        else if(selection.equals("NS")){
            result = 6;
        }
        else if(selection.equals("NT")){
            result = 7;
        }
        else if(selection.equals("NU")){
            result = 8;
        }
        else if(selection.equals("ON")){
            result = 9;
        }
        else if(selection.equals("PE")){
            result = 10;
        }
        else if(selection.equals("QC")){
            result = 11;
        }
        else if(selection.equals("SK")){
            result = 12;
        }
        else if(selection.equals("YT")){
            result = 13;
        }
        else if(selection.equals(" ")){
            result = 14;
        }
        else{
            result = 0;
        }
        return result;
    }
}
