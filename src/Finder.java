import java.io.BufferedReader;
import java.io.IOException;
/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: [Lucas Ying]
 **/

public class Finder {

    private static final String INVALID = "INVALID KEY";
    private final int numberOfBuckets = 10000;
    public final int R = 12;
    public final int P = 100;

    public Finder() {}

    // reads CSV file
    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        // TODO: Complete the buildTable() function!

        String line = br.readLine();
        for (int i =0; i < line.split("", 4).length; i++){

        }


        br.close();
    }

    //
    public String query(String key){
        int index = hash(key);
        LinkedList<KeyValuePair>

        return INVALID;
    }

    private int hash(String key) {
        long hashValue = 0;
        // Use a hash to compute hash value
        for (int i: key.toCharArray()) {
            hashValue = (R + i) % P;
        }
        return hashValue % numberOfBuckets;
    }

}