package hkr.da224a.jobshadow.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.model.Business;
import hkr.da224a.jobshadow.model.Student;
import hkr.da224a.jobshadow.utils.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * The constant VALID_EMAIL_ADDRESS_REGEX.
     */
    /* Regex used for validating email address*/
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private AutoCompleteTextView email_text_field;
    private AutoCompleteTextView password_text_field;
    private CheckBox student_check_box;
    private CheckBox business_check_box;
    private Button register_button;

    private UserRegisterTask mAuthTask = null;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeWidgets();
    }

    private void initializeWidgets() {
        email_text_field = findViewById(R.id.email_text_field);
        password_text_field = findViewById(R.id.password_text_field);
        student_check_box = findViewById(R.id.student_check_box);
        business_check_box = findViewById(R.id.business_check_box);
        register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        if (v == register_button) {
            if (student_check_box.isChecked() && !business_check_box.isChecked()) {
                attemptAccountCreation(Student.getAccountType());
            } else if (business_check_box.isChecked() && !student_check_box.isChecked()) {
                attemptAccountCreation(Business.getAccountType());
            } else {
                Toast.makeText(this, "Please check one box", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Validate email boolean.
     *
     * @param emailStr the email str
     * @return the boolean
     */
    public static boolean isEmailValid(CharSequence emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptAccountCreation(String role) {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        email_text_field.setError(null);
        password_text_field.setError(null);

        // Store values at the time of the login attempt.
        String email = email_text_field.getText().toString();
        String password = password_text_field.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            password_text_field.setError(getString(R.string.error_invalid_password));
            focusView = password_text_field;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            email_text_field.setError(getString(R.string.error_field_required));
            focusView = email_text_field;
            cancel = true;
        } else if (!isEmailValid(email)) {
            email_text_field.setError(getString(R.string.error_invalid_email));
            focusView = email_text_field;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            mAuthTask = new RegisterActivity.UserRegisterTask(email, password, role);
            mAuthTask.execute((Void) null);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private String mRole;

        /**
         * Instantiates a new User login task.
         *
         * @param email    the email
         * @param password the password
         */
        UserRegisterTask(String email, String password, String role) {
            mEmail = email;
            mPassword = password;
            mRole = role;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            if (mRole.equals(Business.getAccountType())) {
                Business business = new Business();
                business.setBusinessName("0");
                business.setPassword(mPassword);
                business.setHqAddress("0");
                business.setContactPhone(0);
                business.setContactName("");
                business.setContactEmail(mEmail);
                business.setVerified(false);
                business.setWebsite("0");

                databaseHelper.addBusiness(business);
                return true;
            } else {
                Student student = new Student();
                student.setPassword(mPassword);
                student.setFirstName("0");
                student.setLastName("0");
                student.setPhoneNumber(00);
                student.setEmailAddress(mEmail);
                student.setCity("0");
                student.setDateOfBirth("0");
                student.setDescription("0");

                databaseHelper.addStudent(student);
                return true;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

                finish();
            } else {
                password_text_field.setError(getString(R.string.error_incorrect_password));
                password_text_field.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;

        }
    }
}
