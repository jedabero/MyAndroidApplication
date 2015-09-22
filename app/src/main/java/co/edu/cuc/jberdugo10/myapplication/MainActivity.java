package co.edu.cuc.jberdugo10.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText mFirstNumberEditText;
    private EditText mSecondNumberEditText;
    private TextView mResultTextView;
    private RadioGroup mOpRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstNumberEditText = (EditText) findViewById(R.id.first_number_field);
        mSecondNumberEditText = (EditText) findViewById(R.id.second_number_field);
        mResultTextView = (TextView) findViewById(R.id.result_text);
        mOpRadioGroup = (RadioGroup) findViewById(R.id.op_radio_group);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
            //return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    public void doOperation(View view) {
        String firstNumberText = mFirstNumberEditText.getText().toString().trim();
        if (!isFieldValid(firstNumberText, mFirstNumberEditText)) {
            return;
        }
        String secondNumberText = mSecondNumberEditText.getText().toString().trim();
        if (!isFieldValid(secondNumberText, mSecondNumberEditText)) {
            return;
        }

        double firstNumber = Double.parseDouble(firstNumberText);
        double secondNumber = Double.parseDouble(secondNumberText);

        int radioButton = mOpRadioGroup.getCheckedRadioButtonId();
        double result = 0;
        switch (radioButton) {
            case R.id.op_add_radioButton:
                result = firstNumber + secondNumber;
                break;
            case R.id.op_subtract_radioButton:
                result = firstNumber - secondNumber;
                break;
            case R.id.op_multiply_radioButton:
                result = firstNumber * secondNumber;
                break;
            case R.id.op_divide_radioButton:
                if (secondNumber == 0.0d) {
                    mSecondNumberEditText.requestFocus();
                    mSecondNumberEditText.setError(getResources().getString(R.string.second_field_is_zero_error));
                    return;
                }
                result = firstNumber / secondNumber;
                break;
            default:
                break;
        }

        mResultTextView.setText("" + result);
    }

    private boolean isFieldValid(String value, EditText numberField) {
        boolean isValid = !TextUtils.isEmpty(value);
        if (!isValid) {
            numberField.requestFocus();
            numberField.setError(getResources().getString(R.string.empty_field_error));
        }
        return isValid;
    }

}
