package jagan.trees;

public class BinarySearchTree {

	private Node head;
	
	public Node getHead(){
		return head;
	}
	
	public Node search(int key){
		
		Node curr = head;
		while(curr != null && key != curr.getKey()){
			if(key < curr.getKey())
				curr = curr.getLeft();
			else
				curr = curr.getRight();
		}
		return curr;	// if curr == null then element not found 
		
	}
	
	public void insert(int key){
		
		Node newNode = new Node(key);
		if(head == null){
			head = newNode;
		}else{
			Node curr = head,node = curr;
			while(curr != null){
				node = curr;
				if(key == curr.getKey()){
					System.out.println(key+" is already present in the tree");
					return;
				}
				else if(key < curr.getKey())
					curr = curr.getLeft();
				else
					curr = curr.getRight();
			}
			if(key < node.getKey())
				node.setLeft(newNode);
			else
				node.setRight(newNode);
		}

	}
	
	public void delete(int key){
		
		Node curr = head,node = curr;
		
		while(curr != null && curr.getKey() != key){
			node = curr;
			if(key < curr.getKey())
				curr = curr.getLeft();
			else
				curr = curr.getRight();
		}
		//curr is the node to be deleted and node is the parent element of node
		if(curr == null){
			System.out.println(key+" is not present in the binary search tree");
			return;
		}
		
		//case I -  curr node not having any children, just delete the element
		if(curr.getLeft() == null && curr.getRight() == null){
			if(curr == head){
				head = null;
				return;
			}
			else if(node.getKey() > curr.getKey()){
				node.setLeft(null);
			}else{
				node.setRight(null);
			}				
		}
		
		//case II - curr node having one children, replace the element to be deleted with the child element
		else if(curr.getLeft() != null && curr.getRight() == null){
			if(curr == head){
				head = curr.getLeft();
				return;
			}
			else if(node.getKey() > curr.getKey())
				node.setLeft(curr.getLeft());
			else
				node.setRight(curr.getRight());
		}else if(curr.getLeft() == null && curr.getRight() != null){
			if(curr == head){
				head = curr.getRight();
				return;
			}
			else if(node.getKey() > curr.getKey())
				node.setLeft(curr.getRight());
			else
				node.setRight(curr.getRight());
		}
		
		// case III - curr node has two children, replace the element with in-order successor element
		else{
			Node delete = curr, temp = delete;			
			curr = curr.getRight();
			while(curr.getLeft() != null ){
				temp = curr;
				curr = curr.getLeft();
			}
			
			//removes the link to the element with which deleted element is going to be replaced with
			if(temp == delete)
				delete.setRight(null);	// if in-order successor is the right element of the to be deleted element
			else if(curr.getRight() != null)
				temp.setLeft(curr.getRight());	//if in-order successor has a right child present
			else
				temp.setLeft(null);		//if in-order successor has no children, just breaks the link
			// Replacing the to be deleted element with in-order successor
			curr.setLeft(delete.getLeft());
			curr.setRight(delete.getRight());
			if(delete == head){
				head = curr;
			}
			else if(node.getKey() > curr.getKey())
				node.setLeft(curr);
			else
				node.setRight(curr);
		}
		
	}
		
	public void displayNode(int key){
		Node node = search(key);
		System.out.println("root "+head.getKey());
		System.out.println("key " + node.getKey());
		System.out.println("left child "+ (node.getLeft() == null?0:node.getLeft().getKey()));
		System.out.println("right child "+ (node.getRight() == null?0:node.getRight().getKey()));
	}
	
	public void displayInorder(Node node){
		if(node == null)
			return;
		displayInorder(node.getLeft());
		System.out.print(" "+node.getKey());
		displayInorder(node.getRight());
	}
}
