package com.example.tvchrvojesecond;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MainConstructor.MainView {
    MainPresenter mainPresenter=new MainPresenter(this);
    Button buttonChange,buttonToast,buttonFizzBuzz;

    String language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter.loadLanguage();
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        buttonChange=findViewById(R.id.button_change_language);
        buttonToast=findViewById(R.id.button_toast);
        buttonFizzBuzz=findViewById(R.id.button_fizzbuzz);
        mainPresenter.setButttonChangeText();

        buttonFizzBuzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        mainPresenter.dialogRegex();


            }
        });

        buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.dialogToastHelloWorld();

            }
        });


        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.dialogChangeLanguage();
            }
        });
    }


    @Override
    public void setButtonChangeText(String language) {
        if (language.isEmpty()){
            buttonChange.setText("-");

        }else {
            buttonChange.setText(language);
        }

    }

    @Override
    public Context getContext() {
        return MainActivity.this;
    }

}
