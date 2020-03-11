package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Button window;
    Button internetButton;
    EditText idEdit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.login_button :
                        Intent selectintent = new Intent(getApplicationContext(),SelectActivity.class);
                        selectintent.putExtra("player", idEdit.getText().toString());
                        startActivity(selectintent);
                        finish();
                        break;
                    case R.id.window :
                        Intent windowintent = new Intent(getApplicationContext(),ChangeActivity.class);
                        startActivity(windowintent);
                    case R.id.internet :
                        Intent internetintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cafe.naver.com/joonggonara"));
                        startActivity(internetintent);
                }

            }
        };
        idEdit = findViewById(R.id.write_id);
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(listener);
        window = findViewById(R.id.window);
        window.setOnClickListener(listener);
        internetButton = findViewById(R.id.internet);
        internetButton.setOnClickListener(listener);


        Intent countIntent = getIntent();
        int i = (int) countIntent.getIntExtra("count",0);
        switch (i){
            case 0:
                break;
            case 1:
                idEdit.setText("");
                break;

        }

    }


}
