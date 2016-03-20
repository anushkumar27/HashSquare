
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class hashDecrypt {

    //HashTable init
    public Hashtable<Long, String> hashSqaure = new Hashtable<>();
    String Dencrypt = "";
    int enLen;
    int n = 0;
    long Drand = 0;
    long DuserKey = 0;
    String DuserKeyString = "";
    String userKey = "";
    static String finalDecrypt = "";
    static boolean validateBool = false;

    hashDecrypt(String d, String k) {
        Dencrypt = d;
        userKey = k;
        enLen = Dencrypt.length();
    }

    void decrypt() {
        try {
            int count = 1;
            while (n < enLen) {
                if (!"$".equals(Character.toString(Dencrypt.charAt(n)))) {
                    DuserKeyString += Dencrypt.charAt(n);
                    count++;
                } else {
                    break;
                }
                n++;
            }
            DuserKey = Long.parseLong(DuserKeyString);
            Drand = Long.parseLong(Character.toString(Dencrypt.charAt(count)) + Character.toString(Dencrypt.charAt(count + 1)));
            boolean x = validate();
            if (x) {
                hashTableGen();
                String tempString = Dencrypt.substring(count + 3, Dencrypt.length() - 1);
                String character[] = tempString.split("%");
                System.out.print("After Decryption::  ");
                for (int i = 0; i < character.length; i++) {
                    long tempLong = Long.parseLong(character[i]);
                    finalDecrypt += hashSqaure.get(tempLong);
                    System.out.print(hashSqaure.get(tempLong));
                }
                System.out.println();;
                validateBool = true;
            } else {
                validateBool = false;
                hashGUI.message.setText("Decryption : Key Not Matching");
            }
        } catch (Exception e) {
            hashGUI.message.setText("Decryption : Invalid Input");
        }
    }

    //This Method validates the userKey Entered
    boolean validate() {
        String orgUserKey = "";
        int count = 0;
        int j = 0;
        int i = DuserKeyString.length() / 3;
        while (i != 0) {
            int temp = Integer.parseInt(DuserKeyString.substring(count, count + 3));
            temp = temp / (j + 2);
            orgUserKey += (char) temp;
            count += 3;
            i--;
            j++;
        }
        if (orgUserKey.equals(userKey)) {
            System.out.println("Got it right!");
            return true;
        } else {
            System.out.println("The User Key Entered is wrong!");
            return false;
        }
    }

    void hashTableGen() {
        char temp;
        long tempKey = DuserKey + Drand;
        for (int i = 32; i < 127; i++) {
            temp = (char) i;
            hashSqaure.put(tempKey, Character.toString(temp));
            tempKey = tempKey + Drand;
        }
    }

}
