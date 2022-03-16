package rs.ac.ni.pmf.greeting2022;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SingleChoiceDialog extends DialogFragment {
    public static final String TAG = "GREETING_INFO";

    private int _selected = -1;
    private final String[] _values;

    public SingleChoiceDialog(String[] values, int initialSelection) {
        _values = values;
        _selected = initialSelection;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

//        final String[] values = {"One", "Two", "Three", "Four"};

        final AlertDialog alertDialog = builder
                .setTitle("Pick a value")
                .setSingleChoiceItems(_values, _selected, (dialogInterface, i) -> {
                    _selected = i;
//                    Log.i(TAG, "Selected item: " + _values[i]);
                    ((AlertDialog)dialogInterface).getButton(DialogInterface.BUTTON_POSITIVE)
                            .setEnabled(true);
                })
                .setPositiveButton("OK", (dialogInterface, i) -> Log.i(TAG, "Done. Selected: " + _selected))
                .setNegativeButton("Cancel", ((dialogInterface, i) -> {}))
                .create();

        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                if(_selected < 0 || _selected >= _values.length) {
                    ((AlertDialog) dialogInterface).getButton(AlertDialog.BUTTON_POSITIVE)
                            .setEnabled(false);
                }
            }
        });

        return alertDialog;
    }
}
