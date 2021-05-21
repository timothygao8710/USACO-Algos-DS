/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author timothy
 */
public class StringBinarySearch {

    public static void main(String[] args) {
        String[] arr = {"contribute", "geeks", "ide", "practice"};
        String x = "hbh";
        List<String> test = new ArrayList();
        test.add("contribute");
        test.add("geeks");
        test.add("geeks");
        test.add("ide");
        test.add("practice");
        int result = strBS(x, test);
//        int result = binarySearch(arr, x);
        if (result == -1) {
            System.out.println("Element not present");
        } else {
            System.out.println("Element found at "
                    + "index " + result);
        }
    }

    //Official Solution
    static int binarySearch(String[] arr, String x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            int res = x.compareTo(arr[m]);

            // Check if x is present at mid 
            if (res == 0) {
                return m;
            }

            // If x greater, ignore left half 
            if (res > 0) {
                l = m + 1;
            } // If x is smaller, ignore right half 
            else {
                r = m - 1;
            }
        }

        return -1;
    }

    static int strBS(String str, List<String> list) {
        int left = 0;
        int right = list.size() - 1;
        int mid = (left + right) / 2;

        while (left <= right) {
            if (list.get(mid).equals(str)) {
                return mid;
            } else if (greater(list.get(mid), str)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }
        //this means that it doesn't exist
        return -1;
    }

    static boolean greater(String one, String two) {
        for (int i = 0; i < Math.min(one.length(), two.length()); i++) {
            if (one.charAt(i) > two.charAt(i)) {
                return true;
            } else if (one.charAt(i) < two.charAt(i)) {
                return false;
            }
        }
        return one.length() > two.length();
    }
}
