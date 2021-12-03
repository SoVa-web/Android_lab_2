package com.example.lab2_suprun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentContentInput.OnSelectedButtonListener, FragmentContentInput.OnSelectedButtonListenerOpen{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public void onButtonSelected(String result_task) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentContentOutput fragment_result = (FragmentContentOutput) fragmentManager
                .findFragmentById(R.id.fragment_container_view_output);

        if (fragment_result != null)
            fragment_result.setTask(result_task);

    }

    @Override
    public void onButtonSelectedOpen(String path) {
        Intent intent = new Intent(this, ActivityReadStorage.class);
        intent.putExtra("path", path);
        startActivity(intent);
    }
}

