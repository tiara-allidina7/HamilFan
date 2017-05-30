package com.example.tiara.hamilfan;

import android.content.res.Resources;

/**
 * Created by Tiara on 2016-12-03.
 */
public class SongUtils {

    public static int getLyricsResource(String songTitle, MyApplication myApplication){
        Resources res = myApplication.getResources();
        String resourceName = songTitle.replace(" ","_").toLowerCase();
        resourceName = resourceName.replace("?","");
        resourceName = resourceName.replace("-","_");
        resourceName = resourceName.replace("(","");
        resourceName = resourceName.replace(")","");
        resourceName = resourceName.replace("'","");
        resourceName = resourceName.replace(",","");
        resourceName = resourceName.replace("#","");
        int songResourceId = res.getIdentifier(resourceName, "raw", myApplication.getPackageName());
        return songResourceId;
    }
}
