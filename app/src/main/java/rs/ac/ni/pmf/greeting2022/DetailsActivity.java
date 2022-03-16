package rs.ac.ni.pmf.greeting2022;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class DetailsActivity extends AppCompatActivity {

    public static final String TAG = "GREETING_INFO";

    private int _defaultAge = -1;

    public static final String PERSON_DATA = "PERSON_DATA";

    private EditText _editFirstName;
    private EditText _editLastName;
    private EditText _editAge;

    private Person _person;

    public static final ActivityResultContract<Person, Person> DETAILS_ACTIVITY_CONTRACT = new ActivityResultContract<Person, Person>() {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Person input) {
            final Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra(PERSON_DATA, input);

            return intent;
        }

        @Override
        public Person parseResult(int resultCode, @Nullable Intent intent) {
            if (resultCode == RESULT_CANCELED) {
                Log.i(TAG, "Age selection canceled");
                return null;
            }

            if (resultCode == RESULT_OK) {
                if (intent == null) {
                    Log.i(TAG, "No data returned from DetailsActivity");
                    return null;
                }

                return (Person) intent.getParcelableExtra(PERSON_DATA);
            }

            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        _editFirstName = findViewById(R.id.editFirstName);
        _editLastName = findViewById(R.id.editLastName);
        _editAge = findViewById(R.id.editAge);

        final Intent intent = getIntent();
        _person = (Person) intent.getParcelableExtra(PERSON_DATA);

        showPersonData();

        findViewById(R.id.btnCancelDetails).setOnClickListener(this::cancel);
        findViewById(R.id.btnAcceptDetails).setOnClickListener(this::accept);
    }

    private void showPersonData()
    {
        if (_person != null)
        {
            _editFirstName.setText(_person.getFirstName());
            _editLastName.setText(_person.getLastName());
            _editAge.setText(String.valueOf(_person.getAge()));
        }
    }

    private void accept(View view) {
        final String firstName = _editFirstName.getText().toString();
        final String lastName = _editLastName.getText().toString();
        final String ageValue = _editAge.getText().toString();

        int age;

        try {
            age = Integer.parseInt(ageValue);
        } catch (final Exception e) {
            Log.e(TAG, e.getMessage());
            age = _defaultAge;
        }

        final Intent resultIntent = new Intent();
        resultIntent.putExtra(PERSON_DATA, new Person(firstName, lastName, age));

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}