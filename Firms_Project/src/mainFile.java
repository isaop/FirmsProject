import exceptions.RepositoryException;
import domain.*;
import repository.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class mainFile {
    public static void printMenu()
    {
        System.out.println("Menu:\n");
        System.out.println(" 0 - Exit\n");
        System.out.println(" 1 - Print for L1:\n");
        System.out.println(" 2 - Print for L2:\n");
    }

    public static double Similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
        }

        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }


    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }


    public static void main(String[] args) throws IOException {

        printMenu();
        FirmRepository firm_list = new FirmRepository();
        firm_list.readFromFile();


        ArrayList L1 = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("F:\\Firms_project\\src\\platitor.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] el = line.split(";");
                if (el.length != 1) {
                    System.err.println("Not a valid number of atributes " + line);
                    continue;
                }

                String id = el[0];
                L1.add(id);


            }
        } catch (IOException ex) {
            throw new RepositoryException("Error reading" + ex);
        }

        ArrayList L2 = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("F:\\Firms_project\\src\\benef.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] el = line.split(";");
                if (el.length != 1) {
                    System.err.println("Not a valid number of atributes " + line);
                    continue;
                }
                String idd = el[0];
                L2.add(idd);


            }
        } catch (IOException ex) {
            throw new RepositoryException("Error reading" + ex);
        }








        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter option: ");
            Integer i = input.nextInt();
            if (i == 0) {
                System.exit(0);
            } else if (i == 1) {
                for (Object firm : L1) {
                    double bestSimilarity = -1;
                    for (Firm myFirm : firm_list.findAll()) {
                        if(Similarity((String) firm,myFirm.getName()) > bestSimilarity)
                        {
                            bestSimilarity = Similarity((String) firm,myFirm.getName());
                        }


                    }
                    Firm mostSimilarFirm = new Firm();
                    for (Firm myFirm : firm_list.findAll())

                        if(Similarity((String) firm,myFirm.getName()) == bestSimilarity)
                        {
                            mostSimilarFirm = myFirm;
                            break;
                        }
                    System.out.println("Most similar firm for " + firm + " is: " + mostSimilarFirm);
                }

            } else if (i == 2) {
                for (Object firm : L2) {
                    double bestSimilarity = -1;
                    for (Firm myFirm : firm_list.findAll()) {
                        if (Similarity((String) firm, myFirm.getName()) > bestSimilarity) {
                            bestSimilarity = Similarity((String) firm, myFirm.getName());
                        }


                    }
                    Firm mostSimilarFirm = new Firm();
                    for (Firm myFirm : firm_list.findAll())

                        if (Similarity((String) firm, myFirm.getName()) == bestSimilarity) {
                            mostSimilarFirm = myFirm;
                            break;
                        }
                    System.out.println("Most similar firm for " + firm + " is: " + mostSimilarFirm);
                }
            }
        }
    }
}


