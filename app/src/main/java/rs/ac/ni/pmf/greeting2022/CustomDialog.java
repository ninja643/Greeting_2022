package rs.ac.ni.pmf.greeting2022;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CustomDialog extends DialogFragment {

    public interface CustomDialogListener {
        void onYes(String username, String password);
    }

    private CustomDialogListener _listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        _listener = (CustomDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View layout = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        final EditText editUsername = layout.findViewById(R.id.username);
        final EditText editPassword = layout.findViewById(R.id.password);

        final AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        return builder
                .setTitle("Custom dialog")
                .setView(layout)
                .setPositiveButton(R.string.dialog_yes, (dialogInterface, i) -> {
                    final String username = editUsername.getText().toString();
                    final String password = editPassword.getText().toString();
                    _listener.onYes(username, password);
                })
                .create();
    }
}
