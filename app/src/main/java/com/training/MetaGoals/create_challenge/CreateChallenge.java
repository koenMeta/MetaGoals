package com.training.MetaGoals.create_challenge;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.training.MetaGoals.R;

import java.util.Calendar;

public class CreateChallenge extends AppCompatActivity {

    // ToDo
    // Listener for buttons
    // Handler class to submit challenge to backend
    // save user input locally

    private Bundle bundle;

    private static TextInputEditText deadlineInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_challenge);
        bundle = savedInstanceState;
        deadlineInput = findViewById(R.id.create_challenge_input_deadline);

//        configureAddPhaseBtn();
//        configurePublishChallengeBtn();
//        configureDiscardChallengeBtn();
        configureSetDeadline();
    }


    // date for deadline --> ToDo test functionality
    private void configureSetDeadline() {
        TextInputEditText setDeadline = findViewById(R.id.create_challenge_input_deadline);
        setDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Deadline clicked", Toast.LENGTH_SHORT);
                // open date picker
                showDatePickerDialog(v);
            }
        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


//    private void configureAddPhaseBtn() {
//        Button addPhaseBtn = findViewById(R.id.create_challenge_btn_add_phase);
//        addPhaseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // add phase
//            }
//        });
//    }
//
//    private void configurePublishChallengeBtn() {
//        Button addPhaseBtn = findViewById(R.id.create_challenge_btn_create);
//        addPhaseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // publish challenge
//            }
//        });
//    }
//
//    private void configureDiscardChallengeBtn() {
//        Button addPhaseBtn = findViewById(R.id.create_challenge_btn_discard);
//        addPhaseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // open dialog
//
//                // discard challenge
//            }
//        });
//    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            deadlineInput.setText(String.valueOf(day)+"."+String.valueOf(month)+"."+String.valueOf(year));
        }
    }

}
