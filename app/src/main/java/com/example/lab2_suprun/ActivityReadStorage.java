package com.example.lab2_suprun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ActivityReadStorage extends AppCompatActivity{

    private TextView show_tasks;
    private String path_storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_storage);
        show_tasks = findViewById(R.id.show_tasks);
        path_storage = getIntent().getStringExtra("path");
        this.readTasks();
    }

    public void readTasks() {
        try(BufferedReader reader = new BufferedReader( new FileReader (path_storage)))
        {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            while( ( line = reader.readLine() ) != null ) {
                stringBuilder.append( line );
                stringBuilder.append( ls );
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            show_tasks.setText(stringBuilder.toString());

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            Toast empty_field = Toast.makeText(getApplicationContext(),
                    "Помилка читання",
                    Toast.LENGTH_SHORT);
            empty_field.show();
        }
    }
}