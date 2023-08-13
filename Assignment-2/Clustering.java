import java.util.*;
import java.io.*;

public class Clustering {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("clustering1.txt"));

        Map<Pair, Integer> graph = new HashMap<>();
        reader.readLine();
        String ls = reader.readLine();
        while (ls != null) {
            String[] parts = ls.split(" ");
            int node1 = Integer.parseInt(parts[0]);
            int node2 = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            graph.put(new Pair(node1, node2), weight);
            ls = reader.readLine();
        }
        reader.close();

        Map<Pair, Integer> g = new HashMap<>(graph);

        int[] c = new int[500];
        for (int i = 0; i < 500; i++) {
            c[i] = i + 1;
        }
        int cnum = 500;

        while (true) {
            Pair edge = getMinKey(g);
            int distance = g.remove(edge);
            int l1 = c[edge.node1 - 1];
            int l2 = c[edge.node2 - 1];

            if (l1 != l2 && cnum > 4) {
                cnum--;
                for (int i = 0; i < 500; i++) {
                    if (c[i] == l2) {
                        c[i] = l1;
                    }
                }
            } else if (l1 != l2 && cnum == 4) {
                System.out.println("Max space cluster: " + distance);
                break;
            }
        }
    }

    static Pair getMinKey(Map<Pair, Integer> map) {
        Pair minPair = null;
        int minValue = Integer.MAX_VALUE;

        for (Map.Entry<Pair, Integer> entry : map.entrySet()) {
            if (entry.getValue() < minValue) {
                minPair = entry.getKey();
                minValue = entry.getValue();
            }
        }

        return minPair;
    }

    static class Pair {
        int node1;
        int node2;

        Pair(int node1, int node2) {
            this.node1 = node1;
            this.node2 = node2;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair pair = (Pair) obj;
            return (node1 == pair.node1 && node2 == pair.node2) || (node1 == pair.node2 && node2 == pair.node1);
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(node1) + Integer.hashCode(node2);
        }
    }
}
