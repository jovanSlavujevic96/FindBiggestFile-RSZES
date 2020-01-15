package com.company;

public class DirectoryTree {

    private Node root;
    private double max = 0;
    private Node maxNode = null;

    public DirectoryTree(String filepath) throws InvalidPathException {
        this.root = new Node(null, filepath);
    }

    public void populateTree(Node node) throws InvalidPathException {
        node = new Node(node.getParent(), node.getFilepath());
    }

    Node findMaxSizeFile(Node node){
        for (Node child : node.getChildren()){
            if (child.representsFile() && child.getSize() > this.max) {
                this.max = child.getSize();
                maxNode = child;
            }else if (!child.representsFile()){
                findMaxSizeFile(child);
            }
        }
        return maxNode;
    }

    Node findMaxSizeDirectory(Node node){
        double max = 0;
        Node maxNode = null;
        for (Node child : node.getChildren()){
            if (!child.representsFile() && child.getSize() > max){
                max = child.getSize();
                maxNode = child;
            }
        }
        return maxNode;
    }

    public Node getRoot() {
        return root;
    }
}
