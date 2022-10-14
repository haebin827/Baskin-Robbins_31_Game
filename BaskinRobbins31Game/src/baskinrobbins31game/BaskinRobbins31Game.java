package baskinrobbins31game;
import java.util.*;
public class BaskinRobbins31Game {
    public static void main(String[] args) {
        //variables
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        int choice = 1;
        int numberOfPlayers = 0;
        boolean playAgain = true;
        
        //codes
        System.out.println("< Baskin Robbins 31 Game >");
        
        while(choice != 0){
            menu();
            choice = input.nextInt();
                if(choice == 1){
                    while(playAgain != false){
                        //ask how many players will play
                        choice = menu2(choice, input);
                        if(choice == 0){
                            break;
                        }
                        numberOfPlayers = choice;
                        String[] bot = setName(choice, input);

                        //choose whether shuffle the order or not
                        choice = menu3(choice, input);
                        game(choice, numberOfPlayers, bot, rand, input);
                        if(menu4(choice, input) == 2){
                            playAgain = false;
                        }
                    }
                }
                else if(choice == 2){
                    //show the game rule
                    description();
                }
                else if (choice != 0){
                    System.out.println("\nYou entered the wrong number.");
                }
                
            if(playAgain == false){
                break;
            }
        }

        System.out.println("\n(=w=)/ < Good Bye! )");
    }
    
    public static void menu(){
        System.out.println("\n1. Start Game");
        System.out.println("2. How to play game");
        System.out.println("0. Quit");
        System.out.print(">> ");
    }
    public static void description(){
        System.out.println("\n(owo)/ < Game Rule )");
        System.out.println("  When the game starts, each player calls the numbers from 1 to 31 sequentially.");
        System.out.println("  You can call 1 to 3 consecutive numbers at a time, and the player cannot select as many numbers as the previous player has called in succession.");
        System.out.println("  Whoever calls the number 31 loses!");
    }
    public static String[] setName(int choice, Scanner input){
        String[] bot = new String[choice];
        System.out.println("\nEnter each player's name: ");
        for(int i = 0; i<choice; i++){
        System.out.print(">> player " + (i+1) + ": ");
        bot[i] = input.next();
        }
        return bot;
    }
    public static int menu2(int choice, Scanner input){
        System.out.print("\nHow many players do you want to play with? (enter 0 to quit): ");
        choice = input.nextInt();
        while(choice >= 10 || choice < 0){
            if(choice >= 10){
                System.out.println("The number of players must be less than 10. Try again.");
            }
            else if(choice < 0){
                System.out.println("The number of players must be at least 1. Try again.");
            }
            System.out.print("\nHow many players do you want to play with? (enter 0 to quit): ");
            choice = input.nextInt();
        }
        return choice;
    }
    public static int menu3(int choice, Scanner input){
        System.out.println("\nDo you want to be the first? Want to shuffle the order?");
        System.out.println("1. Yes, I want to play first!");
        System.out.println("2. No, shuffle the order!");
        System.out.print(">> ");
        choice = input.nextInt();
        while(choice <= 0 || choice > 2){
            System.out.print("You entered the wrong number. Try again.");
            System.out.print("\n>> ");
            choice = input.nextInt();
        }
        return choice;
    }
    public static void game(int choice, int numberOfPlayers, String[] bot, Random rand, Scanner input){
        int call = 0;
        int result = 1;
        int playerOrder = 0;
        int who = 0;
        int prior_number = -1;
        
        if(choice == 2){
            playerOrder = rand.nextInt(numberOfPlayers)+0;
        }
        System.out.println("\nYou are the " + (playerOrder+1) + "th turn!");
        System.out.println("\n**GAME START**\n");
                    
        while(result < 31){
            
            //Bot turn
            for(int i = 0; i<playerOrder; i++){
                System.out.print(bot[i] + ": ");
                do{
                    call = rand.nextInt(3)+1;
                }while(call == prior_number);
                prior_number = call;
                
                for(int j = 0; j<call; j++){
                    System.out.print((result++) + "! ");
                    who = i;
                }
                System.out.println();
                if(result > 31){
                    break;
                }
            }
            if(result > 31){
                break;
            }
            
            //Player turn
            System.out.print("How many numbers will you call? (pick between 1~3): ");
            call = input.nextInt();
            while(call == prior_number || call < 1 || call > 3){
                if(call == prior_number){
                    System.out.println("You cannot choose " + call + ". Try the another number.\n");
                }
                if(call < 1 || call > 3){
                    System.out.println("You entered the wrong number. Try again.\n");
                }
                System.out.print("How many numbers will you call? (pick between 1~3): ");
                call = input.nextInt();
            }
            prior_number = call;
            System.out.print("{You}: ");
            for(int i = 0; i<call; i++){
                System.out.print((result++) + "! ");
                who = -1;
            }
            System.out.println();
            if(result > 31){
                    break;
            }
            
            //Bot turn
            for(int i = playerOrder; i<numberOfPlayers; i++){
                System.out.print(bot[i] + ": ");
                do{
                    call = rand.nextInt(3)+1;
                }while(call == prior_number);
                prior_number = call;
                
                for(int j = 0; j<call; j++){
                    System.out.print((result++) + "! ");
                    who = i;
                }
                System.out.println();
                if(result > 31){
                    break;
                }
            }
            if(result > 31){
                break;
            }
        }
        if(who == -1){
            //Thread.sleep(1000);
            System.out.println("\nYou Lose!");
        }
        else{
            System.out.println("\n" + bot[who] + " Lose!");
        }
    }
    public static int menu4(int choice, Scanner input){
        System.out.println("\nDo you want to play another game? ");
        System.out.println("1. Yes!");
        System.out.println("2. No. I'm done today :)");
        System.out.print(">> ");
        choice = input.nextInt();
        return choice;
    }
}
