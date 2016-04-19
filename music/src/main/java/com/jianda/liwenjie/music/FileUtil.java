package com.jianda.liwenjie.music;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwenjie on 2016/4/19.
 */
public class FileUtil {
    public static List<File> getList(File fileDir){
        List<File> files = new ArrayList<>();
        if (fileDir.isDirectory()){
            File[] files1 = fileDir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().toLowerCase().endsWith(".mp3") && pathname.getTotalSpace() > (300 * 1024);
                }
            });
            int length = files1.length;
            for (int i = 0; i < length; i++) {
                files.add(files1[i]);
            }
        }
        return files;
    }
}
