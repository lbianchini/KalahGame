package com.bianchini.leandro.kalahgame.backend.controller;

/**
 * Class responsible to represent a node inside a CircularLinkedNodeList.
 * 
 * @author lbianchini
 *
 * @param <T> Generic type of a value of Node
 */
public class Node<T> {
	
	private T value;
	private Node<T> nextNode;
	
	/**
	 * Contructor
	 * 
	 * @param value
	 */
	public Node(T value) {
		this.value = value;
	}
	
	/**
	 * Get the value of the node.
	 * 
	 * @return T
	 */
	public T getValue() {
		return value;
	}
	
	/**
	 * Get the next node object reference.
	 * 
	 * @return Node
	 */
	public Node<T> getNextNode() {
		return nextNode;
	}
	
	/**
	 * Set the reference to the next node object on current node.
	 * 
	 * @param nextNode
	 */
	public void setNextNode(Node<T> nextNode) {
		this.nextNode = nextNode;
	}

}
