
import java.io.BufferedReader;
import java.io.IOException;
/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 * Completed by: [Lucas Ying]
 **/

public class Finder {

    private static final String INVALID = "INVALID KEY";
    // Initial size of Hash Table --> chose this number after trial and error for what was faster
    private int tableSize = 9;
    private int numberOfKVPs = 0;
    // Size of prime number for multiplier in Hash Function --> chose this number after trial and error for what was faster
    public final int R = 157;
    private String[] keys;
    private String[] values;


    public Finder() {
        // Initialize the arrays of keys and values with the initial table size
        keys = new String[tableSize];
        values = new String[tableSize];
    }

    // Reads CSV file and builds the hash table based on the values of keyCol and valCol
    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        String line;
        // Read each line of the CSV file
        while ((line = br.readLine()) != null) {
            // Split the line into columns using commas
            String[] columns = line.split(",");
            // Check whether there are enough columns to access the specified key and value columns
            if (columns.length > Math.max(keyCol, valCol)){
                String key = columns[keyCol];
                String value = columns[valCol];
                // Insert key value pair into hash table
                insert(key, value);
            }
        }
        br.close();
    }

    // Method to get the value associated with the given key
    public String query(String key){
        int index = hash(key);
        // Linear probe to find the key
        while (keys[index] != null){
            // If the key is found, return the value there
            if (keys[index].equals(key)){
                return values[index];
            }
            // Move to the next index
            index = (index + 1) % tableSize;
        }
        // If the key is not found, return INVALID
        return INVALID;
    }

    // Inserts the key value pair into the hash table
    public void insert(String key, String val) {
        int index = hash(key);

        // Linear probe to find an empty spot or update an existing key
        while (keys[index] != null) {
            // If the key already exists simply update it at its position
            if (keys[index].equals(key)){
                values[index] = val;
                return;
            }
            // Move to the next index
            index = (index + 1) % tableSize;
        }

        // Insert the key value pair into the hash table
        keys[index] = key;
        values[index] = val;
        numberOfKVPs++;

        // If the table becomes at least half full resize it
        if (numberOfKVPs >= tableSize / 2) {
            resize();
        }

    }

    // When table becomes at least half full perform resizing method to resize the hash table
    public void resize() {
        // Store the old table size
        int oldSize = tableSize;
        // Double the size
        tableSize *= 2;

        // Store where the old keys and values were
        String[] oldKeys = keys;
        String[] oldValues = values;

        // Update the arrays with the new size of the table
        keys = new String[tableSize];
        values = new String[tableSize];
        // Reset number of key value pairs
        numberOfKVPs = 0;

        // Re-insert all the old key value pairs back into the new hash table
        for (int i = 0; i < oldSize; i++) {
            if (oldKeys[i] != null) {
                insert(oldKeys[i], oldValues[i]);
            }
        }
    }

    // Used Horner's method for hashing to hash the given key
    private int hash(String key) {
        long hashValue = 0;
        // Use a hash to compute hash value
        for (int i: key.toCharArray()) {
            hashValue = (R * hashValue + i) % tableSize;
        }
        return (int) hashValue;
    }


}