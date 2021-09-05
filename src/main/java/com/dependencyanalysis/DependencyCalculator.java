package com.dependencyanalysis;

import java.util.*;

/**
 * @author rohan
 * 
 *         The class has the logic to deduce the transitive dependencies between
 *         different characters.
 */
public class DependencyCalculator {

	private Map<String, Node> nodesMap = new HashMap<String, Node>();

	public void printTransitiveDependency() {
		for (String name : nodesMap.keySet()) {
			System.out.println(nodesMap.get(name).toString());
		}
	}

	/**
	 * Get a node from the Map if it already exists, else create it and put it in
	 * the map.
	 */
	public Node getOrCreateNode(String nodeName) {
		Node currentNode;
		if (!nodesMap.containsKey(nodeName)) {
			currentNode = new Node(nodeName);
			nodesMap.put(nodeName, currentNode);
		} else {
			currentNode = nodesMap.get(nodeName);
		}
		return currentNode;
	}

	public boolean checkDependency(String inputString) throws CyclicDependencyException {
		String[] elements = inputString.split(" ");

		String nodeName = elements[0];

		Node node = getOrCreateNode(nodeName);

		for (int i = 1; i < elements.length; i++) {
			if (!node.addChild(getOrCreateNode(elements[i]))) {
				throw new CyclicDependencyException();
			}
		}
		return true;
	}

	public static void main(String args[]) {

		try {
			DependencyCalculator depCalculator = new DependencyCalculator();
			depCalculator.checkDependency("A B C");
			depCalculator.checkDependency("B C E");
			depCalculator.checkDependency("C G");
			depCalculator.checkDependency("D A F");
			depCalculator.checkDependency("E F");
			depCalculator.checkDependency("F H");
			depCalculator.printTransitiveDependency();
		} catch (CyclicDependencyException e) {
			e.printStackTrace();
		}
	}
}