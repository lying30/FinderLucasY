import com.sun.jdi.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.Key;
import java.util.LinkedList;
/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 * Completed by: [Lucas Ying]
 **/

public class Finder {

    private static final String INVALID = "INVALID KEY";
    private int tableSize = 16;
    private int numberOfKVPs = 0;
    public final int R = 107;
    private String[] keys;
    private String[] values;


    public Finder() {
        keys = new String[tableSize];
        values = new String[tableSize];
    }

    // reads CSV file and build the hash table
    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] columns = line.split(",");
            if (columns.length > Math.max(keyCol, valCol)){
                String key = columns[keyCol].trim();
                String value = columns[valCol].trim();
                insert(key, value);
            }
        }
        br.close();
    }

    //
    public String query(String key){
        int index = hash(key);
        LinkedList<KeyValuePair> bucket = bucketArray[index];
        for (KeyValuePair kvp: bucket){
            if (kvp.key.equals(key)){
                return kvp.value;
            }
        }
        return INVALID;
    }

    public void insert(String key, String val) {
        // do all this while table is <= 50% capacity
        // else --> larger than 50% call resize()
        // if the spot is empty then insert at that spot in the table
        // if the spot is full check the spot to the right and continue
        // if the spot is
        int index = hash(key);
        while (keys[index] != null) {
            if (keys[index].equals(key)){
                values[index] = val;
                return;
            }
            index = (index + 1) % tableSize;
        }

    }

    // Code Resize
    public void resize() {
        // dont forget to rehash before resize

    }



    private int hash(String key) {
        long hashValue = 0;
        // Use a hash to compute hash value
        for (int i: key.toCharArray()) {
            hashValue = (R * hashValue + i) % tableSize;
        }
        return (int) hashValue;
    }


}