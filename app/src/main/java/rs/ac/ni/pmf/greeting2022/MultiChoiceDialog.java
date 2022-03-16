package rs.ac.ni.pmf.greeting2022;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MultiChoiceDialog extends DialogFragment {
    public static final String TAG = "GREETING_INFO";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final String[] values = {"One", "Two", "Three", "Four"};

        return builder.setTitle("Pick a value")
                .setMultiChoiceItems(values, new boolean[]{false, false, true, true}, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which, final boolean isChecked) {
                        Log.i(TAG, "Selected item: " + which + ". Value: " + isChecked);
                    }
                })
                .setPositiveButton(R.string.dialog_yes, (dialog, which) -> {
                })
                .setNegativeButton(R.string.dialog_no, (dialog, which) -> {
                })
                .create();
    }
}