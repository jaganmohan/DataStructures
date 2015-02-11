package jagan.trees;

/**
 * 
 * @author Artist
 * Reference - http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * Reference - http://www.geeksforgeeks.org/avl-tree-set-2-deletion/
 */
public class AdelsonVelskyLandis {

	private NodeHeight head;
	
	public void setHead(NodeHeight node){
		head = node;
	}
	
	public NodeHeight getHead(){
		return head;
	}
	
	public int max(int left,int right){
		return left >= right ? left : right;
	}
	
	/**
	 * Calculates height of a node
	 * @param node
	 * @return
	 */
	public int calculateHeight(NodeHeight node){
		int leftHeight =0, rightHeight = 0;
		if(node.getLeft() != null)
			leftHeight = node.getLeft().getHeight()+1;
		if(node.getRight() != null)
			rightHeight = node.getRight().getHeight()+1;
		return max(leftHeight, rightHeight);
	}
	
	/**
	 * Calculates balance factor of a node, height of left subtree minus height of right subtree 
	 * @param node
	 * @return
	 */
	public int getBalance(NodeHeight node){
		int leftSubtreeHeight = node.getLeft() != null ? node.getLeft().getHeight()+1 : 0;
		int rightSubTreeHeight = node.getRight() != null ? node.getRight().getHeight()+1 : 0;
		return (leftSubtreeHeight -	rightSubTreeHeight);
	}
	
	/**
	 * For rotating left (RR rotation) when the balance factor of node is -2
	 * @param node
	 */
	public NodeHeight rotateLeft(NodeHeight node){
		NodeHeight rightChild = node.getRight();
		
		node.setRight(rightChild.getLeft());
		node.setHeight(calculateHeight(node));
		
		rightChild.setLeft(node);
		rightChild.setHeight(calculateHeight(rightChild));
		return rightChild;
		
	}
	
	/**
	 * For rotating right (LL rotation) when the balance factor of node is 2
	 * @param node
	 */
	public NodeHeight rotateRight(NodeHeight node){
		NodeHeight leftChild = node.getLeft();
		
		node.setLeft(leftChild.getRight());
		node.setHeight(calculateHeight(node));
		
		leftChild.setRight(node);
		leftChild.setHeight(calculateHeight(leftChild));
		return leftChild;
	}
	
	/**
	 * AVL tree is a specialization of Binary Search Tree. Insertions are done like BST.
	 * Inserts an element into the tree and then realigns the tree using rotations so that the balance factor of every node is either of -1, 0 or 1
	 * @param key The element to be inserted
	 * @param parent A node in the ancestral hierarchy of the newly created node(key) 
	 * @return
	 */
	public NodeHeight insert(int key, NodeHeight parent){
		
		/*if(head == null){
			head = new NodeHeight(key);;
			return head;
		}*/
		if(parent == null)
			return new NodeHeight(key);
		
		if(key < parent.getKey())	// TODO duplicate element insertions 
			parent.setLeft(insert(key, parent.getLeft()));
		else
			parent.setRight(insert(key, parent.getRight()));
		
		/*if(parent.getLeft() == null || parent.getRight() == null){
			if(key < parent.getKey() && parent.getLeft() == null)
				parent.setLeft(new NodeHeight(key));
			else if(key > parent.getKey() && parent.getRight() == null)
				parent.setRight(new NodeHeight(key));
		}*/
		
		parent.setHeight(calculateHeight(parent));
		
		if(getBalance(parent) == 2 && getBalance(parent.getLeft()) == 1)
			parent = rotateRight(parent);
		else if(getBalance(parent) == -2 && getBalance(parent.getRight()) == -1)
			parent = rotateLeft(parent);
		else if(getBalance(parent) == 2 && getBalance(parent.getLeft()) == -1){
			parent.setLeft(rotateLeft(parent.getLeft()));
			parent = rotateRight(parent);
		}else if(getBalance(parent) == -2 && getBalance(parent.getRight()) == 1){
			parent.setRight(rotateRight(parent.getRight()));
			parent = rotateLeft(parent);
		}
		return parent;
	}
	
	public NodeHeight delete(int key, NodeHeight parent){
		
		NodeHeight inorderSucc;
		int inorderSuccKey;
		
		if(parent == null)
			return null;
		
		if(key < parent.getKey())
			parent.setLeft(delete(key, parent.getLeft()));
		else if(key > parent.getKey())
			parent.setRight(delete(key, parent.getRight()));
		else{		// if(key == parent.getKey()){
			
			// TODO check parent.getRight not null
			inorderSucc = parent.getRight();
			while(inorderSucc != null && inorderSucc.getLeft() != null)
				inorderSucc = inorderSucc.getLeft();
			
			if(inorderSucc != null){
				inorderSuccKey = inorderSucc.getKey();
				delete(inorderSuccKey, parent);
				parent.setKey(inorderSuccKey);
			}else{
				if(parent.getLeft() != null)
					parent = parent.getLeft();
				else{
					parent = null;
					return parent;
				}
			}
			//delete(inorderSucc.getKey(), parent);
			
		}
		
		parent.setHeight(calculateHeight(parent));
		
		if(getBalance(parent) == 2 && getBalance(parent.getLeft()) == 1)
			parent = rotateRight(parent);
		else if(getBalance(parent) == -2 && getBalance(parent.getRight()) == -1)
			parent = rotateLeft(parent);
		else if(getBalance(parent) == 2 && getBalance(parent.getLeft()) == -1){
			parent.setLeft(rotateLeft(parent.getLeft()));
			parent = rotateRight(parent);
		}else if(getBalance(parent) == -2 && getBalance(parent.getRight()) == 1){
			parent.setRight(rotateRight(parent.getRight()));
			parent = rotateLeft(parent);
		}
			
		return parent;
	}
	
	public void displayInorder(NodeHeight root){
		if(head == null){
			System.out.println("Tree is empty");
			return;
		}
		if(root == null)
			return;
		displayInorder(root.getLeft());
		System.out.print(root.getKey()+"("+getBalance(root)+")->");
		displayInorder(root.getRight());
	}
}
