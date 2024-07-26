package com.dsa_graph.adjacencygraph;

import java.util.ArrayList;
import java.util.Arrays;

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

	public static void main(String[] args) {
		Graph graph = new Graph(4);
		graph.addNode(0, 0, 2);
		graph.addNode(1, 1, 2);
		graph.addNode(1, 1, 3);
		graph.addNode(2, 2, 0);
		graph.addNode(2, 2, 1);
		graph.addNode(2, 2, 3);
		graph.addNode(3, 3, 1);
		graph.addNode(3, 3, 2);
//		graph.print(graph);

		System.out.println(graph.getNode(2));
		System.out.println(Arrays.toString(graph.getNeigbors(0)));
	}
}