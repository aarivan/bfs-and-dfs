/*=============================
NAME : ABHILASH ARIVANAN
UNITY ID : aarivan
STUDENT ID : 200158591
===============================*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*Class to load information of vertices*/
class Node {
	String cities;
	LinkedList<Node> neighbouring_cities;
}

public class Node_Class {

	static Node[] node = new Node[20];

	public static void start_process() throws IOException {

		List<String> cities_list = new ArrayList<String>();
		List<String> adjacency_list = new ArrayList<String>();

		for (int i = 0; i < 20; i++) {
			node[i] = new Node();
			node[i].neighbouring_cities = new LinkedList();
		}

		/* Add the cities to a list */
		cities_list = read_cities(node);
		for (int i = 0; i < cities_list.size(); i++) {
			node[i].cities = cities_list.get(i);
		}

		/* Add adjacent nodes of each city to a list */
		adjacency_list = read_neighbouring_cities(node);
		node = process_adjacent_list(adjacency_list, node);
	}

	private static Node[] process_adjacent_list(List<String> adjacency_list,
			Node[] node) {
		/*
		 * Processes the neighboring vertices information and associates it with
		 * the corresponding nodes
		 */
		for (int i = 0; i < adjacency_list.size(); i++) {
			String[] lines = adjacency_list.get(i).split(",");
			for (int j = 0; j < lines.length; j++) {
				node[i].neighbouring_cities
						.add(node[Integer.parseInt(lines[j])]);
			}
		}
		return node;
	}

	private static List<String> read_neighbouring_cities(Node[] node2)
			throws IOException {
		/*
		 * Read file that contains information regarding neighboring
		 * nodes(cities) of each city
		 */
		File file = new File("adjacent.txt");
		List<String> lines_list = new ArrayList<String>();
		String line = null;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while ((line = reader.readLine()) != null) {
			lines_list.add(line);
		}
		reader.close();
		return lines_list;
	}

	private static List<String> read_cities(Node[] node) throws IOException {

		/* Read file that contains the cities in Romania */
		File file = new File("cities.txt");
		List<String> lines_list = new ArrayList<String>();
		String line = null;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while ((line = reader.readLine()) != null) {
			lines_list.add(line);
		}
		reader.close();
		return lines_list;
	}
}
