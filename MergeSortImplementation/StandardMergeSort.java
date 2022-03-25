package MergeSortImplementation;
public class StandardMergeSort {
    private MergeSortHelper myHelper;

    public StandardMergeSort(int[] array, int startIdx, int endIdx, MergeSortHelper myHelper){
        this.myHelper = myHelper;
        standardMergeSort(array, startIdx, endIdx);
    }

    private void standardMergeSort(int arr[], int l, int r){
        if (l < r) {
 
            // Same as (l + r) / 2, but avoids overflow
            // for large l and r
            int m = l + (r - l) / 2;
 
            // Sort first and second halves
            standardMergeSort(arr, l, m);
            standardMergeSort(arr, m + 1, r);
 
            myHelper.merge(arr, l, m, r);
        }
    }
}
