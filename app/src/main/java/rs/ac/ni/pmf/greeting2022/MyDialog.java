package rs.ac.ni.pmf.greeting2022;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {

    private int _value;

    public interface MyDialogListener {
        void onYes(MyDialog dialog);

        void onNo(MyDialog dialog);

        void onCancel(MyDialog dialog);
    }

    private MyDialogListener _listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            _listener = (MyDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement DialogFragment.MyDialogListener interface");
        }
    }

    public int getValue() {
        return _value;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_content)
                .setPositiveButton(R.string.dialog_yes, (dialogInterface, i) -> {
                    _value = 5;
                    _listener.onYes(this);
                })
                .setNegativeButton(R.string.dialog_no, (dialogInterface, i) -> {
                    _value = -1;
                    _listener.onNo(this);
                })
                .setNeutralButton(R.string.dialog_cancel, (dialogInterface, i) -> _listener.onCancel(this));

        return builder.create();
    }
}
