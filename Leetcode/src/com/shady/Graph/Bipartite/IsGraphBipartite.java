package com.shady.Graph.Bipartite;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shadyyyyyl
 * @Date: 2023/01/08/21:35
 * @Description:
 * https://leetcode.com/problems/is-graph-bipartite/
 */
public class IsGraphBipartite {
  //determine whether the given graph is bipartite
  private boolean Is_Bipartite = true;

  //Record the colors of nodes in the graph
  private boolean[] color;

  //To check whether the current node has been visited
  private boolean[] visited;

  public boolean isBipartite(int[][] graph){
    int n = graph.length;
    color = new boolean[n];
    visited = new boolean[n];
    
    //Since the graph may not be fully connected, we must run a DFS for each node of the graph.
    for (int i = 0; i < n; i++) {
      if(!visited[i]){
        traverse(graph, i);
      }
      
    }

    return Is_Bipartite;
  }
  public void traverse(int[][] graph, int v){
    //First we should notice that if 'Is_Bipartite' is already false, we don;t have to  execute the rest of the code.
    if(!Is_Bipartite){
      return;
    }
    visited[v] = true;
    for (int x : graph[v]) {
      if(!visited[x]){
        color[x] = !color[v];

        traverse(graph, x);
      }else{//we should check if the existing color is different
        if(color[x] == color[v]){
          Is_Bipartite = false;
          return;
        }
      }
    }
  }
  public void BFS_Traverse(int [][] graph, int v){
    if(!Is_Bipartite){
      return;//If it's already false, return.
    }
    Queue<Integer> queue = new LinkedList<>();
    visited[v] = true;
    queue.offer(v);
    while(!queue.isEmpty()){
      int x = queue.poll();
      for (int s : graph[x]) {
        //if s is unvisited, we should
        if(!visited[s]){
          //color it with the other color
          color[s] = !color[x];
          visited[s] = true;
          queue.offer(s);
        }else{//We should determine if their colors are the same
          if(color[s] == color[x]){
            Is_Bipartite = false;
            return;
          }
        }
      }
    }
  }
}
