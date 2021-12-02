package com.example.lab2_suprun;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.view.Gravity;
import android.widget.Toast;

public class FragmentContentInput extends Fragment{

    public String result_output;
    private EditText task;
    private String val_type;
    private  String val_dif;

    public interface OnSelectedButtonListener {
        void onButtonSelected(String res);
    }

    @SuppressLint("NonConstantResourceId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_content, container, false);

        RadioGroup difficulty = rootView.findViewById(R.id.difficulty);
        RadioGroup type_task = rootView.findViewById(R.id.type);
        task = rootView.findViewById(R.id.editTextTextMultiLine2);
        Button button_ok = (Button) rootView.findViewById(R.id.button);

        button_ok.setOnClickListener(this::onClick);

        type_task.setOnCheckedChangeListener((arg0, id) -> {
            switch(id) {
                case R.id.theoretical:
                    val_type = "Теоретичне";
                    break;
                case R.id.practice:
                    val_type = "Практичне";
                    break;
                default:
                    break;
            }
        });

        difficulty.setOnCheckedChangeListener((arg0, id) -> {
            switch(id) {
                case R.id.easy:
                    val_dif = "Легкий";
                    break;
                case R.id.average:
                    val_dif = "Середній";
                    break;
                case R.id.high:
                    val_dif = "Високий";
                    break;
                default:
                    break;
            }
        });

        return rootView;
    }

    @SuppressLint("SetTextI18n")
    public void onClick(View view) {
        if (val_dif == null || val_type == null || TextUtils.isEmpty(task.getText())) {
            Toast empty_field = Toast.makeText(getActivity(),
                    "Ви не ввели запитання або не здійснили вибір складності/типу завдання.",
                    Toast.LENGTH_SHORT);
            empty_field.setGravity(Gravity.CENTER, 0, 0);
            empty_field.show();
        } else {
            result_output = "Завдання:   " + task.getText() + "\nТип завдання: " + val_type + "\nРівень складності:  " + val_dif;
            OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
            assert listener != null;
            listener.onButtonSelected(result_output);
        }
    }
}
