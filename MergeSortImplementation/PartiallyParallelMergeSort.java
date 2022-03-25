package MergeSortImplementation;


import java.util.concurrent.RecursiveAction;

public class PartiallyParallelMergeSort extends RecursiveAction{
    private int[] array;
    private int startIdx;
    private int endIdx;
    private int max = 0;
    private MergeSortHelper myHelper;

    public PartiallyParallelMergeSort(int[] array, int startIdx, int endIdx, int max, MergeSortHelper myHelper){
        this.array = array;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        this.max = max;
        this.myHelper = myHelper;
    }
    
    // Main computation performed by this task
    protected void compute(){
        if (startIdx < endIdx){
            // If less than the maximum value, perform a normal merge sort
            if (endIdx - startIdx <= max){
                new StandardMergeSort(array, startIdx, endIdx, myHelper);
            } else {
                int midIdx = (startIdx + endIdx) / 2;
                PartiallyParallelMergeSort left = new PartiallyParallelMergeSort(array, startIdx, midIdx, max, myHelper);
                PartiallyParallelMergeSort right = new PartiallyParallelMergeSort(array, midIdx + 1, endIdx, max, myHelper);
                invokeAll(left, right);
                myHelper.merge(array, startIdx, midIdx, endIdx);
            }
        }
    }
}