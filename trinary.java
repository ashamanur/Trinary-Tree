import java.util.Scanner;
/*
 * @author: Anusha Shamanur
 * Implements insert and delete in a tri-nary tree.
 */

class trinary {

	class Node {
		Node left;
		Node right;
		Node middle;
		int value;

		public Node(int value) {
			this.value = value;
		}
	}

	Node root;

	public void insert(int value) {
		if (this.root == null) {
			this.root = new Node(value);
			return;
		}
		this.in_adjust(this.root, value);
	}

	protected Node in_adjust(Node node, int value) {
		if (node.value > value) {
			if (node.left == null) {
				node.left = new Node(value);
			} else {
				this.in_adjust(node.left, value);
			}
		} else if (node.value == value) {
			if (node.middle == null) {
				node.middle = new Node(value);
			} else {
				this.in_adjust(node.middle, value);
			}
		} else {
			if (node.right == null) {
				node.right = new Node(value);
			} else {
				this.in_adjust(node.right, value);
			}
		}
		return node;
	}
	
	public void delete(int value) {
		this.del_adjust(this.root, value);
	}
	
	protected Node del_adjust(Node node, int value) {
		if (value < node.value) {
			node.left = del_adjust(node.left, value);
		} else if (value > node.value) {
			node.right = del_adjust(node.right, value);
		} else {
			if (node.middle != null) {
				node.middle = del_adjust(node.middle, value);
			} else if (node.right != null) {
				int min = min(node.right).value;
				node.value = min;
				node.right = del_adjust(node.right, min);
			} else {
				node = node.left;
			}
		}

		return node;
	}

	protected Node min(Node node) {
		if (node != null) {
			while (node.left != null) {
				return min(node.left);
			}
		}

		return node;
	}

	public void printNode(Node node) {
		if (node == null) {
			return;
		}
		System.out.println(node.value);
		this.printNode(node.left);
		this.printNode(node.middle);
		this.printNode(node.right);
	} 
	
	public static void main(String[] args) {
		trinary t = new trinary();

        Scanner scan = new Scanner(System.in);
         
        System.out.println("Implementing Tri-nary Tree\n");          
        char ch;
        /*  Perform tree operations  */
        do    
        {
            System.out.println("\nTri-nary Tree operations\n");
            System.out.println("1. Insert ");
            System.out.println("2. Delete");
            System.out.println("3. Print");
 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter the element to insert");
                t.insert( scan.nextInt() );                     
                break;                          
            case 2 : 
                System.out.println("Enter the element to delete");
                try
                {
                    t.delete( scan.nextInt() ); 
                }                    
                catch (Exception e)
                {
                    System.out.println(e.getMessage()+" not found ");
                }
                break;                         
            case 3 : 
                System.out.println("Printing tree: ");
                t.printNode(t.root);
                break;                                          
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            }
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');               
 	}
}