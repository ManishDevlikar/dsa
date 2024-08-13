package com.dsa_graph.topological_sort;

import java.util.ArrayList;
import java.util.Stack;

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

	public static void topologicalSort(Graph g, boolean[] isVis, int curr, Stack<Integer> s) {
		isVis[curr] = true;
		ArrayList<Edge>[] graph = g.graph;
		for (int i = 0; i < graph[curr].size(); i++) {
			Edge edge = graph[curr].get(i);
			if (!isVis[edge.destination]) {
				topologicalSort(g, isVis, edge.destination, s);
			}
		}
		s.push(curr);
	}

	public static void printTopo(Graph g, int v) {
		ArrayList<Edge>[] graph = g.graph;
		Stack<Integer> s = new Stack<>();
		boolean[] isVis = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (!isVis[i]) {
				topologicalSort(g, isVis, i, s);
			}
		}
		while (!s.isEmpty()) {
			System.out.println(s.pop());
		}
	}

	public static void main(String[] args) {
		Graph graph = new Graph(6);

		graph.addNode(2, 2, 3);

		graph.addNode(3, 3, 1);

		graph.addNode(4, 4, 0);
		graph.addNode(4, 4, 1);

		graph.addNode(5, 5, 0);
		graph.addNode(5, 5, 2);

		boolean[] isVis = new boolean[graph.getSize(graph)];
		Stack<Integer> s = new Stack<>();

//		topologicalSort(graph, isVis, 0, s);
		printTopo(graph, 6);

	}
}
