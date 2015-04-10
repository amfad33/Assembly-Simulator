package ir.amfad;

import java.io.*;

/**
 * Created by Amir Hosein Fadaei on 3/23/2015.
 */

public class Parser {
    public int[] Registers = new int[32];

    Parser(BufferedReader br){
        Registers[0] = 0;
        try {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                if(sCurrentLine.equals("end"))
                    break;
                boolean res = parseLine(sCurrentLine);
                if(!res){
                    System.out.println("Unexpected input! you must enter commands like this :\"command in1,in2,in3\"");
                    System.out.println("which in3 can be a register or numeric input");
                    System.out.println("<Assembler Simulator>  Copyright (C) <2015>  <Amir Hosein Fadaei>\n" +
                            "    This program comes with ABSOLUTELY NO WARRANTY.\n" +
                            "    This is free software, and you are welcome to redistribute it\n" +
                            "    under certain conditions.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Invalid data");
        }

    }

    private boolean parseLine(String line){
        String[] parts = spiliter(line);
        if(parts.length<3)
            System.out.println("Invalid data");
        else {
            if (line.startsWith("addi")) {
                int endPoint = Integer.parseInt(parts[0].substring(1));
                int fPoint = Integer.parseInt(parts[1].substring(1));
                long s = Integer.parseInt(parts[2]);
                if (endPoint > 31 || fPoint > 31)
                    System.out.println("Invalid data");
                else {
                    Registers[endPoint] = (int)(Registers[fPoint] + s);
                    //System.out.println("new value of register s"+endPoint+" is "+Registers[endPoint]);
                    return true;
                }
            } else if (line.startsWith("andi")) {
                int endPoint = Integer.parseInt(parts[0].substring(1));
                int fPoint = Integer.parseInt(parts[1].substring(1));
                long s = Integer.parseInt(parts[2]);
                if (endPoint > 31 || fPoint > 31)
                    System.out.println("Invalid data");
                else {
                    Registers[endPoint] = Registers[fPoint] & (int)s;
                    //System.out.println("new value of register s"+endPoint+" is "+Registers[endPoint]);
                    return true;
                }
            } else if (line.startsWith("xori")) {
                int endPoint = Integer.parseInt(parts[0].substring(1));
                int fPoint = Integer.parseInt(parts[1].substring(1));
                long s = Integer.parseInt(parts[2]);
                if (endPoint > 31 || fPoint > 31)
                    System.out.println("Invalid data");
                else {
                    Registers[endPoint] = Registers[fPoint] ^ (int)s;
                    //System.out.println("new value of register s"+endPoint+" is "+Registers[endPoint]);
                    return true;
                }
            } else if (line.startsWith("ori")) {
                int endPoint = Integer.parseInt(parts[0].substring(1));
                int fPoint = Integer.parseInt(parts[1].substring(1));
                long s = Integer.parseInt(parts[2]);
                if (endPoint > 31 || fPoint > 31)
                    System.out.println("Invalid data");
                else {
                    Registers[endPoint] = Registers[fPoint] | (int)s;
                    //System.out.println("new value of register s"+endPoint+" is "+Registers[endPoint]);
                    return true;
                }
            } else if (line.startsWith("add")) {
                int endPoint= Integer.parseInt(parts[0].substring(1));
                int fPoint= Integer.parseInt(parts[1].substring(1));
                int sPoint= Integer.parseInt(parts[2].substring(1));
                if(endPoint>31 || fPoint>31 || sPoint>31)
                    System.out.println("Invalid data");
                else{
                    Registers[endPoint] = Registers[fPoint] + Registers[sPoint];
                    //System.out.println("new value of register s"+endPoint+" is "+Registers[endPoint]);
                    return true;
                }
            } else if (line.startsWith("sub")) {
                int endPoint= Integer.parseInt(parts[0].substring(1));
                int fPoint= Integer.parseInt(parts[1].substring(1));
                int sPoint= Integer.parseInt(parts[2].substring(1));
                if(endPoint>31 || fPoint>31 || sPoint>31)
                    System.out.println("Invalid data");
                else{
                    Registers[endPoint] = Registers[fPoint] - Registers[sPoint];
                    //System.out.println("new value of register s"+endPoint+" is "+Registers[endPoint]);
                    return true;
                }
            } else if (line.startsWith("and")) {
                int endPoint = Integer.parseInt(parts[0].substring(1));
                int fPoint = Integer.parseInt(parts[1].substring(1));
                int sPoint = Integer.parseInt(parts[2].substring(1));
                if (endPoint > 31 || fPoint > 31 || sPoint > 31)
                    System.out.println("Invalid data");
                else {
                    Registers[endPoint] = Registers[fPoint] & Registers[sPoint];
                    //System.out.println("new value of register s"+endPoint+" is "+Registers[endPoint]);
                    return true;
                }
            } else if (line.startsWith("xor")) {
                int endPoint = Integer.parseInt(parts[0].substring(1));
                int fPoint = Integer.parseInt(parts[1].substring(1));
                int sPoint = Integer.parseInt(parts[2].substring(1));
                if (endPoint > 31 || fPoint > 31 || sPoint > 31)
                    System.out.println("Invalid data");
                else {
                    Registers[endPoint] = Registers[fPoint] ^ Registers[sPoint];
                    //System.out.println("new value of register s"+endPoint+" is "+Registers[endPoint]);
                    return true;
                }
            } else if (line.startsWith("or")) {
                int endPoint = Integer.parseInt(parts[0].substring(1));
                int fPoint = Integer.parseInt(parts[1].substring(1));
                int sPoint = Integer.parseInt(parts[2].substring(1));
                if (endPoint > 31 || fPoint > 31 || sPoint > 31)
                    System.out.println("Invalid data");
                else {
                    Registers[endPoint] = Registers[fPoint] | Registers[sPoint];
                    //System.out.println("new value of register s"+endPoint+" is "+Registers[endPoint]);
                    return true;
                }
            }
        }
        return false;
    }

    private String[] spiliter(String in){
        String input = in.substring(in.lastIndexOf(" ")+1);
        return input.split(",");
    }

    public String toString(){
        String res="";
        for(int i=0;i<32;i++)
            res+="s"+i+" : "+Registers[i]+"\n";
        return res;
    }
    public void writeFile(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("result.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            System.out.println("There is a problem in file writing");
        } catch (UnsupportedEncodingException e) {
            System.out.println("There is a problem in file writing");
        }
        for(int i=0;i<32;i++)
            writer.println("s"+i+" : "+Registers[i]);
        writer.close();
    }
}
