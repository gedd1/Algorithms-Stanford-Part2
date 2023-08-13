import java.util.*;

import java.io.*;

public class PrimsMST {
    long totalcost;
    public PrimsMST() throws FileNotFoundException {
        
        Scanner in = new Scanner(new File("Assignment-1/edges.txt"));
        int n = in.nextInt();
        int m = in.nextInt();

        List<List<int[]>> vertices = new ArrayList<List<int[]>>();
        for (int i = 0; i<n; i++){
            vertices.add(new ArrayList<int[]>());
        }

        for (int i = 0; i<m; i++){
            int v = in.nextInt();
            int u = in.nextInt();
            int cost = in.nextInt();

            vertices.get(v-1).add(new int[]{u-1,cost});
            vertices.get(u-1).add(new int[]{v-1,cost});
        }

        in.close();

        // costlara göre sırala
        boolean[] done = new boolean[n];
        PriorityQueue<int[]> undone = new PriorityQueue<int[]>((a,b)->{
            return a[1] - b[1];
        });
        done[0] = true;
        for (int i = 0; i < vertices.get(0).size(); i++){
            int[] neighbour = vertices.get(0).get(i);
            undone.add(neighbour);
        }
        int donecount = 1;
        long totalcost = 0;
        while (donecount < n){
            int[] v;
            while (!undone.isEmpty() && done[undone.peek()[0]]) undone.poll();

            v = undone.poll();
            totalcost += v[1];

            done[v[0]] = true;
            donecount++;

            for (int[] u : vertices.get(v[0])){
                if (!done[u[0]]) undone.add(u);
            }
        }
        this.totalcost = totalcost;
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrimsMST p = new PrimsMST();
        System.out.println(p.totalcost);
    }

}
