package MergeSortImplementation;

import java.util.Arrays;

public class MergeSortHelper {
    // Merge function taken from https://www.geeksforgeeks.org/merge-sort/ and modified
    // Parallelizing this function will lead to large improvements in runtime efficiency
    public void merge(int[] arr, int startIdx, int midIdx, int endIdx){
        // Find size of each subarray
        int len1 = midIdx - startIdx + 1;
        int len2 = endIdx - midIdx;
  
        // Make temp arrays and copy data to them
        int leftArr[] = Arrays.copyOfRange(arr, startIdx, midIdx + 1);
        int rightArr[] = Arrays.copyOfRange(arr, midIdx + 1, endIdx + 1);
        
        
        // Merge arrays
        int i = 0, j = 0; // Indexes for leftArr and rightArr
        int k = startIdx; // Initial index of array

        
        // Keep going until you reach the end of one of the two
        while (i < len1 && j < len2) {
            if (leftArr[i] <= 
            rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            }
            else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
  
        // Dump the rest (if any) of leftArr
        while (i < len1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
  
        // Dump the rest (if any) of rightArr
        while (j < len2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    // Makes sure that the array is sorted
    public void verifySorted(int[] arr) throws Exception{
        for (int i = 0; i < arr.length - 1; i++){
            if (arr[i] > arr[i + 1]){
                throw new Exception("Array is not sorted!");
            }
        }
    }
}

