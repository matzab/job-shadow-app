package hkr.da224a.jobshadow.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.model.Business;
import hkr.da224a.jobshadow.model.Student;
import hkr.da224a.jobshadow.utils.FirebaseDatabaseHelper;
import hkr.da224a.jobshadow.utils.SQLiteDatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    /**
     * The constant VALID_EMAIL_ADDRESS_REGEX.
     */
    /* Regex used for validating email address*/
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private AutoCompleteTextView email_text_field;
    private AutoCompleteTextView password_text_field;
    private AutoCompleteTextView f_name_text_field;
    private AutoCompleteTextView l_name_text_field;
    private AutoCompleteTextView phone_text_field;
    private AutoCompleteTextView city_text_field;
    private AutoCompleteTextView dob_text_field;
    private AutoCompleteTextView desc_text_field;
    private AutoCompleteTextView search_program_text_field;
    private AutoCompleteTextView ice_text_field;
    private AutoCompleteTextView degree_program_text_field;
    private CheckBox student_check_box;
    private CheckBox business_check_box;
    private Button register_button;

    private SQLiteDatabaseHelper SQLiteDatabaseHelper;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeWidgets();
    }

    private void initializeWidgets() {
        email_text_field = findViewById(R.id.email_text_field);
        password_text_field = findViewById(R.id.password_text_field);
        f_name_text_field = findViewById(R.id.f_name_text_field);
        l_name_text_field = findViewById(R.id.l_name_text_field);
        phone_text_field = findViewById(R.id.phone_text_field);
        city_text_field = findViewById(R.id.city_text_field);
        dob_text_field = findViewById(R.id.dob_text_field);
        desc_text_field = findViewById(R.id.desc_text_field);
        search_program_text_field = findViewById(R.id.search_program_text_field);
        ice_text_field = findViewById(R.id.ice_text_field);
        degree_program_text_field = findViewById(R.id.degree_program_text_field);


        student_check_box = findViewById(R.id.student_check_box);
        student_check_box.setOnCheckedChangeListener(this);
        business_check_box = findViewById(R.id.business_check_box);
        business_check_box.setOnCheckedChangeListener(this);
        register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(this);

        SQLiteDatabaseHelper = new SQLiteDatabaseHelper(getApplicationContext());
        pd = new ProgressDialog(RegisterActivity.this);
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
        return password.length() >= 6;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptAccountCreation(String role) {

        // Reset errors.
        email_text_field.setError(null);
        password_text_field.setError(null);
        f_name_text_field.setError(null);
        l_name_text_field.setError(null);
        phone_text_field.setError(null);
        city_text_field.setError(null);
        dob_text_field.setError(null);
        desc_text_field.setError(null);
        search_program_text_field.setError(null);
        ice_text_field.setError(null);
        degree_program_text_field.setError(null);

        boolean cancel = false;
        View focusView = null;

        if (role.equalsIgnoreCase(Student.getAccountType())) {
            // Store values at the time of the login attempt.

            String emailAddress = email_text_field.getText().toString();
            String password = password_text_field.getText().toString();
            String firstName = f_name_text_field.getText().toString();
            String lastName = l_name_text_field.getText().toString();
            String phoneNumber = phone_text_field.getText().toString();
            String city = city_text_field.getText().toString();
            String dateOfBirth = dob_text_field.getText().toString();
            String inCaseOfEmergency = ice_text_field.getText().toString();
            String degreeProgram = degree_program_text_field.getText().toString();
            String searchPrefs = search_program_text_field.getText().toString();
            String description = desc_text_field.getText().toString();

            // Check for a valid email address.
            if (TextUtils.isEmpty(emailAddress) || !isEmailValid(emailAddress)) {
                email_text_field.setError(getString(R.string.error_field_required));
                focusView = email_text_field;
                cancel = true;
            }

            if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
                password_text_field.setError("Incorrect Password");
                focusView = password_text_field;
                cancel = true;
            }

            if (TextUtils.isEmpty(firstName)) {
                f_name_text_field.setError("Incorrect First Name");
                focusView = f_name_text_field;
                cancel = true;
            }

            if (TextUtils.isEmpty(lastName)) {
                l_name_text_field.setError("Incorrect Last Name");
                focusView = f_name_text_field;
                cancel = true;
            }

            if (TextUtils.isEmpty(phoneNumber)) {
                phone_text_field.setError("Incorrect Phone Number");
                focusView = f_name_text_field;
                cancel = true;
            }

            if (TextUtils.isEmpty(city)) {
                city_text_field.setError("Incorrect City");
                focusView = f_name_text_field;
                cancel = true;
            }

            if (TextUtils.isEmpty(dateOfBirth)) {
                dob_text_field.setError("Incorrect DOB");
                focusView = dob_text_field;
                cancel = true;
            }

            if (TextUtils.isEmpty(inCaseOfEmergency)) {
                dob_text_field.setError("Incorrect ICE");
                focusView = dob_text_field;
                cancel = true;
            }

            if (TextUtils.isEmpty(degreeProgram)) {
                degree_program_text_field.setError("Incorrect Degree Program");
                focusView = degree_program_text_field;
                cancel = true;
            }

            if (TextUtils.isEmpty(searchPrefs)) {
                search_program_text_field.setError("Incorrect Search Preferences");
                focusView = search_program_text_field;
                cancel = true;
            }

            if (TextUtils.isEmpty(description)) {
                degree_program_text_field.setError("Incorrect Description");
                focusView = degree_program_text_field;
                cancel = true;
            }

            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else {
                pd.show();
                Student student = new Student();
                student.setEmailAddress(emailAddress);
                student.setPassword(password);
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setPhoneNumber(Long.parseLong(phoneNumber));
                student.setInCaseOfEmergency(Long.parseLong(inCaseOfEmergency));
                student.setDescription(description);
                student.setSearchPrefs(searchPrefs);
                student.setDegreeProgram(degreeProgram);
                student.setCity(city);
                student.setDateOfBirth(dateOfBirth);
                student.setDescription(description);

                if (SQLiteDatabaseHelper.addStudent(student)) {
                    pd.dismiss();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    pd.dismiss();
                    SQLiteDatabaseHelper.deleteStudent(student);
                    Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).delete();
                    FirebaseDatabaseHelper.deleteStudent(student);
                    Toast.makeText(RegisterActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            pd.show();
            String emailAddress = email_text_field.getText().toString();
            String password = password_text_field.getText().toString();
            String businessName = f_name_text_field.getText().toString();
            String contactName = l_name_text_field.getText().toString();
            String phoneNumber = phone_text_field.getText().toString();
            String HQAddress = city_text_field.getText().toString();
            String verified = dob_text_field.getText().toString();
            String website = desc_text_field.getText().toString();

            Business business = new Business();
            business.setContactEmail(emailAddress);
            business.setPassword(password);
            business.setBusinessName(businessName);
            business.setHqAddress(HQAddress);
            business.setContactPhone(Long.parseLong(phoneNumber));
            business.setContactName(contactName);
            business.setVerified(verified.equalsIgnoreCase("TRUE"));
            business.setWebsite(website);

            if (SQLiteDatabaseHelper.addBusiness(business)) {
                pd.dismiss();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                pd.dismiss();
                SQLiteDatabaseHelper.deleteBusiness(business);
                Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).delete();
                FirebaseDatabaseHelper.deleteBusiness(business);
                Toast.makeText(RegisterActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == student_check_box) {
            if (isChecked) {
                business_check_box.setChecked(false);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                finish();
                overridePendingTransition(0, 0);
            }
        }

        if (buttonView == business_check_box) {
            if (isChecked) {
                student_check_box.setChecked(false);
                f_name_text_field.setHint("Business Name");
                l_name_text_field.setHint("Contact Name");
                city_text_field.setHint("HQ Address");
                dob_text_field.setHint("Verified? TRUE / FALSE");
                desc_text_field.setHint("Website");
                ice_text_field.setVisibility(View.INVISIBLE);
                search_program_text_field.setVisibility(View.INVISIBLE);
                degree_program_text_field.setVisibility(View.INVISIBLE);
            }
        }
    }
}
