/**
 * 
 */
package br.com.bethpapp.utils;

import java.util.Random;

/**
 * @author messias
 *
 */
public class Ean {

    public static String calculaDigEan11(String vavedeno) throws Exception {
        if (vavedeno.length() != 11) {
            throw new Exception("11 dígitos são necessários para calcular o dígito verificador.");
        }
        int F[] = new int[11];
        String temp = "";
        for (int i = 0; i < vavedeno.length(); i++) {
            temp = String.valueOf(vavedeno.charAt(i));
            if (temp.replaceAll("[^0-9]", "").equals("")) {
                throw new Exception("Por favor, utilize somente números.");
            }
            F[i] = Integer.parseInt(temp);
        }
        int sum = 0;
        String last = "";
        sum = F[0] + F[2] + F[4] + F[6] + F[8] + F[10];
        sum = sum * 3;

        sum = sum + F[1] + F[3] + F[5] + F[7] + F[9];

        if ((sum + 0) % 10 == 0) {
            last = "0";
        }
        if ((sum + 1) % 10 == 0) {
            last = "1";
        }
        if ((sum + 2) % 10 == 0) {
            last = "2";
        }
        if ((sum + 3) % 10 == 0) {
            last = "3";
        }
        if ((sum + 4) % 10 == 0) {
            last = "4";
        }
        if ((sum + 5) % 10 == 0) {
            last = "5";
        }
        if ((sum + 6) % 10 == 0) {
            last = "6";
        }
        if ((sum + 7) % 10 == 0) {
            last = "7";
        }
        if ((sum + 8) % 10 == 0) {
            last = "8";
        }
        if ((sum + 9) % 10 == 0) {
            last = "9";
        }
        return vavedeno + last;
    }

    public static String calculaDigEan13(String vavedeno) throws Exception {

        if (vavedeno.length() != 13) {
            throw new Exception("13 dígitos são necessários para calcular o dígito verificador.");
        }

        int F2[] = new int[13];
        String temp = "";
        for (int i = 0; i < vavedeno.length(); i++) {
            temp = String.valueOf(vavedeno.charAt(i));
            if (temp.replaceAll("[^0-9]", "").equals("")) {
                throw new Exception("Por favor, utilize somente números.");
            }
            F2[i] = Integer.parseInt(temp);
        }

        int sum2 = 0;
        String last2 = "";
        sum2 = F2[0] + F2[2] + F2[4] + F2[6] + F2[8] + F2[10] + F2[12];
        sum2 = sum2 * 3;

        sum2 = sum2 + F2[1] + F2[3] + F2[5] + F2[7] + F2[9] + F2[11];

        if ((sum2 + 0) % 10 == 0) {
            last2 = "0";
        }
        if ((sum2 + 1) % 10 == 0) {
            last2 = "1";
        }
        if ((sum2 + 2) % 10 == 0) {
            last2 = "2";
        }
        if ((sum2 + 3) % 10 == 0) {
            last2 = "3";
        }
        if ((sum2 + 4) % 10 == 0) {
            last2 = "4";
        }
        if ((sum2 + 5) % 10 == 0) {
            last2 = "5";
        }
        if ((sum2 + 6) % 10 == 0) {
            last2 = "6";
        }
        if ((sum2 + 7) % 10 == 0) {
            last2 = "7";
        }
        if ((sum2 + 8) % 10 == 0) {
            last2 = "8";
        }
        if ((sum2 + 9) % 10 == 0) {
            last2 = "9";
        }
        return vavedeno + last2;
    }

    public static String calculaDigEan12(String vavedeno2) throws Exception {
        if (vavedeno2.length() != 12) {
            throw new Exception("12 dígitos são necessários para calcular o dígito verificador.");
        }
        int F2[] = new int[13];
        String temp = "";
        for (int i = 0; i < vavedeno2.length(); i++) {
            temp = String.valueOf(vavedeno2.charAt(i));
            if (temp.replaceAll("[^0-9]", "").equals("")) {
                throw new Exception("Por favor, utilize somente números.");
            }
            F2[i] = Integer.parseInt(temp);
        }

        int sum2 = 0;
        String last2 = "";
        sum2 = F2[1] + F2[3] + F2[5] + F2[7] + F2[9] + F2[11];
        sum2 = sum2 * 3;

        sum2 = sum2 + F2[0] + F2[2] + F2[4] + F2[6] + F2[8] + F2[10];

        if ((sum2 + 0) % 10 == 0) {
            last2 = "0";
        }
        if ((sum2 + 1) % 10 == 0) {
            last2 = "1";
        }
        if ((sum2 + 2) % 10 == 0) {
            last2 = "2";
        }
        if ((sum2 + 3) % 10 == 0) {
            last2 = "3";
        }
        if ((sum2 + 4) % 10 == 0) {
            last2 = "4";
        }
        if ((sum2 + 5) % 10 == 0) {
            last2 = "5";
        }
        if ((sum2 + 6) % 10 == 0) {
            last2 = "6";
        }
        if ((sum2 + 7) % 10 == 0) {
            last2 = "7";
        }
        if ((sum2 + 8) % 10 == 0) {
            last2 = "8";
        }
        if ((sum2 + 9) % 10 == 0) {
            last2 = "9";
        }
        return vavedeno2 + last2;
    }

    public static String ean11() throws Exception {
        String ean = "";
        Random rand = new Random();
        for (int i = 0; i < 11; i++) {
            ean += String.valueOf(rand.nextInt(9));
        }
        return Ean.calculaDigEan11(ean);
    }

    public static String ean12() throws Exception {
        String ean = "";
        Random rand = new Random();
        for (int i = 0; i < 12; i++) {
            ean += String.valueOf(rand.nextInt(9));
        }
        return Ean.calculaDigEan12(ean);
    }

    public static String ean13() throws Exception {
        String ean = "";
        Random rand = new Random();
        for (int i = 0; i < 13; i++) {
            ean += String.valueOf(rand.nextInt(9));
        }
        return Ean.calculaDigEan13(ean);
    }

    public static boolean isEan(String ean) {
        boolean is = false;
        try {
            String eanT = ean.substring(0, ean.length() - 1);
            switch (ean.length()) {
                case 12:
                    is = ean.equals(Ean.calculaDigEan11(eanT));
                    break;
                case 13:
                    is = ean.equals(Ean.calculaDigEan12(eanT));
                    break;
                case 14:
                    is = ean.equals(Ean.calculaDigEan13(eanT));
                    break;
                default:
                    is = false;
                    break;
            }
        } catch (Exception ex) {
            is = false;
        }
        return is;
    }
    
}
