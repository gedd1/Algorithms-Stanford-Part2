import java.io.*;
import java.util.*;

public class TravelingSalesmanProblem {
    static final int n = 25;

    public static double tsp(double[][] dist, int trigger, int s) {
        if (trigger == (1 << n) - 1) {
            return dist[s][0];
        }
        double ans = Double.MAX_VALUE;

        for (int city = 0; city < n; city++) {
            // for every city, shifts to left so basically multiplies by 2.
            if ((trigger & (1 << city)) == 0) {
                double newAns = dist[s][city] + tsp(dist, trigger | (1 << city), city);
                ans = Math.min(ans, newAns);
            }
        }
        return ans;
    }

    public static double getD(ArrayList<Pair> v, int i, int j){
        double d =  Math.sqrt(Math.pow((double)v.get(j).x - v.get(i).x, 2) + Math.pow(v.get(j).y - v.get(i).y, 2));
        return d;
    }

    public static void main(String[] args) throws IOException {
        double[][] dist = new double[n][n];
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0.0;
        }
        Scanner in = new Scanner(new File("Assignment-5/tsp.txt"));
        in.nextLine();

        ArrayList<Pair> v = new ArrayList<>();
        String line;
        while (in.hasNextLine()) {
            line = in.nextLine();
            String[] parts = line.split(" ");
            double x = Double.parseDouble(parts[0]);
            double y = Double.parseDouble(parts[1]);
            v.add(new Pair(x, y));
        }
        in.close();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double d = getD(v,i,j);
                dist[i][j] = d;
                dist[j][i] = d;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(tsp(dist, 1, 0));
    }

    static class Pair {
        double x, y;

        public Pair(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
