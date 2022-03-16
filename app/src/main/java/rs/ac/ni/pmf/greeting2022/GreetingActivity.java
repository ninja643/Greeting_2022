package rs.ac.ni.pmf.greeting2022;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class GreetingActivity extends AppCompatActivity implements MyDialog.MyDialogListener {

    public static final String TAG = "GREETING_INFO";

    private EditText _editText;
    private TextView _label;

    private Person _person;

    private ActivityResultLauncher<Person> _detailsActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnGreet).setOnClickListener(this::sayHello);
        _editText = findViewById(R.id.editName);
        _label = findViewById(R.id.labelHello);

        findViewById(R.id.btnShowDetails).setOnClickListener(this::showDetails);

        Log.i(TAG, "onCreate()");

        _detailsActivityLauncher = registerForActivityResult(DetailsActivity.DETAILS_ACTIVITY_CONTRACT, this::onDetailsActivityResult);
    }

    public void sayHello(View view) {
        final String name = _editText.getText().toString();

        final int currentAge = _person != null ? _person.getAge() : -1;

        final String greeting = getResources()
                .getQuantityString(R.plurals.greeting, currentAge, name, currentAge);

        _label.setText(greeting);
    }

    private void showDetails(View view) {
        _detailsActivityLauncher.launch(_person);
    }

    private void onDetailsActivityResult(Person person) {
        if (person == null) {
            //Toast.makeText(this, R.string.edit_person_canceled, Toast.LENGTH_SHORT).show();

            final View toastView = getLayoutInflater().inflate(R.layout.custom_toast, null);
            TextView firstLineView = toastView.findViewById(R.id.toast_first_line);
            TextView secondLineView = toastView.findViewById(R.id.toast_second_line);

            firstLineView.setText("This is a custom toast!");
            secondLineView.setText(R.string.edit_person_canceled);

            final Toast myToast = new Toast(getApplicationContext());
            myToast.setDuration(Toast.LENGTH_LONG);
            //myToast.setText(R.string.edit_person_canceled);
            myToast.setGravity(Gravity.BOTTOM | Gravity.START, 50, -50);
            myToast.setView(toastView);

            myToast.show();
            return;
        }

        _person = person;

        Snackbar.make(
                findViewById(R.id.main_layout),
                "Data received. First name: " + _person.getFirstName(),
                Snackbar.LENGTH_INDEFINITE)
                .setAction("Greet", this::sayHello)
                .show();

        showCurrentAge();
    }

    private void showCurrentAge() {
        final TextView labelAge = findViewById(R.id.labelCurrentAge);
        if (_person != null) {
            labelAge.setText("Current age: " + _person.getAge());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    public void showDialog(View view) {
        final MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(), "MY_DIALOG");
    }

    @Override
    public void onYes(MyDialog dialog) {
        Log.i(TAG, "Yes selected. Value: " + dialog.getValue());
    }

    @Override
    public void onNo(MyDialog dialog) {
        Log.i(TAG, "No selected. Value: " + dialog.getValue());
    }

    @Override
    public void onCancel(MyDialog dialog) {
        Log.i(TAG, "Cancel selected. Value: " + dialog.getValue());
    }
}
