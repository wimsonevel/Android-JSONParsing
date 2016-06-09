package com.example.wim.android_jsonparsing.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Wim on 5/29/16.
 */
public class FileUtil {

    public static String copyFromAssets(Context context, String filename){
        StringBuilder buf = new StringBuilder();
        InputStream json = null;
        try {
            json = context.getAssets().open(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str=in.readLine()) != null) {
                buf.append(str);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buf.toString();
    }
}
