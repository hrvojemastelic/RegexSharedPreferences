package com.example.tvchrvojesecond;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class MainPresenter implements MainConstructor.MainPresentor {
    Dialog dialog;
    MainConstructor.MainView mainView;
    String language;
    public MainPresenter(MainConstructor.MainView mainView) {
        this.mainView = mainView;
    }

    //START DIALOG FOR LANGUAGE CHANGE
    @Override
    public void dialogChangeLanguage() {
        DialogFragment singleChoiceDialog = new ChooseLanguageFragmentDialog();
        singleChoiceDialog.setCancelable(true);
        singleChoiceDialog.show(((MainActivity)mainView.getContext()).getSupportFragmentManager(), "Choose Language Dailog");
    }



    //LOADS SELECTED LANGUAGE AND UPDATES SHARED PREFERENCES
    @Override
    public void loadLanguage() {
        //LOAD
        SharedPreferences preferences=mainView.getContext().getSharedPreferences("Settings", MODE_PRIVATE);
        language =preferences.getString("Selected_Language","");
        Locale locale=new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        //UPDATE
        mainView.getContext().getResources().updateConfiguration(configuration,mainView.getContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor =mainView.getContext().getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("Selected_Language",language);
        editor.apply();
    }

    //START INPUT DIALOG
    @Override
    public void dialogRegex() {
        Button check;
        dialog=new Dialog(mainView.getContext());
        dialog.setContentView(R.layout.regex_dialog_layout);
        dialog.setCancelable(false);
        check=dialog.findViewById(R.id.regex_button);
        dialog.show();

        //CHECK IF MATCHES PATTERN
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText regex_edit;
                regex_edit=dialog.findViewById(R.id.regex_editetext);

                String s = regex_edit.getText().toString();

                Pattern pattern = Pattern.compile("^(?:(?!7).)*(7)(?:(?!\\1).)*$");
                Matcher matcher = pattern.matcher(regex_edit.getText().toString());
                Pattern pattern1=Pattern.compile("^.*a.*a.*$",Pattern.CASE_INSENSITIVE);
                Matcher matcher1=pattern1.matcher(regex_edit.getText().toString());
                if ( matcher1.matches() && matcher.matches() && !s.contains("?") && s.length()>4 && s.length()<13){
                    dialog.dismiss();
                }else {
                    regex_edit.setError("two letters A/a one no.7 and no ?");
                    regex_edit.requestFocus();
                }

            }
        });


    }

    //CHANGES TEXT IN LANGUAGE CHANGE BUTTON
    public void setButttonChangeText() {
        mainView.setButtonChangeText(language);
    }

    //SHOW TOAST IF LANGUAGE IS NOT SELECTED OR SHOWS DIALOG
    public void dialogToastHelloWorld() {
        SharedPreferences preferences=mainView.getContext().getSharedPreferences("Settings", MODE_PRIVATE);
        String language =preferences.getString("Selected_Language","");
        if (language.isEmpty()){
            Toast.makeText(mainView.getContext(),"Jezik nije odabran",Toast.LENGTH_LONG).show();

        }
        else {
            AlertDialog.Builder builder=new AlertDialog.Builder(mainView.getContext());
            builder.setTitle(mainView.getContext().getResources().getString(R.string.hello_world_dialog));
            AlertDialog alertDialog= builder.create();
            alertDialog.show();

        }
    }
}
