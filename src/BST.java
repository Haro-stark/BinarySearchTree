import java.io.IOException;
import java.util.Scanner;

public class BST {

    private static boolean found = false;

    public static class Node {
        Integer data = null;
        Node right;
        Node left;

    }

    public static Node head;


    public Node insert(Node node, int value) {

        if (node == null) {
            Node nodeObj = new Node();

            nodeObj.data = value;
            nodeObj.left = null;
            nodeObj.right = null;

            return nodeObj;
        }

        if (value < node.data) {
            node.left = insert(node.left, value);
        } else if (value > node.data) {
            node.right = insert(node.right, value);
        }
        return node;
    }

    public static void printBst(Node node) {
        if (node == null) {
            return;
        }
        printBst(node.left);
        if (node.data != null) {
            System.out.print(node.data + "  ");
        }
        printBst(node.right);

    }

    public static Node searchBST(Node node, int value) {
        if (node == null) {
            return node;
        }
        searchBST(node.left, value);
        if (node.data != null) {
            if (node.data == value) {
                System.out.println("Node found: " + node.data);
                return node;
            }
        }
        searchBST(node.right, value);

        return node;
    }

    public static Node searchPredecessor(Node node, int value) {

        if (node == null) {
            return node;
        }
        searchPredecessor(node.left, value);
        if (node.left != null || node.right != null) {
            if (node.left != null && node.left.data == value) {
                System.out.println("Node predecessor found: " + node.data);
                found = true;
                return node;
            }
            if (node.right != null && node.right.data == value) {
                System.out.println("Node predecessor found: " + node.data);
                found = true;
                return node;
            }
        }
        searchPredecessor(node.right, value);

        return node;
    }

    public static Node deleteNode(Node root, int key) {

        Node predecessor = null; //for predecessor
        // Head node
        Node current = root;

        while (current != null && current.data != key) {
            predecessor = current;
            // if the given key is less than the current node, go to the left subtree;
            // otherwise, go to the right subtree
            if (key < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            System.out.println("The given data is out of bound");
            return root;
        }


        if (current.left == null && current.right == null) {
            // Case 1: Leaf node
            // if the node to be deleted is not a root node, then set its
            // predecessor left/right child to null
            if (current != root) {
                if (predecessor.left == current) {
                    predecessor.left = null;
                } else {
                    predecessor.right = null;
                }
            }
            // if the tree has only a root node, set it to null
            else {
                root = null;
            }
        }

        // Case 2: node to be deleted has two children
        else if (current.left != null && current.right != null) {
            // find its inorder successor node
            Node successor = getMinimumKey(current.right);

            // store successor value
            int val = successor.data;

            // recursively delete the successor. Note that the successor
            // will have at most one child (right child)
            deleteNode(root, successor.data);

            // copy value of the successor to the current node
            current.data = val;
        }

        // Case 3: node to be deleted has only one child
        else {
            // choose a child node
            Node child = (current.left != null) ? current.left : current.right;

            // if the node to be deleted is not a root node, set its predecessor
            // to its child
            if (current != root) {
                if (current == predecessor.left) {
                    predecessor.left = child;
                } else {
                    predecessor.right = child;
                }
            }

            // if the node to be deleted is a root node, then set the root to the child
            else {
                root = child;
            }
        }
        return root;
    }

    public static Node getMinimumKey(Node curr) {
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Node node = new Node();
        BST bst = new BST();
        node = null;
        ValidationClass validationClass = new ValidationClass();

        System.out.println("how many numbers you want to insert?");
        int num = validationClass.askIntInput();
        int insertValue = 0;
        for (int i = 0; i < num; i++) {
            System.out.print("Enter a num to insert in BST: ");
            insertValue = validationClass.askIntInput();
            node = bst.insert(node, insertValue);
            if (i == 0) head = node;
        }

        System.out.println("BST:");
        printBst(bst.head);

        System.out.println("\n");
        System.out.println("Enter the number to delete: ");
        int deleteNum = validationClass.askIntInput();
        deleteNode(head, deleteNum);

        System.out.println("BST:");
        printBst(bst.head);

        System.out.println("\n");


        System.out.println("Enter the number to search: ");
        int searchNum = validationClass.askIntInput();
        searchBST(head, searchNum);
        if(!found) System.out.println("Not found");

    }


}
