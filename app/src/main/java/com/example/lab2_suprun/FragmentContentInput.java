package com.example.lab2_suprun;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FragmentContentInput extends Fragment{

    public String result_output;
    private EditText task;
    private String val_type;
    private  String val_dif;
    public String file_name = (Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOCUMENTS)).toString() + "/saved_task.txt";

    public interface OnSelectedButtonListener {
        void onButtonSelected(String res);
    }

    public interface OnSelectedButtonListenerOpen {
        void onButtonSelectedOpen(String path);
    }

    @SuppressLint("NonConstantResourceId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_content, container, false);

        RadioGroup difficulty = rootView.findViewById(R.id.difficulty);
        RadioGroup type_task = rootView.findViewById(R.id.type);
        task = rootView.findViewById(R.id.editTextTextMultiLine2);
        Button button_ok = rootView.findViewById(R.id.button);
        Button open_storage = rootView.findViewById(R.id.open_saved);

        button_ok.setOnClickListener(this::onClick);
        open_storage.setOnClickListener(this::onClickOpen);

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

    public void saveTask(String res){

        try(FileWriter writer = new FileWriter(file_name, true))
        {
            writer.write(res);
            writer.append('\n');
            writer.flush();
            Toast empty_field = Toast.makeText(getActivity(),
                    "Завдання успішно збережено",
                    Toast.LENGTH_SHORT);
            empty_field.show();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            Toast empty_field = Toast.makeText(getActivity(),
                    "Помилка зберігання",
                    Toast.LENGTH_SHORT);
            empty_field.show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void onClick(View view) {
        if (val_dif == null || val_type == null || TextUtils.isEmpty(task.getText())) {
            Toast empty_field = Toast.makeText(getActivity(),
                    "Ви не ввели запитання або не здійснили вибір складності/типу завдання.",
                    Toast.LENGTH_SHORT);
            empty_field.show();
        } else {
            result_output = "Завдання:   " + task.getText() + "\nТип завдання: " + val_type + "\nРівень складності:  " + val_dif;
            this.saveTask(result_output);
            OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
            assert listener != null;
            listener.onButtonSelected(result_output);

        }
    }

    public void onClickOpen(View view){
        OnSelectedButtonListenerOpen listener = (OnSelectedButtonListenerOpen) getActivity();
        assert listener != null;
        listener.onButtonSelectedOpen(file_name);
    }

}
