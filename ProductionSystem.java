/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productionsystem;

import java.util.*;

/**
 *
 * @author ammarawad
 */
public class ProductionSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        System.out.println("********************Welcome to Production System - Order String Program********************\n\n");
        
        // create a scanner to read the command-line input
        Scanner scanner;
        
        // string varilable to store the user input string
        String userInputString = "";
        
        // the program designed to work on strings made from certain characters "ABCDEFGHIJ" with any order and length !!!
        int stringLength = 0;
        boolean validString = false;
        while(validString != true)
        {
            validString = true;
            
            // Ask the user to enter the string
            System.out.println("Kindly enter your string and I will put it in order !!!");
        
            // read the user input
            scanner = new Scanner(System.in);
            
            // store the user input in string variable
            userInputString = scanner.next();

            // get the length of the string entered by the user
            stringLength = userInputString.length();
            
            userInputString = userInputString.toLowerCase();
            
            for(int i = 0; i < stringLength; i++)
            {
                // check if the user entered character not in the approved list of characters ABCDEFGHIJ
                if(userInputString.charAt(i) != 'a' && userInputString.charAt(i) != 'b' && userInputString.charAt(i) != 'c' && userInputString.charAt(i) != 'd' && userInputString.charAt(i) != 'e' && 
                        userInputString.charAt(i) != 'f' && userInputString.charAt(i) != 'g' && userInputString.charAt(i) != 'h' && userInputString.charAt(i) != 'i' && userInputString.charAt(i) != 'j')
                {
                    validString = false;
                    System.out.println("\nYou should enter string consists of the those characters ABCDEFGHIJ only !!!\n\n");
                }
            }
        }
        
        // user input checked and clear now the logic of the production system starts
        /*
        Production add
            1. ba --> ab    10. cb --> bc   18. dc --> cd   25. ed  --> de  31. fe  --> ef  36. gf  --> fg  40. hg  --> gh  43. ih  --> hi  45. ji --> ij
            2. ca --> ac    11. db --> bd   19. ec --> ce   26. fd  --> df  32. ge  --> eg  37. hf  --> fh  41. ig  --> gi  44. jh  --> hj
            3. da --> ad    12. eb --> be   20. fc --> cf   27. gd  --> dg  33. he  --> eh  38. if  --> fi  42. jg  --> gj
            4. ea --> ae    13. fb --> bf   21. gc --> cg   28. hd  --> dh  34. ie  --> ei  39. jf  --> fj
            5. fa --> af    14. gb --> bg   22. hc --> ch   29. id  --> di  35. je  --> ej  
            6. ga --> ag    15. hb --> bh   23. ic --> ci   30. jd  --> dj
            7. ha --> ah    16. ib --> bi   24. jc --> cj
            8. ia --> ai    17. jb --> bj
            9. ja --> aj
        */
        
        // define linked list to hold the production add
        LinkedList<String> productionSetLinkedlist = new LinkedList<String>();
        productionSetLinkedlist.add(0, "buffer");
        productionSetLinkedlist.add(1, "ba"); productionSetLinkedlist.add(2, "ca"); productionSetLinkedlist.add(3, "da"); productionSetLinkedlist.add(4, "ea"); productionSetLinkedlist.add(5, "fa");
        productionSetLinkedlist.add(6, "ga"); productionSetLinkedlist.add(7, "ha"); productionSetLinkedlist.add(8, "ia"); productionSetLinkedlist.add(9, "ja"); 
        productionSetLinkedlist.add(10, "cb"); productionSetLinkedlist.add(11, "db"); productionSetLinkedlist.add(12, "eb"); productionSetLinkedlist.add(13, "fb"); productionSetLinkedlist.add(14, "gb");
        productionSetLinkedlist.add(15, "hb"); productionSetLinkedlist.add(16, "ib"); productionSetLinkedlist.add(17, "jb");
        productionSetLinkedlist.add(18, "dc"); productionSetLinkedlist.add(19, "ec"); productionSetLinkedlist.add(20, "fc"); productionSetLinkedlist.add(21, "gc"); productionSetLinkedlist.add(22, "hc");
        productionSetLinkedlist.add(23, "ic"); productionSetLinkedlist.add(24, "jc");
        productionSetLinkedlist.add(25, "ed"); productionSetLinkedlist.add(26, "fd"); productionSetLinkedlist.add(27, "gd"); productionSetLinkedlist.add(28, "hd"); productionSetLinkedlist.add(29, "id");
        productionSetLinkedlist.add(30, "jd");
        productionSetLinkedlist.add(31, "fe"); productionSetLinkedlist.add(32, "ge"); productionSetLinkedlist.add(33, "he"); productionSetLinkedlist.add(34, "ie"); productionSetLinkedlist.add(35, "je");
        productionSetLinkedlist.add(36, "gf"); productionSetLinkedlist.add(37, "hf"); productionSetLinkedlist.add(38, "if"); productionSetLinkedlist.add(39, "jf");
        productionSetLinkedlist.add(40, "hg"); productionSetLinkedlist.add(41, "ig"); productionSetLinkedlist.add(42, "jg");
        productionSetLinkedlist.add(43, "ih"); productionSetLinkedlist.add(44, "jh");
        productionSetLinkedlist.add(45, "ji");
        
        
        // define linked lists to carry the final output
        LinkedList<String> iterationLinkedlist = new LinkedList<String>();
        LinkedList<String> workingMemoryLinkedlist = new LinkedList<String>();
        LinkedList<String> conflictSetLinkedlist = new LinkedList<String>();
        LinkedList<String> ruleFiredLinkedlist = new LinkedList<String>();
      
        
        // check the rules violations and do the sort
        boolean violateTicket = false;
        int charChangeIndex = 0; // store the index of the character for replace
        int productionSetNumber = 46; // stroe the number of the production set or the rule fired, start with the bigest possible number and then take the smallest one possible
      
        // store the iteration ID
        int iterationNumber = 0;
        
        do
        {
            //System.out.println("String before processing " + userInputString + "\n\n");
            
            // store the iteration number
            iterationLinkedlist.add(iterationNumber, "" + iterationNumber);
            
            // store memory working (the string under processing now)
            workingMemoryLinkedlist.add(iterationNumber, userInputString);
            
            // keep track of all rules violations
            String rulesViolatedString = "";
            
            boolean anyViolate = false;
            for(int i = 0; i < stringLength - 1; i++)
            {
                if(i == 0)
                {
                    violateTicket = false;
                    productionSetNumber = 46;
                    anyViolate = false;
                }
                
                // get the two characters from the current location and the next location to check
                String tempString = "";
                tempString += userInputString.charAt(i);
                tempString += userInputString.charAt(i+1);

                // check if the two characters violating the defined rules
                boolean ruleExist = false;
                ruleExist = productionSetLinkedlist.contains(tempString);
                
                int indexOfCurrentRule = productionSetLinkedlist.indexOf(tempString);
                
                if(indexOfCurrentRule >= 1)
                {
                     if(rulesViolatedString.equals("") == true)
                     {
                        rulesViolatedString += indexOfCurrentRule;
                     }
                     else
                     {
                        rulesViolatedString += ";" + indexOfCurrentRule;
                     }
                }
                
                // if one rule get violation change the condition status to keep rolling until it is fixed.
                if(ruleExist == true)
                {
                    violateTicket = true;
                    anyViolate = true;
                    
                    
                    if(indexOfCurrentRule < productionSetNumber)
                    {
                        charChangeIndex = i;
                        productionSetNumber = indexOfCurrentRule;
                    }
                    
                }
            }
            
            // do the sort
            if(violateTicket == true)
            {
                // store the rule fired number 
                ruleFiredLinkedlist.add(iterationNumber, "" + productionSetNumber);
                
                String stringReplacement = "";
                stringReplacement += userInputString.charAt(charChangeIndex+1);
                stringReplacement += userInputString.charAt(charChangeIndex);
                    
                String sortedString = "";
                  
                for(int x = 0; x < stringLength; x++)
                {
                    if(x == charChangeIndex)
                    {
                        sortedString += userInputString.charAt(charChangeIndex+1);
                    }
                        
                    if(x == charChangeIndex+1)
                    {
                        sortedString += userInputString.charAt(charChangeIndex);
                    }
                        
                    if(x != charChangeIndex && x != charChangeIndex+1)
                    {
                        sortedString += userInputString.charAt(x);
                    }          
                }
                    
                userInputString = sortedString;
            }
            
            if(violateTicket == false)
            {
                conflictSetLinkedlist.add(iterationNumber, "Null");
                ruleFiredLinkedlist.add(iterationNumber, "Halt");
            }
            else
            {
                conflictSetLinkedlist.add(iterationNumber, rulesViolatedString);
            }
            
            //System.out.println("String after processing " + userInputString + "\n\n");
            
            iterationNumber = iterationNumber + 1;
        }
        while (violateTicket == true);
        
        // print the final output and results
        System.out.println("\n\n" + "Iteration\tWorking Memory\t\tConflict Set\tRule Fired");
        for(int i = 0; i < iterationLinkedlist.size(); i++)
        {
            System.out.println(iterationLinkedlist.get(i) + "\t\t" + workingMemoryLinkedlist.get(i) + "\t\t\t" + conflictSetLinkedlist.get(i) + "\t\t" + ruleFiredLinkedlist.get(i));
        }
        
        System.out.println("*******************************************************************************************");
    }
}
