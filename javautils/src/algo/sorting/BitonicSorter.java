package algo.sorting;

public class BitonicSorter<T> {
	public void bitonicSort(T[] keys, int start, int len, boolean isAscending){
		if(len <= 1) return;
		int half = len / 2;
		bitonicSort(keys, start, half, isAscending);
		bitonicSort(keys, start + half, len - half, !isAscending);
		
	}
	
	public void bitonicMerge(T[] keys, int start, int len, boolean isAscending){
		
	}
			
}
