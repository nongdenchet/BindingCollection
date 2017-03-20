package com.apidez.bindingcollection.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.apidez.bindingcollection.R;

public class NewTodoFragment extends DialogFragment {
    private EditText edtTitle;
    private EditText edtDueDate;
    private Button btnOk;
    private OnSubmitListener onSubmitListener;

    public interface OnSubmitListener {
        void onSubmit(String title, String dueDate);
    }

    public static NewTodoFragment newInstance() {
        Bundle args = new Bundle();
        NewTodoFragment fragment = new NewTodoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_todo, container, false);
        edtTitle = (EditText) rootView.findViewById(R.id.edtTitle);
        edtDueDate = (EditText) rootView.findViewById(R.id.edtDueDate);
        btnOk = (Button) rootView.findViewById(R.id.btnOk);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setTitle(R.string.create_new_todo);
        btnOk.setOnClickListener(v -> {
            String title = edtTitle.getText().toString().trim();
            String dueDate = edtDueDate.getText().toString().trim();
            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(dueDate)) {
                if (onSubmitListener != null) {
                    onSubmitListener.onSubmit(title, dueDate);
                    dismiss();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSubmitListener) {
            this.onSubmitListener = (OnSubmitListener) context;
        }
    }
}
