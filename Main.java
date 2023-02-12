import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Thread;
class Main{
    public static ArrayList<CardMkr> stock = new ArrayList<CardMkr>();
    public static ArrayList<Player> plyArray = new ArrayList<Player>();
    public static Scanner scan = new Scanner(System.in);
    public static String username;
    public static int cardcntr1 = 0;
    public static int players = 2;
    public static int playerTurn = 0;
    public static int turnCounter = 0;
    public static boolean testMode = true;
    public static boolean pHasCards = true;
    public static boolean cRemaining = true;
    private static int pSize;
    private static Player cPlayer;
    public static void wait(int wtime, boolean clearMode) {
        if (testMode == true) {
          wtime = 0;
        }
        try {
          Thread.sleep(wtime);
          if (testMode == false & clearMode == true) {
            System.out.print(Constants.ANSI_CLEAR);
          }
        } catch (Exception e) {
          System.out.print(e);
        }
      }
    public static void main(String[] args){
        /*
         * Making the Card objects, shuffling and adding them to the stock array.
         */
        for (int cntr1 = 0; cntr1<52; cntr1++){
            stock.add(new CardMkr());
        }
        stock = CardMkr.shfflr(stock);
        System.out.print(Constants.ANSI_CLEAR+"What is your name sir?"+Constants.ANSI_ANSWER);
        username = scan.nextLine();
        plyArray.add(new Player(username, 5,true));
        plyArray.add(new Player("ElImpostorFromAmongUs", 5,false));
        System.out.println("Deck has "+stock.size()+" cards");
    
        while(true){
            pSize = plyArray.size();
            cPlayer = plyArray.get(playerTurn);
            if (pSize<players){
                
                System.out.println("The remaining players are: ");
                for (int i = 0; i < pSize; i++){
                    if (i == 0){
                        wait(500,false);
                        System.out.println(Constants.ANSI_BLUE + plyArray.get(i).Name + Constants.ANSI_RESET+", ");
                    }
                    else if (i == pSize-1){
                        wait(500,false);
                        System.out.println("and "+Constants.ANSI_RED+plyArray.get(i).Name+Constants.ANSI_CLEAR);
                    }
                    else{
                        wait(500,false);
                        System.out.println(Constants.ANSI_RED + plyArray.get(i).Name + Constants.ANSI_RESET+", ");
                    }
                }
                players = pSize;
            }
            //Aqui ponemos nuestro codigo pa el juego mogs.
            System.out.print("It's your Turn ");
            if (playerTurn == 0){
                System.out.println(Constants.ANSI_BLUE + cPlayer.Name + Constants.ANSI_RESET + "!");
            }
            else{
                System.out.println(Constants.ANSI_RED + cPlayer.Name + Constants.ANSI_RESET + "!");
            }
            if (cRemaining == true && cPlayer.Hand.size()==0){
                System.out.println("Since you had no cards left we gave you a Card ;)");
                CardMkr.DealCard(cPlayer.Hand, 1);
            }
            System.out.println("Your cards are:");
            CardMkr.CardDisplay(cPlayer.Hand);
            if (cPlayer.Books.size() != 0){
                System.out.println("Your books are:");
                CardMkr.CardDisplay(cPlayer.Books);
            }
            // Get the takecard function to return if the player has been eliminated and end the game.
            wait(2500, true);
            playerTurn++;
            if (playerTurn == pSize){
                System.out.println("The collective turn Ended");wait(3000,true);
                playerTurn = 0;
                turnCounter++;
            }
            if (turnCounter == 30){
                return;
            }
        }
    }
}