/**
 * Knapsack
 */

import java.util.*;
import java.io.*;

public class Knapsack {

    int res;
    public Knapsack() throws FileNotFoundException {
        Scanner in = new Scanner(new File("Assignment-3/knapsack1.txt"));
        int ksize = in.nextInt();
        int n = in.nextInt();

        int[][] arr = new int[n][2]; //val-size 
        int[][] dp = new int[n+1][ksize+1];
        for (int i = 0; i<n;i++){
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }

        
        for (int i = 1; i<=n; i++){
            for (int size = 0; size <= ksize; size++){
                
                dp[i][size] = Math.max(dp[i-1][size], size - arr[i-1][1] >= 0 ? dp[i-1][size-arr[i-1][1]] + arr[i-1][0] : Integer.MIN_VALUE);
            }    
        }

        System.out.println(res = dp[n][ksize]);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Knapsack k = new Knapsack();
        System.out.println(k.res);
    }
}