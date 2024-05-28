package com.example.calculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.calculator.databinding.Fragment1Binding;

import java.util.HashMap;
import java.util.Map;

public class Fragment1 extends Fragment {
    private OnResClickListener callback;private Fragment1Binding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        binding.button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String result = func();
                callback.onResClick(result);
            }
        });try {callback = (OnResClickListener) getActivity();} catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement OnResClickListener");
        }return view;}
    public void resetInput() {binding.num.setText("");}
    public interface OnResClickListener {void onResClick(String result);
    }
    public double convert(double value, int fromUnit, int toUnit) {
        if(fromUnit == toUnit){
            return value;
        }
        Map<String, Double> conversionFactors = new HashMap<>();
        conversionFactors.put("0", 0.0002);conversionFactors.put("1", 0.001);
        conversionFactors.put("2", 1.0);conversionFactors.put("3", 100.0);
        conversionFactors.put("4", 1000.0);conversionFactors.put("5", 0.45359);
        conversionFactors.put("6", 0.02835);
        double a = conversionFactors.get(Integer.toString(fromUnit));
        double b = conversionFactors.get(Integer.toString(toUnit));
        return value * a / b;
    }
    public String func(){
        String result = "";String input = binding.num.getText().toString();
        int selectedId = binding.radioGroup.getCheckedRadioButtonId();
        View radioButton = binding.radioGroup.findViewById(selectedId);
        int idx = binding.radioGroup.indexOfChild(radioButton);
        if (input.isEmpty()){
            result = "Введите текст";
        }else {double num = Double.parseDouble(binding.num.getText().toString());
            if (num < 0 || idx < 0 || idx > 6)
                result = "Выберите величину" + idx;
            else {
                result = convert(num,idx,0) + " карат, " + convert(num,idx,1) + " Грам, " + convert(num,idx,2)  + " Килограм, "
                        + convert(num,idx,3)+ " центнер, " + convert(num,idx,4)  + " тонн, " + convert(num,idx,5)
                        + " фунтов, " + convert(num,idx,6) + " унций.";
            }
        }
        return result;
    }
}
