import repository.FirmRepository;
import domain.Firm;
import java.util.*;
import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        FirmRepository myList = new FirmRepository();
        myList.add(new Firm("1", "PAPER S.R.L."));
        myList.add(new Firm("2", "ABRACADABRA S.R.L."));
        myList.add(new Firm("3","ABSTRACT S.R.L."));
        myList.add(new Firm("4","NORIEL S.R.L."));
        myList.add(new Firm("5","SUGAR S.R.L."));
        myList.add(new Firm("6","NEWTON S.R.L."));


        List<String> L1 = new ArrayList<String>();
        L1.add("abracadabra");
        L1.add("NORIE S.R.L.");
        L1.add("PAPER");
        L1.add("SUGAR SRL");
        L1.add("NEWTOWN SRL");
        L1.add("ABRACAABRA");
        L1.add("ABDRCADABRA");
        L1.add("ABSTRACT S.R.L.");

        List<String> L2 = new ArrayList<String>();
        L2.add("NEWTON");
        L2.add("norie");
        L2.add("PAPEER SRL");
        L2.add("SUGAR SR");
        L2.add("NEWTON SRL");
        L2.add("ABRACAABRA");
        L2.add("ABS");
        L2.add("SUAAR");






        String printMenu = "";
        printMenu += "1 - Get Firms for L1\n";
        printMenu += "2 - Get Firms for L2\n";
        printMenu += "0 - Exit\n";
        Scanner console = new Scanner(System.in);
        boolean stop = false;
        while(stop == false) {
            System.out.println(printMenu);
            System.out.println("Enter number: ");
            int choice = console.nextInt();
            if (choice == 1)
            {
                for (String firm : L1) {
                    double bestSimilarity = -1;
                    for (Firm myFirm : myList.findAll()) {
                        if(Similarity(firm,myFirm.getName()) > bestSimilarity)
                        {
                            bestSimilarity = Similarity(firm,myFirm.getName());
                        }


                    }
                    Firm mostSimilarFirm = new Firm();
                    for (Firm myFirm : myList.findAll())

                        if(Similarity(firm,myFirm.getName()) == bestSimilarity)
                        {
                            mostSimilarFirm = myFirm;
                            break;
                        }
                    System.out.println("Most similar firm for " + firm + " is: " + mostSimilarFirm);
                }
            }
            else
            if (choice == 2) {
                for (String firm : L2) {
                    double bestSimilarity = -1;
                    for (Firm myFirm : myList.findAll()) {
                        if (Similarity(firm, myFirm.getName()) > bestSimilarity) {
                            bestSimilarity = Similarity(firm, myFirm.getName());
                        }


                    }
                    Firm mostSimilarFirm = new Firm();
                    for (Firm myFirm : myList.findAll())

                        if (Similarity(firm, myFirm.getName()) == bestSimilarity) {
                            mostSimilarFirm = myFirm;
                            break;
                        }
                    System.out.println("Most similar firm for " + firm + " is: " + mostSimilarFirm);
                }
            }
            else
            if(choice == 0)
                stop = true;
        }


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
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
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



}
