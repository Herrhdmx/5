package de.hfu;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
/**
 * Application class for OSSE - Praktikum 5: Build Management
 * @author Hermann Lau
 * @version 1.0
 */

public class App {

    /**
     * This program reads the user console input and prints it back capitalized.
     */

    public static void main(String[] args) throws IOException {
        String read_it = "";
        System.out.print("Give me text: ");
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        read_it =  reader.readLine();
        System.out.println(read_it.toUpperCase());
    }
}