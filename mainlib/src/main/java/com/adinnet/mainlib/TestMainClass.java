package com.adinnet.mainlib;

/**Java main test */
public class TestMainClass {

    public static void main(String args[]) {
        testTestUtils();
//        test1();
    }

    private static void testTestUtils() {
    }


    /***
     * nums = [2, 7, 11, 15], target = 9
     */
    public static  void test1(){
        int nums[] = new int[]{2, 7, 11, 15};
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            for (int j = i; j < nums.length; j++) {
                if (temp + nums[j] == 9) {
                    System.out.println(temp + "====" + nums[j]);
                }
            }
        }
    }



}
