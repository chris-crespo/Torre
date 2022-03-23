package adt;

import java.util.function.*;

class StreamNode<T> {
  T value;
  Supplier<StreamNode<T>> next;

  StreamNode(T value, Supplier<StreamNode<T>> next) {
    this.value = value;
    this.next  = next;
  }

  StreamNode(Supplier<T> sup) {
    this.value = sup.get();
    this.next  = () -> new StreamNode<T>(sup);
  }

  StreamNode<T> interleave(StreamNode<T> node) {
    return new StreamNode<T>(
      value,
      () -> new StreamNode<T>(
        node.value,
        () -> next.get().interleave(node.next.get())));
  }
}

public class Stream<T> {
  private StreamNode<T> node;

  private Stream(StreamNode<T> node) {
    this.node = node;
  }
  
  public T next() { 
    var val = node.value;
    node = node.next.get();
    return val;
  }

  public static <T> Stream<T> infinite(Supplier<T> sup) {
    return new Stream<T>(new StreamNode<T>(sup));
  }
  
  private Stream<T> interleaveNodes(StreamNode<T> node) {
    return new Stream<T>(node.interleave(this.node));
  }

  public Stream<T> interleave(Stream<T> stream) {
    return stream.interleaveNodes(node);
  }
}
