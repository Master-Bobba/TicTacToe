package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class StatsReader {

    private final String path = "src/main/resources/Stats.csv";
    private int gamesPlayed;
    private int losses;
    private int draws;
    private int wins;

    public StatsReader(){
        readStats();
    }

    private void writeStats(){
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(path);
            String toWrite = "Wins," + wins + "\n";
            toWrite += "Draws," + draws + "\n";
            toWrite += "Losses," + losses + "\n";
            toWrite += "Games," + gamesPlayed + "\n";
            fileOutputStream.write(toWrite.getBytes(StandardCharsets.UTF_8));

        } catch (FileNotFoundException e) {
            System.out.println("error writing to file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                fileOutputStream.close();
            } catch (IOException e){
                System.out.println("Cannot close file!");
            }
        }
    }


    private void readStats(){
        FileInputStream fileInputStream = null;
        Scanner scanner = null;

        try{
            fileInputStream = new FileInputStream(path);
            scanner = new Scanner(fileInputStream);

            while(scanner.hasNextLine()){
                String[] tokens = scanner.nextLine().split(",");

                switch (tokens[0]){
                    case "Wins": {
                        wins = Integer.parseInt(tokens[1]);
                        break;
                    }
                    case "Draws": {
                        draws = Integer.parseInt(tokens[1]);
                        break;
                    }
                    case "Losses":{
                        losses = Integer.parseInt(tokens[1]);
                        break;
                    }
                    case "Games": {
                        gamesPlayed = Integer.parseInt(tokens[1]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("error reading from file");

        } finally {
            try{
                fileInputStream.close();
            } catch (IOException e){
                System.out.println("Cannot close file!");
            }
        }
    }

    public void setWins(){
        this.wins += 1;
        this.gamesPlayed += 1;
        writeStats();
    }
    public void setLosses(){
        this.losses += 1;
        this.gamesPlayed += 1;
        writeStats();
    }
    public void setDraws(){
        this.gamesPlayed += 1;
        this.draws += 1;
        writeStats();
    }


    /**
     * Just for testing of the filereader class
     * @param args
     */
    public static void main(String[] args){
        StatsReader fr = new StatsReader();
    }

}
