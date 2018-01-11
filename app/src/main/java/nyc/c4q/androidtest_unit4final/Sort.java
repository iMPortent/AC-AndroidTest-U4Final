package nyc.c4q.androidtest_unit4final;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justiceo on 1/7/18.
 */

public class Sort {

    /**
     * Sorts a list using the selection sort algorithm.
     * See lecture on sorting: https://github.com/C4Q/AC-Android/tree/v2/DSA/sorting
     * <p>
     * When `isAscending` is true, the list is sorted in ascending alphabetical order from a to z,
     * otherwise it is sorted in descending order from z to a.
     *
     * @param list
     * @param isAscending
     */
    public static void selectionSort(List<String> list, boolean isAscending) {
        // TODO: Implement selection sort.
        // You may not use Collections.sort or its equivalent
        // You may not implement another sorting algorithm that is not "selection sort"
        // Tip: Try a version without ordering first.
        String thisWord;
        ArrayList<String> temp = new ArrayList<>();

        if (isAscending) {
            final int max = list.size();
            //        ArrayList<String>checkwords = ;
            for (int j = 0; j < max; j++) {
                thisWord = list.get(0);

                for (int i = 0; i < list.size(); i++) {
                    if (thisWord.compareTo(list.get(i)) > 0) {
                        thisWord = list.get(i);
                    }

                }
                temp.add(thisWord);
                list.remove(thisWord);
            }
            list.addAll(temp);
        }
    }
}
