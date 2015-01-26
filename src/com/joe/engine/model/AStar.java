package com.joe.engine.model;

import java.util.ArrayList;
import java.util.Collections;

import com.joe.engine.util.EngineConstants;

public abstract class AStar {

	private ArrayList<Node> closedList = new ArrayList<>();
	private ArrayList<Node> openList = new ArrayList<>();

	/**
	 * Add logic for collision detection here.
	 * 
	 * @param location
	 * 		Current location.
	 * 
	 * @return true if collision has been made.
	 */
	public abstract boolean checkCollisions(Location location);

	/**
	 * Calculate the shortest path using the AStar formula.
	 * 
	 * @param start
	 *            Location to start the path finding from.
	 * 
	 * @param desitination
	 *            Location for the path finding to try and reach.
	 */
	public ArrayList<Node> generatePath(Location start, Location destination) {
		Node start_node = new Node(null, start);
		Node destination_node = new Node(null, destination);
		Node current_node = new Node(null, start);

		openList.clear();
		closedList.clear();

		int startH = getH(start, destination);
		start_node.setG(0);
		start_node.setH(startH);
		start_node.setF(start_node.getG() + startH);

		openList.add(start_node);

		while (!openList.isEmpty()) {
			if (current_node.matches(destination_node)) {
				break;
			}
			current_node = openList.get(0);

			openList.remove(current_node);
			closedList.add(current_node);

			for (Direction direction : EngineConstants.IMMEDIATE_DIRECTIONS) {
				Location location = new Location(current_node.getLocation());
				location.offsetX(direction.getXOffset() << 4);
				location.offsetY(direction.getYOffset() << 4);

				Node adjacent_node = new Node(current_node, location);

				if (closedList.contains(adjacent_node)) {
					continue;
				}

				if (checkCollisions(location)) {
					continue;
				}

				int g = getG(current_node.getLocation(),
						adjacent_node.getLocation());

				int next_step_g = (current_node.getG() + g);
				int h = getH(current_node.getLocation(),
						adjacent_node.getLocation());

				if (!openList.contains(adjacent_node)
						|| next_step_g < adjacent_node.getG()) {

					adjacent_node.setParent(current_node);
					adjacent_node.setG(next_step_g);
					adjacent_node.setH(h);
					adjacent_node.setF(h + next_step_g);
					if (!openList.contains(adjacent_node)) {
						openList.add(adjacent_node);
					}

				}
			}
		}
		return constructPath(current_node);
	}

	/**
	 * Constructs the path from the last node. Continues adding nodes to path
	 * until the current node is null. Reverse the list since we started from
	 * the end to get correct path.
	 * 
	 * @param node
	 *            Node to start constructing the path from.
	 */
	private ArrayList<Node> constructPath(Node node) {
		ArrayList<Node> path = new ArrayList<>();

		while (node.getParent() != null) {
			path.add(node);
			node = node.getParent();
		}
		path.add(node);

		Collections.reverse(path);
		return path;
	}

	/**
	 * Manhattan formula. We are not moving diagonally so this is the most
	 * efficient way.
	 * 
	 * @param start
	 *            Location to start from.
	 * 
	 * @param goal
	 *            Location we're trying to reach.
	 */
	private int getH(Location start, Location goal) {
		int dx = (int) Math.abs(goal.getTileX() - start.getTileX());
		int dy = (int) Math.abs(goal.getTileY() - start.getTileY());
		return (dx + dy);
	}

	/**
	 * We aren't using diagonal nodes so it will always return 10.
	 * 
	 * @param start
	 *            Location to start from.
	 * 
	 * @param next
	 *            Location we're trying to reach.
	 */
	private int getG(Location start, Location next) {
		// if (start.getTileX() == next.getTileX() || start.getTileY() ==
		// next.getTileY()) {
		return 10;
		// }
		// return 14;
	}

}
