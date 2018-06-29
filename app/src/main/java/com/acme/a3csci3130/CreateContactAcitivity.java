package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText bNField, nameField, addressField;
    private Spinner pBField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
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

        submitButton = (Button) findViewById(R.id.submitButton);
        bNField = (EditText) findViewById(R.id.businessNumber);
        nameField = (EditText) findViewById(R.id.name);
        pBField = (Spinner) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (Spinner) findViewById(R.id.province);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String businessNum = bNField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = pBField.getSelectedItem().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getSelectedItem().toString();

        Contact person = new Contact(personID, businessNum, name, primaryBusiness, address, province);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
