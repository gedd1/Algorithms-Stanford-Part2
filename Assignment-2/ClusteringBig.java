import java.util.*;
import java.io.*;

public class ClusteringBig {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("clustering_big.txt"));

        reader.readLine();
        String ls = reader.readLine();
        List<String> graph = new ArrayList<>();
        while (ls != null) {
            graph.add(ls.trim().replaceAll(" ", ""));
            ls = reader.readLine();
        }
        reader.close();

        Set<String> uniqueGraph = new HashSet<>(graph);
        int N = uniqueGraph.size();
        List<Integer> gn = new ArrayList<>();
        for (String i : uniqueGraph) {
            gn.add(Integer.parseInt(i, 2));
        }

        Map<Integer, Integer> uf = new HashMap<>();
        Map<Integer, Integer> rank = new HashMap<>();

        for (Integer i : gn) {
            uf.put(i, i);
            rank.put(i, 0);
        }

        for (int s = 0; s < N; s++) {
            if (s % 10000 == 0) {
                System.out.println("scan " + (s + 1) + ", size=" + new HashSet<>(uf.values()).size());
            }
            List<Integer> neighbors = nb(graph.get(s));
            for (Integer vn : neighbors) {
                if (uf.containsKey(vn)) {
                    merge(gn.get(s), vn, uf, rank);
                }
            }
        }

        for (Integer i : uf.keySet()) {
            find(i, uf);
        }
        System.out.println("DONE, cluster size=" + new HashSet<>(uf.values()).size());
    }

    static List<Integer> nb(String v) {
        int n = 24;
        List<Integer> data = new ArrayList<>();
        char[] s = v.toCharArray();
        for (int i = 0; i < n; i++) {
            char[] ss = s.clone();
            ss[i] = (ss[i] == '0') ? '1' : '0';
            data.add(Integer.parseInt(new String(ss), 2));
            for (int j = i + 1; j < n; j++) {
                char[] sss = ss.clone();
                sss[j] = (sss[j] == '0') ? '1' : '0';
                data.add(Integer.parseInt(new String(sss), 2));
            }
        }
        return data;
    }

    static int find(int i, Map<Integer, Integer> uf) {
        if (uf.get(i) == i) {
            return i;
        } else if (uf.get(uf.get(i)) == uf.get(i)) {
            return uf.get(i);
        } else {
            int newi = uf.get(i);
            while (uf.get(newi) != newi) {
                newi = uf.get(newi);
            }
            uf.put(i, newi);
            return newi;
        }
    }

    static void merge(int i, int j, Map<Integer, Integer> uf, Map<Integer, Integer> rank) {
        i = find(i, uf);
        j = find(j, uf);
        if (i != j) {
            if (rank.get(i) > rank.get(j)) {
                uf.put(j, i);
            } else if (rank.get(i) < rank.get(j)) {
                uf.put(i, j);
            } else {
                uf.put(j, i);
                rank.put(i, rank.get(i) + 1);
            }
        }
    }
}
