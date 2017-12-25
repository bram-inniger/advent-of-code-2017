package be.inniger.advent.util;

import java.util.stream.IntStream;

// TODO instead of Quick Find make this proper rank weighted and path compressed
public final class UnionFind {

  private final int[] parents;

  public UnionFind(int n) {
    parents = IntStream.range(0, n).toArray();
  }

  public int find(int index) {
    return parents[index];
  }

  public void union(int aIndex, int bIndex) {
    int aParent = parents[aIndex];
    int bParent = parents[bIndex];

    for (int i = 0; i < parents.length; i++) {
      if (parents[i] == aParent) {
        parents[i] = bParent;
      }
    }
  }
}
