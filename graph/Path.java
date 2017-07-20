package graph;

public class Path<T> {
  public Path<T> parent;
  public T node;

  public Path(T node) {
    this.node = node;
  }

  public Path(Path<T> parent, T node) {
    this.parent = parent;
    this.node = node;
  }

  public String toString() {
    if (parent == null) return node.toString();
    return parent.toString() + " --> " + node.toString();
  }
}
