/**
 * Weighted - length sum calculation assignment
 */
import java.util.*;
import java.io.*;

public class Greedy {

    long sum;
    public Greedy() throws FileNotFoundException {
        Scanner in = new Scanner(new File("Assignment-1/jobs.txt"));
        int n = in.nextInt();
        PriorityQueue<int[]> pq = new PriorityQueue<>(n, (a,b)->{
            if ((double)a[0] / a[1] > (double)b[0] / b[1]) return -1;
            else if ((double)a[0] / a[1] == (double)b[0] / b[1]) return b[0] - a[0];
            return +1;
        });
        for (int i = 0; i<n;i++){
            int[] job = new int[]{in.nextInt(),in.nextInt()};
            pq.add(job);
        }

        long time = 0, sum = 0;
        while (!pq.isEmpty()){
            int[] job = pq.poll();
            time += job[1];
            sum += job[0] * time;
        }
        this.sum = sum;
    }


    public static void main(String[] args) throws FileNotFoundException {
        
        Greedy g = new Greedy();
        System.out.println(g.sum);
    }
}