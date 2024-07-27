package com.dsa_graph.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	private int v;
	private ArrayList<Edge> graph[];

	static class Edge {
		int source;
		int destination;

		public Edge(int source, int destination) {
			this.source = source;
			this.destination = destination;
		}

		@Override
		public String toString() {
			return "[" + this.source + "," + this.destination + "]";
		}
	}

	public Graph(int v) {
		graph = new ArrayList[v];
		for (int i = 0; i < v; i++) {
			graph[i] = new ArrayList<Edge>();
		}
	}

	public Graph() {
		graph = new ArrayList[4];
		for (int i = 0; i < 4; i++) {
			graph[i] = new ArrayList<Edge>();
		}
	}

	public void addNode(int pos, int source, int destination) {
		if (pos > graph.length - 1)
			return;
		graph[pos].add(new Edge(source, destination));
	}

	public void print(Graph graph) {
		for (ArrayList<Edge> l : this.graph) {
			System.out.println(l);
		}
	}

	public int getSize(Graph graph) {
		return graph.graph.length;
	}

	public ArrayList<Edge> getNode(int node) {
		return graph[node];
	}

	public int[] getNeigbors(int node) {
		ArrayList<Edge> arrayList = graph[node];
		int[] neigbors = new int[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++) {
			neigbors[i] = arrayList.get(i).destination;
		}
		return neigbors;
	}

	public void bfs(Graph g, boolean isVisited[], int start) {
		ArrayList<Edge>[] data = g.graph;
		Queue<Integer> queue = new LinkedList<>();
//		queue.add(data[0].get(0).source);
		queue.add(start);

		while (!queue.isEmpty()) {
			int curr = queue.remove();
			if (!isVisited[curr]) {
				System.out.println(curr);
				isVisited[curr] = true;

				for (int i = 0; i < data[curr].size(); i++) {
					Edge edge = data[curr].get(i);
					queue.add(edge.destination);
				}
			}
		}

	}

	public void dfs(Graph g, int curr, boolean[] isValid) {
		ArrayList<Edge>[] graph2 = g.graph;
		System.out.println(curr);
		isValid[curr] = true;

		for (int i = 0; i < graph2[curr].size(); i++) {
			Edge e = graph2[curr].get(i);
			if (!isValid[e.destination])
				dfs(g, e.destination, isValid);
		}
	}

	public static void printAllPath(Graph g, int curr, boolean[] isVisited, String path, int target) {
		if (curr == target) {
			System.out.println(path);
			return;
		}

		for (int i = 0; i < g.graph[curr].size(); i++) {
			Edge edge = g.graph[curr].get(i);
			if (!isVisited[edge.destination]) {
				isVisited[curr] = true;
				printAllPath(g, edge.destination, isVisited, path + edge.destination, target);
				isVisited[curr] = false;
			}
		}
	}

	public static void main(String[] args) {
		Graph graph = new Graph(7);
		graph.addNode(0, 0, 1);
		graph.addNode(0, 0, 2);

		graph.addNode(1, 1, 0);
		graph.addNode(1, 1, 3);

		graph.addNode(2, 2, 0);
		graph.addNode(2, 2, 4);

		graph.addNode(3, 3, 1);
		graph.addNode(3, 3, 4);
		graph.addNode(3, 3, 5);

		graph.addNode(4, 4, 2);
		graph.addNode(4, 4, 3);
		graph.addNode(4, 4, 5);

		graph.addNode(5, 5, 3);
		graph.addNode(5, 5, 4);
		graph.addNode(5, 5, 6);

		graph.addNode(6, 6, 5);
		// so that we can print disconnect graph
		boolean[] isVisited = new boolean[graph.getSize(graph)];

//		for (int i = 0; i < isVisited.length; i++) {
//			if (!isVisited[i]) {
//				graph.dfs(graph, i, isVisited);
//			}
//		}

		printAllPath(graph, 0, isVisited, "0", 5);
	}
}
