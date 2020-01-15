package com.company;

public class InvalidPathException extends Exception {
    public InvalidPathException(){
        super("Greska: Nepostojeca putanja!");
    }
}
