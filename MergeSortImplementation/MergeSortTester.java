package MergeSortImplementation;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class MergeSortTester {
    public static int NUM_TRIALS = 5000;
    public static final int NUM_VARIATIONS = 4;
    public static int MAX_NUM_ELEMS = 1000000;
    public static void main(String[] args) throws FileNotFoundException {
        // Variables keeping track of time we will gather and what algorithm it pertains to
        if (args.length > 1){
            NUM_TRIALS = Integer.parseInt(args[0]);
            MAX_NUM_ELEMS = Integer.parseInt(args[1]);
            System.out.println("Conducting " + NUM_TRIALS + " trials with max number of elements: " + MAX_NUM_ELEMS);
        }
        
            
        long[][] times = new long[NUM_VARIATIONS][NUM_TRIALS];
        String[] names = {"Standard Merge Sort Time (in ns)", "Parallel Merge Sort Time (in ns)", 
                          "Partially Parallel Merge Sort Time (in ns)", "Java Parallel Merge Sort Time (in ns)"};
        
        int[] numElements = new int[NUM_TRIALS];
        MergeSortHelper myHelper = new MergeSortHelper();
        

        Random rand = new Random();
        long startTime;
        for (int i = 0; i < NUM_TRIALS; i++) {
            if (i % 500 == 0 && i !=0)
                System.out.println(i);
            startTime = 0;

            int len = rand.nextInt(MAX_NUM_ELEMS);
            numElements[i] = len;

            // Generate random array of len and clone it 3 times
            int[][] arrays = new int[NUM_VARIATIONS][len];
            arrays[0] = IntStream.generate(() -> new Random().nextInt() + len).limit(len).toArray();
            for (int j = 1; j < arrays.length; j++){
                arrays[j] = arrays[0].clone();
            }
            
            startTime = System.nanoTime();
            new StandardMergeSort(arrays[0], 0, len - 1, myHelper);
            times[0][i] = System.nanoTime() - startTime;
            // System.out.println("Done with iteration #" + (i + 1) + " of Standard Merge Sort");
            // Check if array is sorted
            try {
                myHelper.verifySorted(arrays[0]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // Make pool with num of processors
            startTime = System.nanoTime();
            ForkJoinPool fjp = new ForkJoinPool(Runtime.getRuntime().availableProcessors() - 1);
            fjp.invoke(new ParallelMergeSort(arrays[1], 0, len - 1, myHelper));
            times[1][i] = System.nanoTime() - startTime;
            fjp.shutdown();
            // System.out.println("Done with iteration #" + (i + 1) + " of Parallel Merge Sort");
            
            // Check if array is sorted
            try {
                myHelper.verifySorted(arrays[1]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
            // Make pool with num of processors
            startTime = System.nanoTime();
            ForkJoinPool fjp2 = new ForkJoinPool(Runtime.getRuntime().availableProcessors() - 1);
            fjp2.invoke(new PartiallyParallelMergeSort(arrays[2], 0, len - 1, (int)Math.pow(len, 0.25f), myHelper)); // Use 4th root as point to use sequential (need to find better value)
            times[2][i] = System.nanoTime() - startTime;
            // System.out.println("Done with iteration #" + (i + 1) + " Partially Parallel Merge Sort);
            
            // Check if array is sorted
            try {
                myHelper.verifySorted(arrays[2]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            startTime = System.nanoTime();
            Arrays.parallelSort(arrays[3]);
            long endTime = System.nanoTime();
            times[3][i] = endTime - startTime;
            // System.out.println("Done with iteration #" + (i + 1) + " Java Parallel Sort");

            // Check if array is sorted
            try {
                myHelper.verifySorted(arrays[3]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
            // System.out.println("Done with iteration #" + (i + 1));
        }
        
        // Reference https://stackoverflow.com/questions/30073980/java-writing-strings-to-a-csv-file
        try (PrintWriter writer = new PrintWriter("Data/data.csv")) {
            StringBuilder sb = new StringBuilder();
            // These will be the keys for our csv
            sb.append("Number of Elements");
            sb.append(",");
            int i, j;
            for (i = 0; i < NUM_VARIATIONS - 1; i++){
                sb.append(names[i]);
                sb.append(",");
            }
            sb.append(names[i]);
            sb.append("\n");
            
            for (j = 0; j < NUM_TRIALS - 1; j++){
                sb.append(numElements[j]);
                sb.append(",");
                for (i = 0; i < NUM_VARIATIONS - 1; i++){
                    sb.append(times[i][j]);
                    sb.append(",");
                }
                sb.append(times[i][j]);
                sb.append("\n");
            }

            sb.append(numElements[j]);
            sb.append(",");

            for (i = 0; i < NUM_VARIATIONS - 1; i++){
                sb.append(times[i][j]);
                sb.append(",");
            }
            sb.append(times[i][j]);
            sb.append("\n");
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }        
}
