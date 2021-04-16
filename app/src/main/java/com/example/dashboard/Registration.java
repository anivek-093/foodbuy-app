package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dashboard.data.Preferences;
import com.example.dashboard.model.User;
import com.example.dashboard.model.UserResponseObject;
import com.example.dashboard.network.DataService;
import com.example.dashboard.network.RetrofitClientInstance;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private TextView name_text, mail_text, doneButton;
    private EditText storeName, userName, street, city, state, pincode, phone;
    private String userType = "Customer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name_text = findViewById(R.id.nametext);
        mail_text = findViewById(R.id.mailtext);
        userName = findViewById(R.id.username);
        street = findViewById(R.id.street);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        pincode = findViewById(R.id.pincode);
        phone = findViewById(R.id.phone);
        doneButton = findViewById(R.id.done_text_activity_registration);

        int loginOption = LoginPage.signInOption;
        if(loginOption==2) {
            Intent intent = getIntent();
            if (intent == null) {
                Toast.makeText(this, "intent received == null", Toast.LENGTH_SHORT).show();
            }
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                String name = bundle.getString("name");
                String mail = bundle.getString("mail");

                name_text.setText("Name: " + name);
                mail_text.setText("Email: " + mail);
            } else {
                name_text.setText("");
                mail_text.setText("");
            }
        }else{
            GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
            if(signInAccount!=null){
                name_text.setText(signInAccount.getDisplayName());
                mail_text.setText(signInAccount.getEmail());
            } else{
                name_text.setText("2");
                mail_text.setText("2");
            }
        }

        Spinner spinner = findViewById(R.id.user_menu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndRegisterUser();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        userType = adapterView.getItemAtPosition(i).toString();    //"Selection" stores spinner value
        Toast.makeText(this, userType, Toast.LENGTH_SHORT).show();
        storeName=findViewById(R.id.storeName);
        if(userType.equals("Customer")){
            storeName.setVisibility(View.GONE);
        }else{
            storeName.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void validateAndRegisterUser() {
        User user = new User(mail_text.getText().toString(), userName.getText().toString(), userType, storeName.getText().toString(), phone.getText().toString(), street.getText().toString(), pincode.getText().toString(), city.getText().toString(), state.getText().toString());
        postNewUser(user);
    }

    private void postNewUser(User user) {
        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<UserResponseObject> call = service.postNewUser(user.userType, user);//(user.userType, user.email, user.name, user.storeName, user.phone, user.address.street, user.address.pincode, user.address.city, user.address.state);

        call.enqueue(new Callback<UserResponseObject>() {
            @Override
            public void onResponse(Call<UserResponseObject> call, Response<UserResponseObject> response) {

                if(response.body().error){
                    Log.e("Registration.java", "onResponse: " + response.body().message);
                    Toast.makeText(Registration.this, "Error adding user", Toast.LENGTH_SHORT).show();
                }
                else {
                    Preferences preferences = Preferences.getPreferences(getApplicationContext());
                    preferences.saveUser(user);

                    Intent intent = new Intent(Registration.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UserResponseObject> call, Throwable t) {
                Log.e("Registration.java", "onFailure: " + t.getMessage());
                Toast.makeText(Registration.this, "Error adding user", Toast.LENGTH_SHORT).show();
            }
        });
    }
}