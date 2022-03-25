package MergeSortImplementation;

import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction{
    private int[] array;
    private int startIdx;
    private int endIdx;
    private MergeSortHelper myHelper;

    public ParallelMergeSort(int[] array, int startIdx, int endIdx, MergeSortHelper myHelper){
        this.array = array;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        this.myHelper = myHelper;
    }

    // Main computation performed by this task
    protected void compute(){
        if (startIdx < endIdx){
            int midIdx = (startIdx + endIdx) / 2;
            ParallelMergeSort left = new ParallelMergeSort(array, startIdx, midIdx, myHelper);
            ParallelMergeSort right = new ParallelMergeSort(array, midIdx + 1, endIdx, myHelper);
            invokeAll(left, right);
            myHelper.merge(array, startIdx, midIdx, endIdx);
        }
    }

}