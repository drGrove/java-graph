package graph;
import java.util.HashSet;

public class Vertex<T> {
  public T id;
  private HashSet<T> inbound = new HashSet<T>();
  private HashSet<T> outbound = new HashSet<T>();

  public Vertex(T id) {
    this.id = id;
  }

  public HashSet<T> getInbound() {
    return inbound;
  }

  public HashSet<T> getOutbound() {
    return outbound;
  }

  public void print() {
    for(T entry : inbound) {
      System.out.println("   <-- " + entry);
    }
    for(T entry : outbound) {
      System.out.println("   --> " + entry);
    }
  }
}
