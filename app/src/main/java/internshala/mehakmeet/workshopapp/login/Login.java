package internshala.mehakmeet.workshopapp.login;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import internshala.mehakmeet.workshopapp.Activities.MainActivity;
import internshala.mehakmeet.workshopapp.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

private final AppCompatActivity activity = Login.this;

public static int HAS_LOGGED_IN=0;

private NestedScrollView nestedScrollView;

private TextInputLayout textInputLayoutEmail;
private TextInputLayout textInputLayoutPassword;

private TextInputEditText textInputEditTextEmail;
private TextInputEditText textInputEditTextPassword;

private AppCompatButton appCompatButtonLogin;

private AppCompatTextView textViewLinkRegister;

private InputValidation inputValidation;
private DatabaseHelper databaseHelper;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
        }
private void initViews(){
        nestedScrollView =findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword =findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = findViewById(R.id.textViewLinkRegister);
        }

private void initListeners(){
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
        }

private void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
        }

@Override
public void onClick(View v){
        switch (v.getId()){
        case R.id.appCompatButtonLogin:
        verifyFromSQLite();
        break;
        case R.id.textViewLinkRegister:
        Intent intentRegister = new Intent(getApplicationContext(), Register.class);
        startActivity(intentRegister);
        break;
        }
        }

private void verifyFromSQLite(){
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
        return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
        return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
        return;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
        , textInputEditTextPassword.getText().toString().trim())) {
        Intent accountsIntent = new Intent(activity, MainActivity.class);
        accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
        emptyInputEditText();
        HAS_LOGGED_IN=1;
        MainActivity.navigationView.getMenu().getItem(2).setTitle("Log Out");
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("login_status", HAS_LOGGED_IN).apply();
        startActivity(accountsIntent);
        finish();
        } else {
        Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
        }

private void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        }
}

