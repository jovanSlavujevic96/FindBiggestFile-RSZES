package com.company;

import java.io.File;
import java.util.Arrays;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Node {

    private String filename;
    private String filepath;
    private double size;
    private Node[] children;
    private Node parent;

    public Node(Node parent, String filepath) throws InvalidPathException {
        if (!new File(filepath).exists()){
            throw new InvalidPathException();
        }
        File node = new File(filepath);
        this.filename = node.getName();
        this.filepath = filepath;
        this.parent = parent;
        this.size = this.calculateSize();
        this.createChildren();
    }

    public long calculateSize (){
        File node = new File(this.filepath);
        if (node.isDirectory()){
            return this.getFolderSize(node);
        }else {
            return node.length();
        }
    }

    public void createChildren() throws InvalidPathException {
        File node = new File(this.filepath);
        if (node.isDirectory()) {
            File[] files = node.listFiles();
            this.children = new Node[files.length];
            for (int i = 0; i < files.length; i++) {
                Node child = new Node(this, files[i].getAbsolutePath());
                this.children[i] = child;
            }
        }
    }

    public boolean representsFile() {
        File node = new File(this.filepath);
        if (node.isFile()){
            return true;
        }else{
            return false;
        }
    }

    private long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                length += files[i].length();
            }
            else {
                length += getFolderSize(files[i]);
            }
        }
        return length;
    }

    @Override
    public String toString() {
        String character = "B";
        int KB = 1024, MB = KB * 1024, GB = MB * 1024;
        if(this.size > GB)
        {
            this.size = this.size / GB;
            character = "GB";
        }
        else if (this.size > MB)
        {
            this.size = this.size / MB;
            character = "MB";
        }
        else if (this.size > KB)
        {
            this.size = this.size / KB;
            character = "KB";
        }
        NumberFormat formatter = new DecimalFormat("#0.00");
        String out = ("Node{" + "filename='" + this.filename + '\'' +
                ", size=" + formatter.format(this.size) + " " + character + '}');
        return out;
    }

    public String getFilename() {
        return filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public double getSize() {
        return size;
    }

    public Node[] getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }
}
