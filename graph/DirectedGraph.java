package graph;

import graph.Vertex;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DirectedGraph<T> {
  private HashMap<T, Vertex<T>> verticies = new HashMap<>();
  private Vertex<T> getOrCreate(T id) {
    if (!verticies.containsKey(id)) {
      addVertex(id);
    }
    return verticies.get(id);
  }

  public Set<T> getOutboundEdges(T id) {
    Vertex<T> v = verticies.get(id);
    if (v == null) return null;
    return java.util.Collections.<T>unmodifiableSet(v.getOutbound());
  }

  public Set<T> getInboundEdges(T id) {
    Vertex<T> v = verticies.get(id);
    if (v == null) return null;
    return java.util.Collections.<T>unmodifiableSet(v.getInbound());
  }

  public void addVertex(T id) {
    if (verticies.containsKey(id)) return;
    verticies.put(id, new Vertex<T>(id));
  }

  public void addEdge(T v1, T v2) {
    Vertex<T> _v1 = getOrCreate(v1);
    Vertex<T> _v2 = getOrCreate(v2);
    _v1.getOutbound().add(v2);
    _v2.getInbound().add(v1);
  }

  public void removeVertex(T id) {
    Vertex<T> v = verticies.get(id);
    HashSet<T> inbound = v.getInbound();
    HashSet<T> outbound = v.getOutbound();
    for (T ib : inbound) {
      removeEdge(ib, id);
    }
    for (T ob: outbound) {
      removeEdge(id, ob);
    }
    verticies.remove(id);
  }

  public void removeEdge(T v1, T v2) {
    Vertex<T> _v1 = verticies.get(v1);
    if (_v1 == null) return;
    Vertex<T> _v2 = verticies.get(v2);
    if (_v2 == null) return;

    _v1.getOutbound().remove(v2);
    _v2.getInbound().remove(v1);
  }

  public void print() {
    for(Map.Entry<T, Vertex<T>> entry : verticies.entrySet()) {
      System.out.println(entry.getKey());
      entry.getValue().print();
    }
  }
}
