package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import java.text.DecimalFormat;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;
import java.text.NumberFormat;
import java.util.Locale;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textViewTipAmountResult;
    TextView textViewTotalwithTipResult;
    TextView textViewTotalperResult;
    EditText totalInput;
    EditText numPeopleInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Sets inputs as variables using their id
        totalInput = findViewById(R.id.totalInput);
        numPeopleInput = findViewById(R.id.numPeopleInput);
        //Sets radio group as variable using id
        radioGroup = findViewById(R.id.radioGroup);
        //Sets textView results as variables using their id
        textViewTipAmountResult = findViewById(R.id.textViewTipAmountResult);
        textViewTotalwithTipResult = findViewById(R.id.textViewTotalwithTipResult);
        textViewTotalperResult = findViewById(R.id.textViewTotalperResult);

    }

    //Method that clears all fields when clear button is triggered by user
    public void clear(View v){
        radioGroup.clearCheck();
        textViewTipAmountResult.setText("");
        textViewTotalwithTipResult.setText("");
        textViewTotalperResult.setText("");
        totalInput.setText("");
        numPeopleInput.setText("");
    }

    //Method that is executed when Radio button is clicked, takes bill total as input and performs
    //tax calculation resulting in the tip amount and total being displayed to user
    public void tipTotal(View v){
        //Assigning user input
        String billTotalWithTax = totalInput.getText().toString();

        //Setting format to dollar amount
        NumberFormat num = NumberFormat.getCurrencyInstance(Locale.US);

        //Initializing variables used in calculation
        double totalWithTip;
        double totalTip = 0;

        //Establishing a decimal format
        DecimalFormat x = new DecimalFormat("##.00");

        //Series of if statements determine which radio button was clicked by the user resulting in
        //the appropriate tip percentage being applied to the total bill
        if(v.getId() == R.id.buttonTip12){
            totalTip = Double.parseDouble(x.format((Double.parseDouble(billTotalWithTax)) * 0.12));
        }
        else if(v.getId() == R.id.buttonTip15){
            totalTip = Double.parseDouble(x.format((Double.parseDouble(billTotalWithTax)) * 0.15));
        }
        else if(v.getId() == R.id.buttonTip18){
            totalTip = Double.parseDouble(x.format((Double.parseDouble(billTotalWithTax)) * 0.18));
        }
        else if(v.getId() == R.id.buttonTip20){
            totalTip = Double.parseDouble(x.format((Double.parseDouble(billTotalWithTax)) * 0.20));
        }

        //Following the if statements the total amount including tip is calculated using the tip amount and
        //bill amount. From there the textViews the user gets is the Tip amount in dollars and the total with Tip in dollars
        totalWithTip = (Double.parseDouble(billTotalWithTax)) + totalTip;
        textViewTipAmountResult.setText(num.format(totalTip));
        textViewTotalwithTipResult.setText(num.format(totalWithTip));

    }

    //Method that is executed when user clicks Go button resulting in the Calculation of the total dollar amount
    //per person
    public void totalPerPerson(View v){
        //Sets the number of people inserted by user to an integer
        int numPeople = Integer.parseInt(numPeopleInput.getText().toString());

        //If # of people is 0 user is sent Toast message
        if (numPeople == 0){
            Toast.makeText(this, "Enter Value Greater than 0",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //Takes previously calculated total with tip not accounting for $ and is stored as a double
        double totalWithTax = Double.parseDouble((textViewTotalwithTipResult.getText().toString()).substring(1));

        //Performs total amount per person using num people and total with tip amount
        double totalAmtPerPerson = (totalWithTax/numPeople);

        NumberFormat num = NumberFormat.getCurrencyInstance(Locale.US);

        //Reformats and returns the total amount per person in dollar
        textViewTotalperResult.setText(num.format(totalAmtPerPerson));
    }

    //Method that displays a toast message on what radio button that was selected by the user
    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected Tip %: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }
}