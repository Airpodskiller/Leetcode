package com.shady.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shadyyyyyl
 * @Date: 2022/11/15/23:01
 * @Description: Using differential array
 * Time: O(n) Space: O(n)
 * https://leetcode.com/problems/range-addition/
 */
public class RangeAddition {
   static class Difference{
    private int[] diff;
    public Difference(int[] nums){
      diff = new int[nums.length];
      // 1 2  0  8  -1  7 original array
      // 1 1 -2  8  -9  8 difference array
      diff[0] = nums[0];
      for (int i = 1; i < diff.length; i++) {
        diff[i] = nums[i] - nums[i - 1];
      }
    }
    /**
     * increase the value of the original array in the given indexes.
     * @param low
     * @param high
     * @param val
     */

    // 1 2  0  8  -1  7 original array
    // 1 1 -2  8  -9  8 difference array
    public void modify(int low, int high, int val){
      diff[low] += val;
      if(high < diff.length - 1){//since if high is at the last index, there's no need to do this.
        diff[high + 1] -= val;//In this case, elements positioned behind high will not increase.

      }
    }

    /**
     * Return the result array
     */
    // 1 2  0  8  -1  7 original array
    // 1 1 -2  8  -9  8 difference array
    public int[] getResult(){
      int res[] = new int[diff.length];
      res[0] = diff[0];
      for (int i = 1; i < diff.length; i++) {
          res[i] = diff[i] + res[i - 1];
      }
      return res;
    }

  }





  public int[] getModifiedArray(int length, int[][] updates) {
      //Use the diffrece class and call its methods to solve this problem.
    int nums[] = new int[length];
    Difference difference = new Difference(nums);
    for (int[] a : updates) {
      difference.modify(a[0], a[1], a[2]);


    }
    return difference.getResult();
  }
}
