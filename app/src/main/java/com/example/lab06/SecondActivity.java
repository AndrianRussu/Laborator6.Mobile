package com.example.lab06;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab06.R;
import java.io.*;
public class SecondActivity extends AppCompatActivity {
    TextView showText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        showText = (TextView) findViewById(R.id.getText);
    }
    public void back(View view) {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void displayData(View view) {
        File folder = getExternalFilesDir("example"); // Folder Name
        File myFile = new File(folder, "input.txt"); // Filename
        String text = getData(myFile);
        if (text != null) {
            showText.setText(text);
        } else {
            showText.setText("No Data");
        }
    }
    private String getData(File myFile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = fileInputStream.read()) != -1) {
                buffer.append((char) i);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public void clear(View view) {
        String data = "";
        File folder = getExternalFilesDir("example");
        File myFile = new File(folder, "input.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile, false);
            fileOutputStream.write(data.getBytes());
            Toast.makeText(this, "Au fost stersele datele din fisierul:\n" +
                    myFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        displayData(view);
    }
}