package com.example.tvchrvojesecond;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class ChooseLanguageFragmentDialog extends DialogFragment {
    int positionn =-1; //default selected position

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] list = getActivity().getResources().getStringArray(R.array.choose_lang);
        SharedPreferences preferences=getActivity().getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        positionn = preferences.getInt("position",-1);

        builder.setTitle(getResources().getString(R.string.dialog_title))
                .setSingleChoiceItems(list, positionn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i==0){
                            setLocale("en",i);
                            Restart(getActivity());
                        }
                        if (i==1){
                            setLocale("hr",i);
                            Restart(getActivity());
                        }

                    }
                });

        return builder.create();
    }

    //SET LANGUAGE BASED ON SELECTED BUTTON POSITION ISIDE DIALOG ,AND SAVE THE POSITION TO SHARED PREFERENCES
    private void setLocale(String lang,int positionn) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getActivity().getResources().updateConfiguration(configuration,getActivity().getResources().getDisplayMetrics());
        //
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("Selected_Language",lang);
        editor.putInt("position",positionn);
        editor.apply();

    }
    //RESTART MAIN ACTIVITY
    public void Restart(Context ctx)
    {
        //geting activty from context
        MainActivity a = (MainActivity) ctx;
        //forcing activity to recrete
        a.recreate();
    }
}
