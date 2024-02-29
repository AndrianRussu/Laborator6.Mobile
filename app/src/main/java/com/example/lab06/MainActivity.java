package com.example.lab06;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab06.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {
    EditText editText;
    private int STORAGE_PERMISSION_CODE = 23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText2);
    }
    public void next(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
    public void saveData(View view) {
        String info = editText.getText().toString();
        if (info.length() != 0) {
            info += "\n";
            File folder = getExternalFilesDir("example");// Folder Name
            File myFile = new File(folder, "input.txt");// Filename
            writeData(myFile, info);
            editText.setText("");
        } else {
            Toast.makeText(this, "Este nevoie sa introduceti careva date",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void writeData(File myFile, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile, true);
            fileOutputStream.write(data.getBytes());
            Toast.makeText(this, "Au fost introduse datele in fisierul:\n" +
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
    }
}