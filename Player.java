import java.util.ArrayList;
import java.util.Random;

//I changed the atribute hand from being an Array to being an ArrayList is
class Player extends Main{
  public ArrayList<CardMkr> Hand;
  public ArrayList<CardMkr> Books;
  public String Name;
  public int Score;
  public boolean isPlayer;
  public static int nameindexcntr = 0;
  public static boolean ran = false;
  public static ArrayList<Integer> BCKR = new ArrayList<>();
  static Random randy = new Random();
  public static int catchCntr = 0;
  public static Player hscPlayer = new Player("bot", 0, false);
  public static int bWon = 0;
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
    Name = Constants.defaultNames[nameindexcntr];
    nameindexcntr++;
    CardMkr.DealCard(Hand, newCardnum);
  }
  /*
   * Checks if the player as a matching set of 4 cards. If it is the case then it will 
   */
  public static boolean BookCkr(Player ply){
    int hsz = ply.Hand.size();
    ArrayList<Integer> matchKrd = new ArrayList<>();
    for (int cntr1 = 0; cntr1<hsz;cntr1++){
      matchKrd.clear();
      for (int cntr2 = 0; cntr2<hsz;cntr2++){
        if (ply.Hand.get(cntr1).SuiteName.equals(ply.Hand.get(cntr2).SuiteName)){
          matchKrd.add(cntr2);
        }
      }
      if (matchKrd.size() == 4){
        for (int cntr3 = 0; cntr3 <4;cntr3++){
          ply.Books.add(ply.Hand.remove(matchKrd.get(cntr3)-cntr3));
        }
        ply.Score++;
        bWon++;
        for (Player sugoma : plyArray){
          bWon += sugoma.Score;
          if (sugoma.Score> hscPlayer.Score){
            hscPlayer = sugoma;
          }
        }
        if (bWon == 13){
          return true;
        }
      }
    }
    return false;
  }

  public static boolean RemovePlayer(int plyIndex){
    System.out.println("Player: "+plyArray.remove(plyIndex)+" is out of the game.");
    if (plyArray.get(plyIndex-1).isPlayer == true){
      return true;
    }
    return false;
  }
  public static void DisplayPly(int wTime){
    for (int i = 0; i < plyArray.size(); i++){
      if (i == 0){
        wait(wTime,false);
        System.out.print(Constants.ANSI_BLUE + plyArray.get(i).Name + Constants.ANSI_RESET+", ");
      }
      else if (i == plyArray.size()-1){
        wait(wTime,false);
        System.out.print("and "+Constants.ANSI_RED+plyArray.get(i).Name+Constants.ANSI_CLEAR+"\n");
      }
      else{
        wait(wTime,false);
        System.out.print(Constants.ANSI_RED + plyArray.get(i).Name + Constants.ANSI_RESET+", ");
      }
    }
  }
  public static boolean TakeCard(Player cPly, int pTurn){
    String askType = new String();
    int plyIndex = pTurn;
    // The reason why it doen't print sometimes 
    boolean hadCard = false;
    boolean hasCard = false;
    Player askedP;
    String newStateMnt = new String();
    boolean choseCard = false;
    boolean chosePlayer = false;
    /*
     * This runs until an acceptable player and card are chosen.
     */
    if (cPly.isPlayer){
      while(true){
        choseCard = false;
        hasCard = false;
        chosePlayer = false;
        plyIndex = -1;
        if (ran && cPly.isPlayer){
          System.out.println("Your Cards Are:");
          CardMkr.CardDisplay(cPly.Hand);
        }
        ran = true;
        System.out.print("\nAsk any player for the type of card they have." + Constants.ANSI_ANSWER);
        newStateMnt = scan.nextLine().toUpperCase();
        for (CardMkr Plycard : cPly.Hand){
          if (newStateMnt.contains(Plycard.SuiteName)) {
            askType = Plycard.SuiteName;
            choseCard = true;
            break;  
          }
        }
        for (int cntr1 = 0; cntr1 < plyArray.size(); cntr1++) {
          if (newStateMnt.contains(plyArray.get(cntr1).Name.toUpperCase())) {
            plyIndex = cntr1;
            chosePlayer = true;
            break;
          }
        }
        if(plyIndex == pTurn){
          System.out.println(Constants.ANSI_RED+"\nIm sorry but you can't ask yourself for cards. Please try again."+Constants.ANSI_RESET);wait(500, false);
          chosePlayer = false;
        }
        for (String amog : CardMkr.suiteStrings){
          if (newStateMnt.contains(amog) && !choseCard){
            System.out.println(Constants.ANSI_RED+"\nIm sorry but you can't ask for a card of a type you don't have =(."+Constants.ANSI_RESET);wait(500, false);
            hasCard = true;break;
          }
        }
        if (!choseCard && !hasCard){
          System.out.println(Constants.ANSI_RED+"\nIm sorry but you didn't choose a valid card. =("+Constants.ANSI_RESET);wait(500, false);
        }
        if (!chosePlayer && plyIndex != pTurn){
          System.out.println(Constants.ANSI_RED+"\nIm sorry but you didn't choose a valid player. =("+Constants.ANSI_RESET);wait(500, false);
        }
        if (!(choseCard && chosePlayer)){
          wait(1000, true);
        }
        else{
          wait(1000, false);
          break;
        }
      }
      askedP = plyArray.get(plyIndex);
    }
    else{
      
      while(plyIndex == pTurn || !hasCard){
        if (plyIndex == pTurn){
          plyIndex = randy.nextInt(plyArray.size());
        }
        if (!hasCard){
          askType = CardMkr.suiteStrings[randy.nextInt(13)];
        }
        for (CardMkr mog : cPly.Hand){
          if (askType.equals(mog.SuiteName)){
            hasCard = true;break;
          }
        }
      }
      askedP = plyArray.get(plyIndex);
      System.out.print(Constants.ANSI_RED+cPly.Name + Constants.ANSI_RESET+":");wait(500, false);
      System.out.print(" Hmmm, ");wait(1000, false);
      System.out.print(askedP.Name);wait(1000, false);
      System.out.println(", do you have any "+askType.toLowerCase()+"s?");wait(2500, false);
    }
    for (int cntr2 = 0; cntr2<askedP.Hand.size(); cntr2++){
      if (askedP.Hand.get(cntr2).SuiteName.equals(askType)){
        hadCard = true;break;
      }
    }
    if (hadCard){
      if (askedP.isPlayer){
        System.out.print(Constants.ANSI_BLUE+askedP.Name+Constants.ANSI_RESET+": ");
      }
      else{
        System.out.print(Constants.ANSI_RED+askedP.Name+Constants.ANSI_RESET+": ");
      }
      System.out.println(Constants.THADCARD[randy.nextInt(5)]);
      for (int cntr2 = 0; cntr2<askedP.Hand.size(); cntr2++){
        if (askedP.Hand.get(cntr2).SuiteName.equals(askType)){
          cPly.Hand.add(askedP.Hand.remove(cntr2));cntr2--;
        }
      }
      if (askedP.Hand.size()==0 && !cRemaining){
        if (RemovePlayer(plyIndex)== true){
          return true;
        }
      }
      catchCntr++;
      if (askedP.isPlayer){
        System.out.println(Constants.ANSI_GREEN+"\nYou made a catch "+Constants.ANSI_BLUE+cPly.Name+Constants.ANSI_GREEN+"!\nFish Again!"+Constants.ANSI_RESET);
      }
      else{
        System.out.println(Constants.ANSI_GREEN+"\nYou made a catch "+Constants.ANSI_RED+cPly.Name+Constants.ANSI_GREEN+"!\nFish Again!"+Constants.ANSI_RESET);
      }
      wait(2000, true);
      TakeCard(cPly, pTurn);
    }
    else{
      if (askedP.isPlayer){
        System.out.println(Constants.ANSI_BLUE+askedP.Name+Constants.ANSI_RESET+": "+Constants.FHADCARD[randy.nextInt(5)]);
        wait(1000, false);
        if (catchCntr == 0 && Main.cRemaining){
          System.out.println(Constants.ANSI_BLUE+askedP.Name+Constants.ANSI_RESET+": Go Fish! Go draw a card.");
          CardMkr.DealCard(cPly.Hand, 1);
        }
        else if (catchCntr == 0 && !Main.cRemaining){
          System.out.println(Constants.ANSI_BLUE+askedP.Name+Constants.ANSI_RESET+": Go Fish! Oh wait theres no more cards left");
        }
      }
      else{
        System.out.println(Constants.ANSI_RED+askedP.Name+Constants.ANSI_RESET+": "+Constants.FHADCARD[randy.nextInt(5)]);
        wait(1000, false);
        if (catchCntr == 0 && Main.cRemaining){
          System.out.println(Constants.ANSI_RED+askedP.Name+Constants.ANSI_RESET+": Go Fish! Go draw a card.");
          CardMkr.DealCard(cPly.Hand, 1);
        }
        else if (catchCntr == 0 && !cRemaining){
          System.out.println(Constants.ANSI_RED+askedP.Name+Constants.ANSI_RESET+": Go Fish! Oh wait theres no more cards left");
        }
      }
    }
    return false;
  }
}