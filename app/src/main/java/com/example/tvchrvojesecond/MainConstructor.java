package com.example.tvchrvojesecond;

import android.content.Context;

public interface MainConstructor {
    interface MainView{
        void setButtonChangeText(String language);

        Context getContext();
    }
    interface MainPresentor{
        void dialogChangeLanguage();
        void dialogToastHelloWorld();
        void loadLanguage();
        void dialogRegex();
    }
}
