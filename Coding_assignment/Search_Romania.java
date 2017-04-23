/*=============================
NAME : ABHILASH ARIVANAN
UNITY ID : aarivan
STUDENT ID : 200158591
===============================*/

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Search_Romania extends Node_Class {

	static LinkedList<Node> traversed_path = new LinkedList<Node>();
	static LinkedList<Node> visited_nodes = new LinkedList<Node>();
	static Queue<Node> queue = new LinkedList<Node>();
	static boolean goalReached = false;
	@SuppressWarnings("rawtypes")
	static Map map = new HashMap();

	static void depth_first_search(Node current, Node goal) {
		// Depth first search algorithm
		if (goalReached == false) {
			traversed_path.add(current);

			// Add visited nodes to list
			visited_nodes.add(current);
			if (current.cities.equals(goal.cities)) {
				goalReached = true;
				// Print the path traversed once goal is reached
				System.out.println("\n" + "Length of the list:"
						+ traversed_path.size()+"\n");
				System.out.println("The traversed path from " +traversed_path.get(0).cities+ " to " +goal.cities);
				for (Node p : traversed_path) {
					System.out.println(p.cities);
				}
			} else {
				for (Node next : current.neighbouring_cities) {
					if (!visited_nodes.contains(next)) {
						// Continue till destination reached
						depth_first_search(next, goal);
					}
				}
				traversed_path.removeLast();
			}
		}
	}

	@SuppressWarnings("unchecked")
	static void breadth_first_search(Node current, Node goal) {
		// Breadth first search algorithm
		if (!visited_nodes.contains(current)) {
			visited_nodes.add(current);
		}
		if (goalReached == false) {
			for (Node child : current.neighbouring_cities) {
				// Add the not-visited nodes to the list
				if (!visited_nodes.contains(child)) {
					visited_nodes.add(child);
					queue.add(child);
					map.put(child, current);
					if (child.cities.equals(goal.cities)) {
						// Print the path traversed once goal is reached
						goalReached = true;
						traversed_path.add(child);
						traversed_path.add(current);
						while (traversed_path.getLast() != visited_nodes.get(0)) {
							current = (Node) map.get(current);
							traversed_path.add(current);
						}
						System.out.println("\n" + "Length of the list is : "
								+ traversed_path.size() + "\n");
				System.out.println("The traversed path from " + visited_nodes.get(0).cities + " to " +goal.cities);
						while (!traversed_path.isEmpty()) {
							System.out.println(traversed_path.removeLast().cities);
						}
						return;
					}
				}
			}
			breadth_first_search(queue.remove(), goal);
		}
	}

	public static void main(String[] args) throws IOException {

		// Call start_process to read information regarding cities and Neighbors
		Node_Class object = new Node_Class();
		object.start_process();
		Node start = node[0];
		Node destination = node[1];

		for (int i = 0; i < node.length; i++) {
			if (node[i].cities.equals(args[1])) {
				start = node[i];
			}
			if (node[i].cities.equals(args[2])) {
				destination = node[i];
			}
		}
		if (args[0].equals("dfs")) {
			depth_first_search(start, destination);
		} else if (args[0].equals("bfs")) {
			breadth_first_search(start, destination);
		}

		System.out.println("Total number of nodes expanded:"
				+ visited_nodes.size() + "\n");
		System.out.println("\nNodes expanded:\n");
		for (Node n : visited_nodes) {
			System.out.println(n.cities);
		}
	}
}
