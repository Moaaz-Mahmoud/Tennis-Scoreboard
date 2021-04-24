package com.company;

public class StringAssistant{
    public static String placeNameInField(String name){
        StringBuilder nameInFieldBuilder = new StringBuilder();
        nameInFieldBuilder.append(name.substring(0, Math.min(Score.NAME_MAX_LENGTH, name.length())));
        for(int i = 0; i < Score.NAME_FIELD - name.length(); i++)
            nameInFieldBuilder.append(" ");
        return nameInFieldBuilder.toString();
    }
}