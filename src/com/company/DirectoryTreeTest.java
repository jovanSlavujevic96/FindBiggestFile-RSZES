package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DirectoryTreeTest {

    public static void main(String[] args) {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                System.out.println("Unesite apsolutnu putanju: ");
                String path = bufferRead.readLine();
                DirectoryTree tree = new DirectoryTree(path);
                Node root = tree.getRoot();
                Node maxFileNode = tree.findMaxSizeFile(root);
                Node maxDirectoryNode = tree.findMaxSizeDirectory(root);
                if (maxFileNode != null)
                    System.out.println("Najveci fajl je: " + maxFileNode);
                else
                    System.out.println("Ne postoji fajl");

                if (maxDirectoryNode != null)
                    System.out.println("Najveci direktorijum je: " + maxDirectoryNode);
                else
                    System.out.println("Ne postoji direktorijum");

            } catch (IOException | InvalidPathException e) {
                e.printStackTrace();
            }
        }
    }
}
