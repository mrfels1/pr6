package com.example.calculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calculator.databinding.Fragment2Binding;


public class Fragment2 extends Fragment {
    private Fragment2Binding binding;
    private TextView resView;
    private OnRes2ClickListener callback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        resView = view.findViewById(R.id.textView);
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!resView.getText().toString().isEmpty())
                    callback.onRes2Click();
            }
        });
        try {
            callback = (OnRes2ClickListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement OnResetClickListener");
        }
        return view;
    }
    public void displayResult(String result) {
        resView.setText(String.valueOf(result));
    }
    public boolean hasResult() {
        return !resView.getText().toString().isEmpty();
    }
    public interface OnRes2ClickListener {
        void onRes2Click();
    }
    public void resetInput() {
        binding.textView.setText("");
    }
}
