package ro.pub.cs.systems.eim.practicaltest01var02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var02SecondaryActivity extends AppCompatActivity {

    private TextView operationTextView;
    private Button okButton, cancelButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ok_button:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_secondary);

        operationTextView = (TextView)findViewById(R.id.operation_text_view);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.OPERATION_RESULT)) {
            int operationResult = intent.getIntExtra(Constants.OPERATION_RESULT, -1);

            if (intent.getExtras().containsKey(Constants.OPERATION_PLUS)) {
                String message = "Addition: " + String.valueOf(operationResult);
                operationTextView.setText(message);
            }

            if (intent.getExtras().containsKey(Constants.OPERATION_MINUS)) {
                String message = "Substraction: " + String.valueOf(operationResult);
                operationTextView.setText(message);
            }
        }

        okButton = (Button)findViewById(R.id.ok_button);
        okButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(buttonClickListener);
    }
}
