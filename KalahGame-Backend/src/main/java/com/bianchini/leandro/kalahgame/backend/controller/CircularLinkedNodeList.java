package com.bianchini.leandro.kalahgame.backend.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible to move forward through the nodes 
 * on a Linked Circular list.
 * 
 * @author lbianchini
 *
 * @param <T> Generic type of a value of Node
 */
public class CircularLinkedNodeList<T> {
	
	private Node<T> firstNode;
	private Node<T> lastNode;
	private int size;
	
	/**
	 * Contructor
	 */
	public CircularLinkedNodeList() {
		this.firstNode = null;
		this.lastNode = null;
		this.size = 0;
	}
	
	/**
	 * Method responsible to add a node into the linked list
	 * keeping circular. 
	 * @param node
	 */
	public void addNode(Node<T> node) {
		//Check if it is the first node of list
		if (this.firstNode == null) {
			this.firstNode = node;
			node.setNextNode(this.firstNode);
			this.lastNode = this.firstNode;
		} else {
			node.setNextNode(this.firstNode);
			this.lastNode.setNextNode(node);
			this.lastNode = node;
		}
		this.size++;
	}
	
	/**
	 * Get the first node of the list.
	 * 
	 * @return Node
	 */
	public Node<T> getFirstNode() {
		return this.firstNode;
	}
	
	/**
	 * Get the value of the first node of the list.
	 * 
	 * @return T
	 */
	public T getFirstNodeValue() {
		return this.firstNode.getValue();
	}
	
	/**
	 * Get the last node of the list.
	 * 
	 * @return Node
	 */
	public Node<T> getLastNode() {
		return this.lastNode;
	}
	
	/**
	 * Get the value of the last node of the list.
	 * 
	 * @return T
	 */
	public T getLastNodeValue() {
		return this.lastNode.getValue();
	}
	
	/**
	 * Get the node of the index informed.
	 * 
	 * @param index
	 * @return Node
	 */
	public Node<T> getNodeByIndex(int index) {
		Node<T> indexNode = this.firstNode;
		for (int i = 1; i <= size; i++) {
			if (i == index) {
				return indexNode;
			}
			indexNode = indexNode.getNextNode();
		}
		return null;
	}

	/**
	 * Get the value's node of the index informed.
	 * 
	 * @param index
	 * @return Node
	 */
	public T getNodeValueByIndex(int index) {
		Node<T> indexNode = this.firstNode;
		for (int i = 1; i <= size; i++) {
			if (i == index) {
				return indexNode.getValue();
			}
			indexNode = indexNode.getNextNode();
		}
		return null;
	}
	
	/**
	 * Get the value's list of all nodes.
	 * 
	 * @return List
	 */
	public List<T> getListNodeValues() {
		List<T> returnList = new ArrayList<>();
		returnList.add(this.firstNode.getValue());
		Node<T> indexNode = this.firstNode.getNextNode();
		while (indexNode != this.firstNode) {
			returnList.add(indexNode.getValue());
			indexNode = indexNode.getNextNode();
		}
		return returnList;
	}
	
	/**
	 * Get the list of all nodes.
	 * 
	 * @return List
	 */
	public List<Node<T>> getListNode() {
		List<Node<T>> returnList = new ArrayList<>();
		returnList.add(this.firstNode);
		Node<T> indexNode = this.firstNode.getNextNode();
		while (indexNode != this.firstNode) {
			returnList.add(indexNode);
			indexNode = indexNode.getNextNode();
		}
		return returnList;		
	}

}
