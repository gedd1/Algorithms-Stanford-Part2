
import java.io.*;
import java.util.*;

public class FloydWarshall {
    static final int V = 1000;

    public static void floydWarshall(int[][] dist) {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) {
                        continue;
                    } else if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                System.out.println("NOT POSSIBLE! negative edge cycle exists");
                return;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] < min) min = dist[i][j];
            }
        }
        System.out.println(min);
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("Assignment-4/g3.txt"));
        String[] parts = in.nextLine().split(" ");

        int[][] dist = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = Integer.MAX_VALUE;
            }
        }

        String line;
        while (in.hasNextLine()) {
            line = in.nextLine();
            parts = line.split(" ");
            int src = Integer.parseInt(parts[0]) - 1;
            int dst = Integer.parseInt(parts[1]) - 1;
            int wt = Integer.parseInt(parts[2]);
            dist[src][dst] = wt;
        }

        floydWarshall(dist);
        in.close();
    }
}
