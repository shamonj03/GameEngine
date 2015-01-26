package com.joe.engine.model;

public class Node {

	private Node parent;
	private int h;
	private int g;
	private int f;
	private Location location;

	/**
	 * Creates a new node.
	 * 
	 * @param parent
	 * 		Node current node is connected to.
	 * 
	 * @param location
	 * 		Location where node is.
	 */
	public Node(Node parent, Location location) {
		this.parent = parent;
		this.location = location;
	}
	
	/**
	 * Sets the parent node.
	 * 
	 * @param parent
	 * 		Node current node is connected to.
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * Distance to sounding tiles.
	 * 
	 * @param g
	 * 		This will usually be 10 since
	 * 		diagonals aren't allowed.
	 */
	public void setG(int g) {
		this.g = g;
	}

	/**
	 * Cost to move to next node.
	 * 
	 * @param f
	 * 		G + H.
	 */
	public void setF(int f) {
		this.f = f;
	}

	/**
	 * Heuristic cost.
	 * 
	 * @param h
	 * 		H = Manhattan value.
	 */
	public void setH(int h) {
		this.h = h;
	}

	/**
	 * @return parent node current is linked to.
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * @return location of node.
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return Move cost.
	 */
	public int getG() {
		return g;
	}

	/**
	 * return heuristic.
	 */
	public int getH() {
		return h;
	}

	/**
	 * @return Next step cost.
	 */
	public int getF() {
		return f;
	}

	/**
	 * Checks if another node equals current node.
	 * 
	 * @param n
	 * 		Node to check against.
	 * 
	 * @return true/false
	 */
	public boolean matches(Node n) {
		return location.getTileX() == n.getLocation().getTileX()
				&& location.getTileY() == n.getLocation().getTileY();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Node) {
			Node n = (Node) obj;
			
			return n.matches(this);
		}
		return super.equals(obj);
	}
}
