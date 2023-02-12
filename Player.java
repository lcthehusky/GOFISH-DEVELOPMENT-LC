import java.util.ArrayList;
import java.util.Random;

//I changed the atribute hand from being an Array to being an ArrayList is
class Player {
  public ArrayList<CardMkr> Hand;
  public ArrayList<CardMkr> Books;
  public String Name;
  public int Score;
  public boolean isPlayer;
  String[] defaultNames = { "Owen", "Alex", "Omar", "Miguel" };
  public static int nameindexcntr = 0;
  public static ArrayList<Integer> BCKR = new ArrayList<>();
  static Random randy = new Random();

  Player(String newName, int newCardnum, boolean isplayer) {
    Hand = new ArrayList<CardMkr>();
    Books = new ArrayList<CardMkr>();
    Name = newName;
    Score = 0;
    isPlayer = isplayer;
    CardMkr.DealCard(Hand, newCardnum);
  }

  Player(int newCardnum) {
    Hand = new ArrayList<CardMkr>();
    Books = new ArrayList<CardMkr>();
    Score = 0;
    isPlayer = false;
    Name = defaultNames[nameindexcntr];
    nameindexcntr++;
    CardMkr.DealCard(Hand, newCardnum);
  }

  // https://bicyclecards.com/how-to-play/go-fish/
  public static Boolean BookCkr(int plyTurn) {
    for (CardMkr sussy : Main.plyArray.get(plyTurn).Hand) {
      BCKR.clear();
      for (int cntr1 = 0; cntr1 < Main.plyArray.get(plyTurn).Hand.size(); cntr1++) {
        if (sussy.SuiteName.equals(Main.plyArray.get(plyTurn).Hand.get(cntr1).SuiteName)) {
          BCKR.add(cntr1);
        }
      }
      if (BCKR.size() == 4) {
        for (int cntr2 = 0; cntr2 < 4; cntr2++) {
          Main.plyArray.get(plyTurn).Books.add(Main.plyArray.get(plyTurn).Hand.remove(BCKR.get(cntr2) - cntr2));
        }
        return true;
      }
    }
    return false;
  }
  public static boolean RemovePlayer(int plyIndex){
    System.out.println("Player: "+Main.plyArray.remove(plyIndex)+" is out of the game.");
    if (Main.plyArray.get(plyIndex-1).isPlayer == true){
      return true;
    }
    return false;
  }
  public static boolean TakeCard(Player cPly){
    String askType = new String();
    String newStateMnt = new String();
    boolean hadCard = false;
    boolean choseCard = false;
    boolean chosePlayer = false;
    int plyIndex = -1;
    while(!(choseCard || chosePlayer)){
      System.out.print("\nAsk any player for the type of card they have." + Constants.ANSI_ANSWER);
        newStateMnt = Main.scan.nextLine().toUpperCase();
        for (CardMkr Plycard : cPly.Hand){
          if (newStateMnt.contains(Plycard.SuiteName)) {
            askType = Plycard.SuiteName;
            choseCard = true;
            break;  
          }
        }
    }
  }
  // public static void TakeCard(int PlayerAsking) {
  //   String askType = new String("");
  //   String newStateMnt = new String();
  //   int plyIndex = -1;
  //   boolean hadCard = false;
  //   boolean choseCard = false;
  //   boolean chosePlayer = false;
  //   if (PlayerAsking == 0){
  //     while (true) {
  //       System.out.print("\nAsk any player for the type of card they have." + Constants.ANSI_ANSWER);
  //       newStateMnt = Main.scan.nextLine().toUpperCase();
  //       for (CardMkr Plycard : Main.plyArray.get(PlayerAsking).Hand){
  //         if (newStateMnt.contains(Plycard.SuiteName)) {
  //           askType = Plycard.SuiteName;
  //           choseCard = true;
  //           break;
  //         }
  //       }
  //       for (String stype : CardMkr.suiteStrings) {
  //         if (newStateMnt.contains(stype) && (!askType.equals(stype))) {
  //           System.out.println(Constants.ANSI_RED+"\nIm sorry but you can't ask for a card of a type you don't have =(."+Constants.ANSI_RESET);
  //         }
  //       }
  //       for (int cntr1 = 0; cntr1 < Main.plyArray.size(); cntr1++) {
  //         if (newStateMnt.contains(Main.plyArray.get(cntr1).Name.toUpperCase())) {
  //           plyIndex = cntr1;
  //           chosePlayer = true;
  //           break;
  //         }
  //       }
  //       if (plyIndex == -1 || askType == "") {
  //         System.out.println(Constants.ANSI_RED+"\nIm sorry but you input was invalid, try again."+Constants.ANSI_RESET);
  //       }
  //       else if(plyIndex == PlayerAsking){
  //         System.out.println(Constants.ANSI_RED+"\nIm sorry but you can't ask yourself for cards. Please try again."+Constants.ANSI_RESET);
  //         chosePlayer = false;
  //       }
  //       if (choseCard && chosePlayer){
  //         break;
  //       }
  //       //System.out.println(Main.plyArray.get(PlayerAsking).Name+" is Asking for some: "+askType+" from: "+Main.plyArray.get(plyIndex).Name);
  //     }
  //   }
  //   else{
  //     while(true){
  //       //askType = CardMkr.suiteStrings[randy.nextInt(12)];]
  //       askType = Main.plyArray.get(PlayerAsking).Hand.get(randy.nextInt(Main.plyArray.get(PlayerAsking).Hand.size()-1)).SuiteName;
  //       plyIndex = randy.nextInt(Main.players);
  //       //System.out.println(Main.plyArray.get(PlayerAsking).Name+" is Asking for some: "+askType+" from: "+Main.plyArray.get(plyIndex).Name);
  //       if ((!askType.equals(Main.plyArray.get(PlayerAsking).Name) && plyIndex != PlayerAsking)){
  //         break;
  //       }
  //     }
  //     System.out.print(Constants.ANSI_RED+Main.plyArray.get(PlayerAsking).Name + Constants.ANSI_RESET+":");Main.wait(500, false);
  //     System.out.print(" Hmmm, ");Main.wait(1000, false);
  //     System.out.print(Main.plyArray.get(plyIndex).Name);Main.wait(1000, false);
  //     System.out.println(", do you have any "+askType.toLowerCase()+"s?");Main.wait(2500, false);
  //   }
  //   for (int cntr2 = 0; cntr2 < Main.plyArray.get(plyIndex).Hand.size(); cntr2++) {
  //     if (Main.plyArray.get(plyIndex).Hand.get(cntr2).SuiteName.toUpperCase().equals(askType)) {
  //       Main.plyArray.get(PlayerAsking).Hand.add(Main.plyArray.get(plyIndex).Hand.remove(cntr2));
  //       cntr2--;
  //       hadCard = true;
  //     }
  //   }
  //   if (hadCard == false) {
  //     System.out
  //         .println("\n"+Constants.ANSI_RED + Main.plyArray.get(plyIndex).Name + Constants.ANSI_RESET + ": Nah I don't have that card, I guess you have to Go Fish");
  //     System.out.println("\n"+Constants.ANSI_RED +Main.plyArray.get(PlayerAsking).Name+" has to draw a card!"+Constants.ANSI_RESET);
  //     if (CardMkr.DealCard(Main.plyArray.get(PlayerAsking).Hand,1) == false){
  //       System.out.println("\n"+Constants.ANSI_RED +"I guess there are no more cards left too bad then."+Constants.ANSI_RESET);
  //     }
  //   } 
  //   else {
  //     System.out.println(
  //         "\n"+Constants.ANSI_RED + Main.plyArray.get(plyIndex).Name + Constants.ANSI_RESET + ": DANG IT HOW DID YOU KNOW???");
  //   }
  // }
}