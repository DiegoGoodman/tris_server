package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket MyServerSocket = new ServerSocket(3000);
        Socket G1 = MyServerSocket.accept();

        BufferedReader G1_in = new BufferedReader(new InputStreamReader(G1.getInputStream()));
        PrintWriter G1_out = new PrintWriter(G1.getOutputStream(), true);

        G1_out.println("WAIT");
        Socket G2 = MyServerSocket.accept();

        BufferedReader G2_in = new BufferedReader(new InputStreamReader(G2.getInputStream()));
        PrintWriter G2_out = new PrintWriter(G2.getOutputStream(), true);

        G1_out.println("READY");
        G2_out.println("READY");

        String board = "0,0,0,0,0,0,0,0,0,";
        String celle[] = board.split(",");
        int cont = 1;
        do {
            String risultato = "";
            do {
                if (cont%2 != 0){
                    String pos_received = G1_in.readLine();
                    int pos = Integer.parseInt(pos_received);
                    if (pos >= 0 && pos < 9){
                        if (celle[pos].equals("0")){
                            celle[pos] = "1";
                            risultato = "OK";
                        } else risultato = "KO"; 
                    }
                    else risultato = "KO";
                    if (celle[0].equals("1") && celle[3].equals("1") && celle[6].equals("1") ||
                    celle[1].equals("1") && celle[4].equals("1") && celle[7].equals("1") ||
                    celle[2].equals("1") && celle[5].equals("1") && celle[8].equals("1") ||
                    celle[0].equals("1") && celle[1].equals("1") && celle[2].equals("1") ||
                    celle[3].equals("1") && celle[4].equals("1") && celle[5].equals("1") ||
                    celle[6].equals("1") && celle[7].equals("1") && celle[8].equals("1") ||
                    celle[0].equals("1") && celle[4].equals("1") && celle[8].equals("1") ||
                    celle[2].equals("1") && celle[4].equals("1") && celle[6].equals("1")){
                        risultato = "W";
                    }
                    else if (cont == 9) risultato = "P";
                    if (risultato.equals("KO")) G1_out.println(risultato);
                }
                else {
                    String pos_received = G2_in.readLine();
                    int pos = Integer.parseInt(pos_received);
                    if (pos >= 0 && pos < 9){
                        if (celle[pos].equals("0")){
                            celle[pos] = "2";
                            risultato = "OK";
                        } else {risultato = "KO";}
                    }
                    else{ risultato = "KO";
                    }
                    if (celle[0].equals("2") && celle[3].equals("2") && celle[6].equals("2") ||
                    celle[1].equals("2") && celle[4].equals("2") && celle[7].equals("2") ||
                    celle[2].equals("2") && celle[5].equals("2") && celle[8].equals("2") ||
                    celle[0].equals("2") && celle[1].equals("2") && celle[2].equals("2") ||
                    celle[3].equals("2") && celle[4].equals("2") && celle[5].equals("2") ||
                    celle[6].equals("2") && celle[7].equals("2") && celle[8].equals("2") ||
                    celle[0].equals("2") && celle[4].equals("2") && celle[8].equals("2") ||
                    celle[2].equals("2") && celle[4].equals("2") && celle[6].equals("2")){
                        risultato = "W";
                    }
                    else if (cont == 9) {risultato = "P";}

                    if (risultato.equals("KO")) G2_out.println(risultato);
                }
            }while(risultato.equals("KO"));
            if (cont%2 != 0){
                if (risultato.equals("OK")){
                    G1_out.println(risultato);
                    G2_out.println(Arrays.toString(celle));
                }
                else if (risultato.equals("W")){
                    celle[9] = risultato;
                    G1_out.println(risultato);
                    G2_out.println(Arrays.toString(celle));
                    cont = 9;
                }
                else {
                    celle[9] = risultato;
                    G1_out.println(risultato);
                    G2_out.println(Arrays.toString(celle));
                }
            }
            else {
                if (risultato.equals("OK")){
                    G2_out.println(risultato);
                    G1_out.println(Arrays.toString(celle));
                }
                else if (risultato.equals("W")){
                    celle[9] = risultato;
                    G2_out.println(risultato);
                    G1_out.println(Arrays.toString(celle));
                    cont = 9;
                }
                else {
                    celle[9] = risultato;
                    G2_out.println(risultato);
                    G1_out.println(Arrays.toString(celle));
                }
            }
            cont++;
        }while(cont < 10);
        MyServerSocket.close();
    }
}