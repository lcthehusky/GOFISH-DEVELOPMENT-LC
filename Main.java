import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Thread;
class Main extends Constants{
    public static ArrayList<CardMkr> stock = new ArrayList<CardMkr>();
    public static ArrayList<Player> plyArray = new ArrayList<Player>();
    public static Scanner scan = new Scanner(System.in);
    public static String username;
    public static int cardcntr1 = 0;
    public static int players = 2;
    private static int playerTurn = 0;
    public static int turnCounter = 0;
    public static boolean testMode = false;
    public static boolean pHasCards = true;
    public static boolean cRemaining = true;
    private static int pSize;
    private static Player cPlayer;
    private static boolean uElim = false;
    public static boolean wntply, wntname;
    public static int crdspr = 0;
    public static String scansr = new String();
    public static String[] CstmPlayerNams = new String[4];

    public static Boolean asker() {
        System.out.print(ANSI_YELLOW + "\n(YES)" + ANSI_RED + " (NO)\n" + ANSI_RESET + ANSI_ANSWER);
        scansr = scan.nextLine().toUpperCase();
        for (String YesChkr : YesWrds) {
          if (scansr.equals(YesChkr) || (scansr.contains(YesChkr))) {
            return true;
          }
        }
        return false;
      }
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
        System.out.println(ANSI_CLEAR + "Welcome our program made by Luis and Jesus. Hope you like it =)");
    System.out.println("Wanna run the program in test mode?");
    testMode = asker();
    wait(1500, true);
    System.out.print(ANSI_CLEAR + "What is your name?" + ANSI_ANSWER);
    username = scan.nextLine();
    wait(1000, true);
    System.out.println("Here are the rules of GOFISH "+username+"\n\n══════════════════════════════════════════════════════════════════════════");
    System.out.println("\033[4;34m\nTHE RULES OF GOFISH"+ANSI_RESET+"\n\nThe player and the opponents both start with 5/7 cards depending on the\namount of players. The player asks for a card by asking any opponent by\ntheir name. The asker must have at least one of the card they are asking\nfor. Make sure to type it out in letters, For example: A Queen or a ten.\nThe type of suite doesn't matter just the value. If the person has the\ncard then they must hand over every card of that value. If the player has\nnone, they say, 'Go fish!' and the player who made the request draws a\ncard. The game ends when all thirteen books have been won. The winner is\nthe player with the most books. \n\n══════════════════════════════════════════════════════════════════════════\n\nAre you ready to proceed?");
    wntply = asker();
    while (wntply != true) {
      System.out.println(ANSI_CLEAR + "\n" + username + " are you sure you don't want to play" + ANSI_BLUE + " GoFish"
          + ANSI_RESET + " ?");
      wntply = asker();
      if (wntply == true) {
        System.out.println("\nOh alright then =(");
        wait(500, true);
        System.out.println(ANSI_BLUE + "\nGoodbye!");
        wait(500, true);
        return;
      } else {
        wntply = true;
      }
    }
    System.out.println("\nThen lets play some" + ANSI_BLUE + " GoFish" + ANSI_RESET + "!");
    wait(1000, true);
    while (true) {
      while(true){
        try{
          System.out.print("\nHow many players do you want to play with (2-5)?" + ANSI_ANSWER);
          players = scan.nextInt();
          break;
        }
        catch(Exception e){
          System.out.println(Main.ANSI_RED+"\nIm sorry but you input was invalid, try again."+Main.ANSI_RESET);
          scan.nextLine();
          wait(500, true);
        }
      }
      
      wait(1000, true);
      scan.nextLine();
      if (players >= 2 & players <= 3) {
        crdspr = 7;
        break;
      } else if (players >= 4 & players <= 5) {
        crdspr = 5;
        break;
      } else {
        System.out.print(
          "Im Sorry you have an " + ANSI_RED + "invalid" + ANSI_RESET + " number of players.\n\nPlease Try Again.");
        wait(1250, true);
      }
    }
    System.out.println("Would You like to Name your opponents?");
    wntname = asker();
    wait(1000, true);
    plyArray.add(new Player(username, crdspr,true));
    for (int cntr1 = 1; cntr1 < players; cntr1++) {
      if (wntname == true) {
        System.out.print("\nWhat would you like to name player number " + (cntr1 + 1) + " ?" + ANSI_ANSWER);
        CstmPlayerNams[cntr1 - 1] = scan.nextLine();
        plyArray.add(new Player(CstmPlayerNams[cntr1 - 1], crdspr, false));
      } else {
        plyArray.add(new Player(crdspr));
      }
    }
    System.out.println("You will receive " + ANSI_BLUE + crdspr + ANSI_RESET + " cards in your hand.\n");
    wait(2000, true);
    for (int counter = 0; counter < 5; counter++) {
      System.out.print(ANSI_CLEAR + "Generating Cards ");
      wait(250, true);
      System.out.print(ANSI_CLEAR + "Generating Cards . ");
      wait(250, true);
      System.out.print(ANSI_CLEAR + "Generating Cards . . ");
      wait(250, true);
      System.out.print(ANSI_CLEAR + "Generating Cards . . . ");
      wait(250, true);
    }
    System.out.println("Cards generated " + ANSI_GREEN + "Successfully\n" + ANSI_RESET);
    wait(2000, true);
    // Remove this code this is just the code for formatting the Arrays
    // plyArray[0].CardNum = CardMkr.CardDetector(plyArray[0].Hand);
    // plyArray[0].Hand =
    // CardMkr.CWardFormatter(plyArray[0].Hand,plyArray[0].CardNum);
    System.out.println("\nThe order of the players is:\n");
    for (int cntr10 = 0; cntr10 < players; cntr10++) {
      if (cntr10 == 0) {
        System.out.print(ANSI_BLUE + plyArray.get(cntr10).Name + ANSI_RESET + ",");
        wait(1000, false);
      } else if (cntr10 == players - 1) {
        System.out.println(" Lastly, " + ANSI_RED + plyArray.get(cntr10).Name + ANSI_RESET);
        wait(1000, false);
      } else {
        System.out.print(" then " + ANSI_RED + plyArray.get(cntr10).Name + ANSI_RESET + ",");
        wait(1000, false);
      }
    }
    System.out.println("══════════════════════════════════════════════════════════════════════════");
    System.out.println("Remember to ask another player for their cards you must state for example:" + ANSI_ANSWER
        + plyArray.get(1).Name + " do you have any tens?\n\nor"+ANSI_ANSWER+" Omar do you have any kings?");
    System.out.println("══════════════════════════════════════════════════════════════════════════");
        while(true){
            pSize = plyArray.size();
            cPlayer = plyArray.get(playerTurn);
            if (turnCounter !=0 && (!(pSize<players))){
              System.out.println("Order of Players: ");
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
            }
            if (pSize<players){
                
                System.out.println("A player has been eliminated, The remaining players are: ");
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
            System.out.print("It's your Turn " );
            if (playerTurn == 0){
                System.out.println(Constants.ANSI_BLUE + cPlayer.Name + Constants.ANSI_RESET + "!\n");
            }
            else{
                System.out.println(Constants.ANSI_RED + cPlayer.Name + Constants.ANSI_RESET + "!\n");
            }
            if (cRemaining == true && cPlayer.Hand.size()==0){
                System.out.println("Since you had no cards left we gave you a Card ;)");
                CardMkr.DealCard(cPlayer.Hand, 1);
            }
            if (cPlayer.isPlayer){
              System.out.println("Your cards are:");
              CardMkr.CardDisplay(cPlayer.Hand);
            }
            if (cPlayer.Books.size() != 0){
              System.out.println("Your books are:");
              CardMkr.CardDisplay(cPlayer.Books);
            }
            // Get the takecard function to return if the player has been eliminated and end the game.
            Player.ran = false;
            Player.catchCntr = 0;
            if (Player.TakeCard(cPlayer, playerTurn)== true){
                uElim = true;
                break;
            }
            if (Player.BookCkr(cPlayer)){
              break;
            }
            wait(2000, true);
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
        if (uElim){
            System.out.println(Constants.ANSI_RED+"GAME OVER YOU HAVE BEEN ELIMINATED. BETTER LUCK NEXT TIME"+Constants.ANSI_RESET);
        }
        else{
            System.out.print("\nTHE GAME HAS ENDED");
        }
        System.out.print("\nTHE WINNER IS:"+Player.hscPlayer.Name.toUpperCase()+"!!!\n\nWITH "+Player.hscPlayer.Score+" POINTS!!!");
    }
}