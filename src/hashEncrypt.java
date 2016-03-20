
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class hashEncrypt {

    //HashTable init
    public Hashtable<String, Long> hashSqaure = new Hashtable<>();
    //String ip stores Input txt
    String ip;
    //String userKey stores user Key
    String userKey;
    //Int Array to store ASCII value of each Char in UserKey seperately
    int[] userKeyArray;
    //String conCat stores the concatination of userKeyArray
    String conCat = "";
    //Int key stores the final key to genrate the hashTable
    long key;
    //String to hold the final Encrypt
    static String encrypt = "";

    //Constructor
    //@params input txt and userKey
    hashEncrypt(String ip, String userKey) {
        this.ip = ip;
        System.out.println("Input: " + ip);
        this.userKey = userKey;
    }

    //Method to encrypt userKey
    void encryptKey() {
        try {
            int i;
            userKeyArray = new int[userKey.length()];
            for (i = 0; i < userKey.length(); i++) {
                //Storing the ASCII value into the userKeyArray
                userKeyArray[i] = (int) userKey.charAt(i);
                //Multiplying the value corresponding to position
                userKeyArray[i] = userKeyArray[i] * (i + 2);
                //Concatenating to the string
                conCat += userKeyArray[i];
            }
            //Parsing conCat to Int
            key = Long.parseLong(conCat);
            encrypt += key;
            hashTableGen();
            for (i = 0; i < ip.length(); i++) {
                String temp = Character.toString(ip.charAt(i));
                encrypt += hashSqaure.get(temp) + "%";
            }
            System.out.println("Final Encrypt:" + encrypt);
        } catch (Exception e) {
            hashGUI.message.setText("Encryption : Invalid Input");
        }
    }

    void hashTableGen() {
        //Creating a object of random class 
        Random rand = new Random();
        //Generating a Random number b/n { 65 - 122 }
        int randNum = rand.nextInt((90 - 65) + 1) + 65;
        encrypt += "$" + randNum + "$";
        long tempKey = key + randNum;
        char temp;
        for (int i = 32; i < 127; i++) {
            temp = (char) i;
            hashSqaure.put(Character.toString(temp), tempKey);
            tempKey = tempKey + randNum;
        }
    }

    public static List getKeysFromValue(Map hm, Object value) {
        Set ref = hm.keySet();
        Iterator it = ref.iterator();
        List list = new ArrayList();

        while (it.hasNext()) {
            Object o = it.next();
            if (hm.get(o).equals(value)) {
                list.add(o);
            }
        }
        return list;
    }

}
