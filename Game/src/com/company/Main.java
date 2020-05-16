package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;


public class Main {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int points = 0;

        Map<String,String> associative = new HashMap<String,String>();

        //добавление элементов
        associative.put("АБСОЛЮТНЫЙ", "водка, идеальный, истинный, конец, минус, плюс, победа, полностью");
        associative.put("АВТОРИТЕТ", "местный, папа, человека, я, учителя, важность, важный, высокий, глава, зона, лучший");
        associative.put("ГАРМОНИЧНЫЙ", "баян, букет, гармонист, гармония, день, дизайн, дом, дух, инструмент, картина, комфорт,");

        System.out.print("Ник игрока1: ");
        String playerOne = in.next();

        //System.out.print("\nНик игрока2: ");
        //String playerTwo = in.next();

        System.out.println(associative.get("АБСОЛЮТНЫЙ"));
        System.out.print("Напишите ассоциацию: ");

        String anewF = "АБСОЛЮТНЫЙ";
        String anewS = "АВТОРИТЕТ";
        String anewT = "ГАРМОНИЧНЫЙ";
        String answer = in.next().toUpperCase();

            if (answer.equals(anewF)){

                points += 15;
                System.out.println("Молодец +" + points);

            } else {

                System.out.print("Не переживай, попробуй отгадать другое слово" + "\n" + associative.get("АВТОРИТЕТ"));
                System.out.print("Напишите ассоциацию: ");
                answer = in.next().toUpperCase();

                if (answer.equals(anewS)){

                    points += 20;
                    System.out.print("Молодец +" + points);

                } else{

                    System.out.print("На этом мы с тобой прощаемся, попробуй еще раз, позже!");
                    System.exit(0);

                }


            }
            System.out.println("Молодец у тебя не плохо получается, давай продолжим.\nПоробуй угадать это слово: " + associative.get("ГАРМОНИЧНЫЙ") + "\n");
            System.out.print("Напишите ассоциацию: ");
            answer = in.next().toUpperCase();

        if (answer.equals(anewT)){

            points += 30;
            System.out.print("Молодец +" + points + "\n" + "Ты получил максимальное количество баллов!");

        } else{

            System.out.print("На этом мы с тобой прощаемся, попробуй еще раз, позже!");
            System.exit(0);

        }




        File resultFile = new File("result.txt");
        try{
            BufferedWriter write = new BufferedWriter(new FileWriter(resultFile, true));
            //write.write("Ник1: " + playerOne + "\n" + "Ник2: " + playerTwo);
            write.write("Ник: " + playerOne + " | " + "Баллы: " + points + "\n");
            write.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try(FileReader reader = new FileReader("result.txt"))
        {
            char[] buf = new char[256];
            int c;
            while((c = reader.read(buf))>0){

                if(c < 256){
                    buf = Arrays.copyOf(buf, c);
                }
                System.out.print(buf);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }


    }

