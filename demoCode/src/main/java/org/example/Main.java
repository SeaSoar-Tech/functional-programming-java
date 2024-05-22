package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import org.w3c.dom.Node;

public class Main {
  public static int[] findRequestsInQueue(int[] wait) {
    TreeMap<Integer, Integer> counts = new TreeMap<>();
    int[] res = new int[wait.length];

    // Populate the TreeMap with the count of each wait time
    for (int time : wait) {
      counts.put(time, counts.getOrDefault(time, 0) + 1);
    }

    int requests = wait.length; // Number of pending requests

    for (int i = 0; i < wait.length; i++) {
      // Remove all expired requests
      while (!counts.isEmpty() && counts.firstKey() <= i) {
        int expiredTime = counts.firstKey();
        requests -= counts.get(expiredTime);
        counts.remove(expiredTime);
      }

      // Update the result array
      res[i] = requests;
      if(counts.containsKey(wait[i])){
        counts.put(wait[i], counts.get(wait[i]) - 1);
        if(counts.get(wait[i]) == 0){
          counts.remove(wait[i]);
        }
        requests -= 1;
      }
    }

// Handle the final zero: ensure there is exactly one zero at the end
    // Handle the final zero: ensure there is exactly one zero at the end
    int lastIndex = res.length - 1;
    while (lastIndex > 0 && res[lastIndex] == 0) {
      lastIndex--;
    }

    if (lastIndex < res.length - 1) {
      return Arrays.copyOf(res, lastIndex + 2); // Include one zero
    } else {
      int[] resultWithZero = Arrays.copyOf(res, res.length + 1);
      resultWithZero[res.length] = 0;
      return resultWithZero;
    }
  }


  public static void main(String[] args) {
    int[] wait1 = {2, 2, 3, 1};
    System.out.println(Arrays.toString(findRequestsInQueue(wait1))); // Output: [4, 2, 1, 0]

    int[] wait2 = {4, 4, 4};
    System.out.println(Arrays.toString(findRequestsInQueue(wait2))); // Output: [3, 2, 1, 0]

    int[] wait3 = {3, 1, 2, 1};
    System.out.println(Arrays.toString(findRequestsInQueue(wait3))); // Output: [4, 1, 0]
  }
}