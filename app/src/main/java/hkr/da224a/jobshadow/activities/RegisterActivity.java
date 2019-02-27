package hkr.da224a.jobshadow.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import hkr.da224a.jobshadow.R;

public class RegisterActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final LinearLayout layout = (LinearLayout) findViewById(R.id.register_linear_layout);
        final CheckBox studentCheckBox = (CheckBox) findViewById(R.id.student_check_box);
        final CheckBox businessCheckBox = (CheckBox) findViewById(R.id.business_check_box);
        final Button registerButton = (Button) findViewById(R.id.register_button);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);

        studentCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //Here add change to student data
                    //Also uncheck business checkbox
                    System.out.println("studentCheckBox is now checked.");
                    businessCheckBox.setChecked(false);

                    TextView preferenceText = new TextView(context);
                    preferenceText.setText(getResources().getString(R.string.preference_text));

                    Spinner preferenceSpinner1 = new Spinner(context);
                    Spinner preferenceSpinner2 = new Spinner(context);
                    Spinner preferenceSpinner3 = new Spinner(context);

                    View view = new View(context);
                    params.setMargins(70,0,70,0);
                    view.setLayoutParams(params);


                    TextView locationText = new TextView(context);
                    locationText.setText(getResources().getString(R.string.location_text));
                    EditText locationEditText = new EditText(context);


                    layout.addView(preferenceText);
                    layout.addView(preferenceSpinner1);
                    layout.addView(preferenceSpinner2);
                    layout.addView(preferenceSpinner3);
                    //layout.addView(view);
                    layout.addView(locationText);
                    layout.addView(locationEditText);
                    //layout.addView(view);

                }
            }
        });

        businessCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //Here add change to business data
                    //Also uncheck student checkbox
                    System.out.println("businessCheckBox is now checked.");
                    studentCheckBox.setChecked(false);

                    for(int i = 7; i < 13; i++){
                        layout.removeViewAt(7);
                    }
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                RegisterActivity.this.startActivity(intent);

            }
        });

    }
}
