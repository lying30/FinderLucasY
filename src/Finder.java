import java.io.BufferedReader;
import java.io.IOException;
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
    private final int numberOfBuckets = 10000;
    public final int R = 12;
    public final int P = 100;

    private final LinkedList<KeyValuePair>[] bucketArray;

    public Finder() {
        // initialize the array of linked lists
        bucketArray = new LinkedList[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            bucketArray[i] = new LinkedList<>();
        }
    }

    // reads CSV file and build the hash table
    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] columns = line.split(",");
            if (columns.length > Math.max(keyCol, valCol)){
                String key = columns[keyCol].trim();
                String value = columns[valCol].trim();

                int index = hash(key);
                bucketArray[index].add(new KeyValuePair(key, value));
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

    private int hash(String key) {
        long hashValue = 0;
        // Use a hash to compute hash value
        for (int i: key.toCharArray()) {
            hashValue = (R * hashValue + i) % P;
        }
        return (int)(hashValue % numberOfBuckets);
    }

    private static class KeyValuePair {
        String key;
        String value;

        KeyValuePair(String key, String value){
            this.value = value;
            this.key = key;
        }
    }

}