package com.hyssop.algorithm.leetcode.questions.dpproblem;

import java.util.Arrays;
import java.util.Comparator;

public class Lecode56 {
    public int[][] merge(int[][] intervals) {
        int[][] result = new int[intervals.length][2];
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        int resultI=0;
        result[resultI][0]=intervals[0][0];
        result[resultI][1]=intervals[0][1];

        for(int j=1;j<intervals.length;j++){
            if(result[resultI][1]>=intervals[j][0]){

                result[resultI][1]=intervals[j][1];
            }else {
                resultI++;
                result[resultI][0]=intervals[j][0];
                result[resultI][1]=intervals[j][1];
            }
        }
        return result;

    }

    public static void main(String[] args) {
      int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
      //[1,3],[2,6],[8,10],[15,18]
        Lecode56 lecode56 = new Lecode56();
        int[][] result= lecode56.merge(intervals);
        for(int i=0;i<result.length;i++){
            for(int j=0;j<result[i].length;j++){
                System.out.println(result[i][j]);
            }
        }
    }
}
