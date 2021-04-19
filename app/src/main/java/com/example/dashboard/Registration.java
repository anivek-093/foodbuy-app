package com.example.dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dashboard.data.Preferences;
import com.example.dashboard.model.User;
import com.example.dashboard.model.UserResponseObject;
import com.example.dashboard.network.DataService;
import com.example.dashboard.network.RetrofitClientInstance;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity{
    private TextView name_text, mail_text, storename_head;
    private EditText storeName, userName, street, city, state, pincode, phone;
    private String userType = "Customer";
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private FloatingActionButton doneButton;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name_text = findViewById(R.id.nametext);
        mail_text = findViewById(R.id.mailtext);
        userName = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        doneButton = findViewById(R.id.done_text_activity_registration);
        storename_head = findViewById(R.id.storeName_head);
        radioGroup = findViewById(R.id.userGroup);
        storeName = findViewById(R.id.storeName);

        progressDialog = new ProgressDialog(Registration.this);
        progressDialog.setMessage("Registering...");

        int loginOption = LoginPage.signInOption;
        if(loginOption == 2) {
            Intent intent = getIntent();
            if (intent == null) {
                Toast.makeText(this, "intent received == null", Toast.LENGTH_SHORT).show();
            }
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                String name = bundle.getString("name");
                String mail = bundle.getString("mail");

                name_text.setText(name);
                mail_text.setText(mail);
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


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndRegisterUser();
            }
        });
    }


    public void radioButtonClicked(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
        if(radioButton.getText().equals("Consumer"))
        {
            storeName.setVisibility(View.GONE);
            storename_head.setVisibility(View.GONE);
        }else{
            userType = radioButton.getText().toString();
            storeName.setVisibility(View.VISIBLE);
            storename_head.setVisibility(View.VISIBLE);
        }
    }

    private void validateAndRegisterUser() {
        User user = new User(mail_text.getText().toString(), userName.getText().toString(), userType.toLowerCase(), storeName.getText().toString(), phone.getText().toString(), street.getText().toString(), pincode.getText().toString(), city.getText().toString(), state.getText().toString());
        postNewUser(user);
    }

    private void postNewUser(User user) {
        progressDialog.show();

        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<UserResponseObject> call = service.postNewUser(user.userType, user);//(user.userType, user.email, user.name, user.storeName, user.phone, user.address.street, user.address.pincode, user.address.city, user.address.state);

        call.enqueue(new Callback<UserResponseObject>() {
            @Override
            public void onResponse(Call<UserResponseObject> call, Response<UserResponseObject> response) {
                progressDialog.dismiss();
                if(response.body().error){
                    Log.e("Registration.java", "onResponse: " + response.body().message);
                    Toast.makeText(Registration.this, "Error adding user", Toast.LENGTH_SHORT).show();
                }
                else {
                    Preferences preferences = Preferences.getPreferences(getApplicationContext());
                    preferences.saveUser(response.body().user);

                    Toast.makeText(Registration.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Registration.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UserResponseObject> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("Registration.java", "onFailure: " + t.getMessage());
                Toast.makeText(Registration.this, "Error adding user", Toast.LENGTH_SHORT).show();
            }
        });
    }
}