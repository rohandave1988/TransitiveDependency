package com.dependencyanalysis;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author rohan
 * 
 * The character or token and the dependencies are stored in the node
 * which is a graph data structure, which signifies the character or
 * token as name (String) and Hashset which contains the dependencies of
 * the current node.
 * 
 */
class Node implements Comparable<Node> {

	Set<Node> dependants = new HashSet<Node>();

	String name;

	public Node(String name) {
		this.name = name;
	}

	public boolean addChild(Node n) {
		if (n.allDescendants().contains(this)) {
			return false;
		} else {
			this.dependants.add(n);
			return true;
		}
	}

	public Set<Node> allDescendants() {
		Set<Node> descendants = new TreeSet<Node>();

		for (Node c : dependants) {
			descendants.add(c);
			descendants.addAll(c.allDescendants());
		}
		return descendants;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Node node = (Node) o;
		return name != null ? name.equals(node.name) : node.name == null;

	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

	@Override
	public String toString() {
		return name + " " + allDescendants().stream().map(x -> x.name).collect(Collectors.joining(" "));
	}

	@Override
	public int compareTo(Node o) {
		return name.compareTo(o.name);
	}
}
