package com.example.wim.android_jsonparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wim.android_jsonparsing.tags.CustomerTags;
import com.example.wim.android_jsonparsing.util.FileUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView firstName;
    TextView lastName;
    TextView age;
    TextView street;
    TextView city;
    TextView state;
    TextView postalCode;
    TextView home;
    TextView fax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        injectViews();
        parsingJson();
    }

    private void injectViews(){
        firstName = (TextView) findViewById(R.id.first_name);
        lastName = (TextView) findViewById(R.id.last_name);
        age = (TextView) findViewById(R.id.age);
        street = (TextView) findViewById(R.id.street);
        city = (TextView) findViewById(R.id.city);
        state = (TextView) findViewById(R.id.state);
        postalCode = (TextView) findViewById(R.id.postal_code);
        home = (TextView) findViewById(R.id.home);
        fax = (TextView) findViewById(R.id.fax);
    }

    private void parsingJson(){
        String json = FileUtil.copyFromAssets(this.getApplicationContext(), "customer.json");

        try {
            JSONObject jsonObj = new JSONObject(json);

            String objFirstName = jsonObj.getString(CustomerTags.TAG_FIRSTNAME);
            String objLastName = jsonObj.getString(CustomerTags.TAG_LASTNAME);
            int objAge = jsonObj.getInt(CustomerTags.TAG_AGE);

            // Address
            JSONObject objAddress = jsonObj.getJSONObject(CustomerTags.TAG_ADDRESS);

            String objStreet = objAddress.getString(CustomerTags.TAG_STREET_ADDRESS);
            String objCity = objAddress.getString(CustomerTags.TAG_CITY);
            String objState = objAddress.getString(CustomerTags.TAG_STATE);
            String objPostalCode = objAddress.getString(CustomerTags.TAG_POSTAL_CODE);

            // Phone Number
            JSONArray arrPhone = jsonObj.getJSONArray(CustomerTags.TAG_PHONE);

            String[] type = new String[arrPhone.length()];
            String[] number = new String[arrPhone.length()];

            for (int i = 0; i < arrPhone.length(); i++){
                JSONObject objPhone = arrPhone.getJSONObject(i);

                type[i] = objPhone.getString(CustomerTags.TAG_TYPE);
                number[i] = objPhone.getString(CustomerTags.TAG_NUMBER);
            }

            firstName.setText(getResources().getString(R.string.first_name, objFirstName));
            lastName.setText(getResources().getString(R.string.last_name, objLastName));
            age.setText(getResources().getString(R.string.age, objAge));

            street.setText(getResources().getString(R.string.street, objStreet));
            city.setText(getResources().getString(R.string.city, objCity));
            state.setText(getResources().getString(R.string.state, objState));
            postalCode.setText(getResources().getString(R.string.postal_code, objPostalCode));

            home.setText(type[0] +" : "+number[0]);
            fax.setText(type[1] + " : "+number[1]);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
