package kmpalgorithm;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
public class KMPAlgorithm {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<Integer> foundAt = new ArrayList<Integer>();
        String pat, text;
        int ch = 1, i;
        while (ch != 4) {
            System.out.println("1. ENTER TEXT MANUALLY");
            System.out.println("2. ENTER TEXT VIA txt FILE");
            System.out.println("3. ENTER TEXT VIA csv FILE");
            System.out.println("4. EXIT");
            ch = in.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("ENTER THE TEXT : ");
                    text = in.next();
                    System.out.println("ENTER THE PATTERN : ");
                    pat = in.next();
                    foundAt = KMPAlgo(text, pat);
                    for (i = 0; i < foundAt.size(); i++) {
                        System.out.println("Pattern " + pat + " Found With Shift " + foundAt.get(i));
                    }
                    break;
                case 2:
                    text = fileReader();
                    System.out.println("ENTER THE PATTERN : ");
                    pat = in.next();
                    foundAt = KMPAlgo(text, pat);
                    //for(i=0 ; i<foundAt.size()  ; i++)
                    //  System.out.println("Pattern "+pat+ " Found With Shift "+ foundAt.get(i));
                    System.out.println("pattern found at " + foundAt.size() + " locations");
                    break;
                case 3:
                    int countg = 0,
                     countb = 01;
                    String[] good = {"fast", "great", "excellent", "living", "good", "fantastic"};
                    String[] bad = {"bad", "worthless", "worst", "unsatisfactory", "disappointing"};
                    countg = KMPCsv(good);
                    countb = KMPCsv(bad);
                    System.out.println(countg);
                    System.out.println(countb);
                    System.out.println("Star rating = " + (((float) countg / (float) (countg + countb)) * 5) + "/5" + "");
                    break;
                case 4:
                    break;
            }
        }
    }
    public static String fileReader() {
        String line, text = "";
        try {
            FileReader f = new FileReader("text.txt");
            BufferedReader br = new BufferedReader(f);
            while ((line = br.readLine()) != null) {
                line.trim();
                text = text + line;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return text;
    }
    public static int KMPCsv(String[] words) {
        String line = "", text = "";
        String[] cols;
        int coll, i, count = 0;
        try {
            FileReader f = new FileReader("Customerreviews.csv");
            BufferedReader br = new BufferedReader(f);
            while ((line = br.readLine()) != null) {
                line.trim();
                cols = line.split(",");
                coll = cols.length;
                text = cols[coll - 1];
                for (i = 0; i < words.length; i++) {
                    count += KMPAlgo(text, words[i]).size();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }
    public static ArrayList<Integer> KMPAlgo(String text, String pat) {
        int n = text.length();
        int m = pat.length();
        int preTable[] = compute_pi_table(pat);
        int i, q = 0;
        ArrayList<Integer> found = new ArrayList<Integer>();
        for (i = 0; i < n; i++) {
            while (q > 0 && pat.charAt(q) != text.charAt(i)) {
                q = preTable[q - 1];
            }
            if (pat.charAt(q) == text.charAt(i)) {
                q = q + 1;
            }
            if (q == m) {
                q = preTable[q - 1];
                found.add(i - m + 1);
            }
        }
        return found;
    }
    public static int[] compute_pi_table(String pat) {
        int m = pat.length(), k = 0, q;
        int[] pi = new int[m];
        pi[0] = 0;
        for (q = 1; q < m; q++) {
            while (k > 0 && pat.charAt(k) != pat.charAt(q)) {
                k = pi[k];
            }
            if (pat.charAt(k) == pat.charAt(q)) {
                k = k + 1;
            }
            pi[q] = k;
        }
        return pi;
    }
}
