package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "6f8db599de986fab7a21625b7916589c")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] stream = md.digest(byteArrayOutputStream.toByteArray());

        StringBuilder sb = new StringBuilder();
        for (byte b : stream)
            sb.append(String.format("%02x", b));

        //BigInteger i = new BigInteger(1, stream);
        //return String.format("%032x", i).equals(md5);
        return md5.equals(sb.toString());
    }
}