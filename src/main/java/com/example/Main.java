package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket MyServerSocket = new ServerSocket(3000);
        Socket G1 = MyServerSocket.accept();
        System.out.println("WAIT");
        MyServerSocket.wait();
        Socket G2 = MyServerSocket.accept();

        BufferedReader G1_in = new BufferedReader(new InputStreamReader(G1.getInputStream()));
        PrintWriter G1_out = new PrintWriter(G1.getOutputStream(), true);

        BufferedReader G2_in = new BufferedReader(new InputStreamReader(G2.getInputStream()));
        PrintWriter G2_out = new PrintWriter(G2.getOutputStream(), true);


        
    }
}