package com.dsa_graph.cycle_detection;

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

	public static boolean isCycle(Graph g, boolean[] rec, boolean[] isVis, int curr) {
		rec[curr] = true;
		isVis[curr] = true;
		ArrayList<Edge> data[] = g.graph;
		for (int i = 0; i < data[curr].size(); i++) {
			Edge edge = data[curr].get(i);
			if (isVis[edge.destination]) {
				return true;
			} else {
				if (!isVis[edge.destination] && isCycle(g, rec, isVis, edge.destination)) {
					return true;
				}
			}
		}
		rec[curr] = false;
		return false;

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

		boolean[] rec = new boolean[graph.getSize(graph)];
		boolean[] isVis = new boolean[graph.getSize(graph)];

		for (int i = 0; i < graph.getSize(graph); i++) {
			if (!isVis[i]) {
				boolean cycle = isCycle(graph, isVis, rec, 0);
				if (cycle) {
					System.out.println(cycle);
					break;
				}
			}
		}

	}
}
