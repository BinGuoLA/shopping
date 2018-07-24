package com.lanqiao.shop.utils;

import java.security.MessageDigest;

public class MD5Util {
    /**
     * Title: MD5���� ����32λmd5��
     * Description: TestDemo
     * @author lu
     * @param inStr
     * @return ����32λmd5��
     * @throws Exception
     */
    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    /**
     * Title: MD5����
     * Description: TestDemo
     * @author lu
     * @param inStr
     * @return
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * Title: ���ܽ����㷨 ִ��һ�μ��ܣ����ν���
     * Description: TestDemo
     * @author lu
     * @param inStr
     * @return
     */
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }
    public static String md5Decode(String str) {
        return convertMD5(convertMD5(str));
    }
    
    public static void main(String[] args) {
		String pwd = "123";//ԭʼ����
		String md5pwd = MD5Util.string2MD5(pwd);//����
		String str =convertMD5(convertMD5(md5pwd));//���ν���
		//202cb962ac59075b964b07152d234b70
		System.out.println("ԭʼ���룺"+pwd);  //123
		System.out.println("md5�������룺"+md5pwd);//fdsfdsafdsafdsafdsa
		System.out.println("���ܣ�"+str);  // 123
	}
}