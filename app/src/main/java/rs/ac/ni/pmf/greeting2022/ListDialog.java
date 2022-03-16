package rs.ac.ni.pmf.greeting2022;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ListDialog extends DialogFragment {
    public static final String TAG = "GREETING_INFO";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final String[] values = {"One", "Two", "Three", "Four"};

        return builder
                .setTitle("Pick a value")
                .setItems(values, (dialogInterface, i) -> Log.i(TAG, "Selected item: " + values[i]))
                .create();
    }
}
