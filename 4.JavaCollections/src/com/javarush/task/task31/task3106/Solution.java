package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Разархивируем файл
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        //ZipOutputStream out = new ZipOutputStream(new FileOutputStream(new File(args[0])));

        ArrayList<InputStream> inputStreams = new ArrayList<>();
        String[] fileNameParts = new String[args.length - 1];
        for (int i = 1; i < args.length; i++)
            fileNameParts[i-1] = args[i];

        Arrays.sort(fileNameParts);

        for (int i = 0; i < fileNameParts.length; i++)
            inputStreams.add(new FileInputStream(fileNameParts[i]));

        SequenceInputStream sequenceIn = new SequenceInputStream(Collections.enumeration(inputStreams));
        ZipInputStream in = new ZipInputStream(sequenceIn);
        FileOutputStream fout = new FileOutputStream(args[0]);

        byte[] buffer = new byte[1024*1024];
        while ( in.getNextEntry() != null) {
            int count;
            while ((count = in.read(buffer)) != -1) {
                fout.write(buffer, 0, count);
            }
        }
        sequenceIn.close();
        in.close();
        fout.close();
    }
}
