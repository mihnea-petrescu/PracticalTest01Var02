package ro.pub.cs.systems.eim.practicaltest01var02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PracticalTest01Var02MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private EditText firstNumberEditText, secondNumberEditText;
    private Button plusButton, minusButton;
    private Button navigateToSecondaryActivityButton;

    private GenericButtonClickListener genericButtonClickListener = new GenericButtonClickListener();
    private class GenericButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int firstNumber = Integer.valueOf(firstNumberEditText.getText().toString());
            int secondNumber = Integer.valueOf(secondNumberEditText.getText().toString());

            switch(view.getId()) {
                case R.id.plus_button:
                    resultTextView.setText(Constants.TEXT_VIEW_PLUS_FORMAT.
                            replace("xxx", String.valueOf(firstNumber)).
                            replace("xxy", String.valueOf(secondNumber)).
                            replace("xyy", String.valueOf(firstNumber + secondNumber)));
                    break;

                case R.id.minus_button:
                    resultTextView.setText(Constants.TEXT_VIEW_MINUS_FORMAT.
                            replace("xxx", String.valueOf(firstNumber)).
                            replace("xxy", String.valueOf(secondNumber)).
                            replace("xyy", String.valueOf(firstNumber - secondNumber)));
                    break;

                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02SecondaryActivity.class);
                    int operationResult = Integer.parseInt(resultTextView.getText().toString().split("= ")[1]);
                    intent.putExtra(Constants.OPERATION_RESULT, operationResult);

                    String operation = resultTextView.getText().toString().split(" ")[1];
                    if (operation.equals("+"))
                        intent.putExtra(Constants.OPERATION_PLUS, operation);
                    else
                        intent.putExtra(Constants.OPERATION_MINUS, operation);
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_main);

        resultTextView = (TextView)findViewById(R.id.result_text_view);
        firstNumberEditText = (EditText)findViewById(R.id.first_edit_text);
        secondNumberEditText = (EditText)findViewById(R.id.second_edit_text);

        plusButton = (Button)findViewById(R.id.plus_button);
        plusButton.setOnClickListener(genericButtonClickListener);

        minusButton = (Button)findViewById(R.id.minus_button);
        minusButton.setOnClickListener(genericButtonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.FIRST_VALUE)) {
                firstNumberEditText.setText(savedInstanceState.getString(Constants.FIRST_VALUE));
            }

            if (savedInstanceState.containsKey(Constants.SECOND_VALUE)) {
                secondNumberEditText.setText(savedInstanceState.getString(Constants.SECOND_VALUE));
            }

            if (savedInstanceState.containsKey(Constants.RESULT_VALUE)) {
                resultTextView.setText(savedInstanceState.getString(Constants.RESULT_VALUE));
            }
        }

        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        navigateToSecondaryActivityButton.setOnClickListener(genericButtonClickListener);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(Constants.FIRST_VALUE, firstNumberEditText.getText().toString());
        savedInstanceState.putString(Constants.SECOND_VALUE, secondNumberEditText.getText().toString());
        savedInstanceState.putString(Constants.RESULT_VALUE, resultTextView.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.FIRST_VALUE)) {
            firstNumberEditText.setText(savedInstanceState.getString(Constants.FIRST_VALUE));
        }

        if (savedInstanceState.containsKey(Constants.SECOND_VALUE)) {
            secondNumberEditText.setText(savedInstanceState.getString(Constants.SECOND_VALUE));
        }

        if (savedInstanceState.containsKey(Constants.RESULT_VALUE)) {
            resultTextView.setText(savedInstanceState.getString(Constants.RESULT_VALUE));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}
