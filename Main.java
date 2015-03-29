package ir.amfad;

import java.io.*;

/**
 * Created by Amir Hosein Fadaei on 3/23/2015.
 * Assembler Simulator is a simple simulator for Assembly programming language
 Copyright (C) 2015  Amir Hosein Fadaei

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to Assembler Simulator version 0.02!");
        System.out.println("For help please enter '-help'");
        while(true) {
            String input = sbr.readLine();
            if(input.equals("-end"))
                break;
            else if(input.equals("-help")) {
                System.out.println("All the instructions :");
                System.out.println("\"-input 'the input file address'\" will start the simulation for a '.sim' input file");
                System.out.println("\"-online\" is the online simulator (reading console)");
                System.out.println("\"-end\" wil end the simulation");
            }
            else if(input.startsWith("-input")){
                String address = input.trim().substring(input.lastIndexOf(" ")+1);
                if(!address.endsWith(".sim"))
                    System.out.println("Not a valid input");
                else {
                    BufferedReader fbr = null;
                    try {
                        fbr = new BufferedReader(new FileReader(address));
                        Parser fileParser= new Parser(fbr);
                        fileParser.writeFile();
                    } catch (IOException ex) {
                        System.out.println("Not a valid input");
                    }
                }
            }
            else if(input.startsWith("-online")){
                System.out.println("to end simulation please enter \"end\"");
                Parser fileParser= new Parser(sbr);
                System.out.println(fileParser);
            }
        }
    }
}
