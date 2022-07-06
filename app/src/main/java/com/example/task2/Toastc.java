package com.example.task2;

import android.content.Context;
import android.widget.Toast;

public class Toastc {
    public static final boolean toast=true;

    public static void isShow(Context context, String message) {
        try {
            if (toast) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
