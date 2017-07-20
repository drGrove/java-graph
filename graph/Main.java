package graph;

import graph.DirectedGraph;
import graph.Path;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
  private static <T> String getPath(DirectedGraph<T> g, T start, T end, HashSet<T> touched) {
    touched.add(start);
    Set<T> out = g.getOutboundEdges(start);
    if (out == null) return "";

    if (out.contains(end)) {
      return start.toString() + " --> " + end.toString();
    }

    for(T o : out) {
      if (!touched.contains(o)) {
        String p = getPath(g, o, end, touched);
        if (p.equals("")) continue;
        return start.toString() + " --> " + p.toString();
      }
    }
    return "";
  }
  public static <T> String shortestPath(DirectedGraph<T> graph, T start, T end) {
    Queue<Path<T>> q = new LinkedList<Path<T>>();
    HashSet<T> touched = new HashSet<T>();
    q.add(new Path<T>(start));
    touched.add(start);
    while(!q.isEmpty()) {
      Path<T> curr = q.remove();
      Set<T> out = graph.getOutboundEdges(curr.node);
      if (out == null) break;
      if (out.contains(end)) {
        Path<T> np = new Path<T>(curr, end);
        return np.toString();
      } else {
        for(T o: out) {
          if (!touched.contains(o)) {
            touched.add(o);
            q.add(new Path<T>(curr, o));
          }
        }
      }
    }
    return "No path";
  }

  public static <T> String path(DirectedGraph<T> graph, T start, T end) {
    HashSet<T> touched = new HashSet<T>();
    String out = Main.<T>getPath(graph, start, end, touched);
    if (out.equals("")) return "No path";
    return out;
  }
  public static void main(String[] args) {
    DirectedGraph<String> g = new DirectedGraph<String>();
    g.addVertex("a");
    g.addVertex("b");
    g.addVertex("c");
    g.addVertex("d");
    g.addVertex("e");
    g.addEdge("a", "b");
    g.addEdge("a", "c");
    g.addEdge("a", "d");
    g.addEdge("a", "e");
    g.addEdge("e", "a");
    g.addEdge("c", "b");
    g.addEdge("b", "c");
    g.addEdge("e", "a");
    g.addEdge("d", "e");
    for(char c: "abcdef".toCharArray()) {
      for(char d: "abcdef".toCharArray()) {
        String cString = c + "";
        String dString = d + "";
        String p1 = Main.<String>path(g, cString, dString);
        String p2 = Main.<String>shortestPath(g, cString, dString);
        Boolean shortestFound = p1.equals(p2);
        System.out.printf("%c -> %c : %s : %s : %b\n", c, d, p1, p2, shortestFound);
      }
    }
  }
}
