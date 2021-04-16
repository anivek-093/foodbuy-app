package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView name_text, mail_text;
    Button logout_btn;
    public String visible;
    public EditText visibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        logout_btn = findViewById(R.id.logoutbtn);
        name_text = findViewById(R.id.nametext);
        mail_text = findViewById(R.id.mailtext);

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

        Spinner spinner = findViewById(R.id.user_menu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selection = adapterView.getItemAtPosition(i).toString();    //"Selection" stores spinner value
        Toast.makeText(this, selection, Toast.LENGTH_SHORT).show();
        visibility=findViewById(R.id.storeName);
        if(selection.equals("Consumer")){
            visibility.setVisibility(View.GONE);
        }else{
            visibility.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}