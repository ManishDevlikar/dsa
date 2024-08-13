package com.dsa_graph.cycle_detection_undir;

import java.util.ArrayList;

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

	public static boolean isCycle(Graph g, boolean[] isVis, int curr, int par) {
		ArrayList<Edge>[] graph = g.graph;
		isVis[curr] = true;
		for (int i = 0; i < graph[curr].size(); i++) {
			Edge edge = graph[curr].get(i);
			if (isVis[edge.destination] && edge.destination != par)
				return true;
			else if (!isVis[edge.destination])
				if (isCycle(g, isVis, edge.destination, curr))
					return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Graph graph = new Graph(7);
		graph.addNode(0, 0, 1);

		graph.addNode(1, 1, 0);
		graph.addNode(1, 1, 2);

		graph.addNode(2, 2, 1);

		graph.addNode(3, 3, 4);
		graph.addNode(3, 3, 1);

		graph.addNode(4, 4, 3);

		boolean[] isVis = new boolean[graph.getSize(graph)];

		boolean cycle = isCycle(graph, isVis, 0, 0);

		System.out.println(cycle);

	}
}
