package de.hfu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws IOException {
        String read_it = "";
        System.out.print("Give me text: ");
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        read_it =  reader.readLine();
        System.out.println(read_it.toUpperCase());
    }
}