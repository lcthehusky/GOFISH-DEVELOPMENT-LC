import java.util.Random;
import java.util.ArrayList;

class CardMkr {
  public String SuiteType;
  public String SuiteName;
  public String SuiteValue;
  public String SuiteGraphicLy1;
  public String SuiteGraphicLy2;
  public String SuiteGraphicLy3;
  public String SuiteGraphicLy4;
  String[] suites = { "♥", "♠", "♦", "♣" };
  public static String[] suiteStrings = { "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN",
    "ACE", "JACK", "QUEEN", "KING" };
  String[] suiteNums = {"2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10", "J ", "Q ", "K ","A "};
  public static int CardAssignmentCntr = 0;

  CardMkr() {
    Random rand = new Random();
    int randsuite = rand.nextInt(4);
    int randval = rand.nextInt(13);
    SuiteType = suites[randsuite];
    SuiteName = suiteStrings[randval];
    SuiteValue = suiteNums[randval];
    SuiteGraphicLy1 = "╔═══╗";
    SuiteGraphicLy2 = "║ " + SuiteType + " ║";
    SuiteGraphicLy3 = "║ " + SuiteValue + "║";
    SuiteGraphicLy4 = "╚═══╝";
  }
  public static ArrayList<CardMkr> shfflr(ArrayList<CardMkr> shfflDeck) {
    while (true) {
      int shfflcounter = 0;
      for (int cntr2 = 0; cntr2 < 52; cntr2++) {
        for (int cntr3 = 0; cntr3 < 52; cntr3++) {
          if ((shfflDeck.get(cntr2).SuiteValue).equals(shfflDeck.get(cntr3).SuiteValue) & cntr2 != cntr3
              & (shfflDeck.get(cntr2).SuiteType).equals(shfflDeck.get(cntr3).SuiteType)) {
            shfflDeck.set(cntr3,new CardMkr());
          } else {
            shfflcounter++;
          }
        }
      }
      if (shfflcounter == 2704) {
        break;
      }
    }
    return shfflDeck;
  }
  public static void CardSorter(ArrayList<CardMkr> srtDeck){
    int i = srtDeck.size();
    int smaldex;
    int currdex;
    for (int cntr1 = 0; cntr1<i;cntr1++){
      smaldex = cntr1;
    }
  }
  public static boolean DealCard(ArrayList<CardMkr> srtDeck, int CardReturnNum) {
    if (Main.stock.size() == 0){
      Main.cRemaining = false;
      return false;
    }
    for (int cntr4 = 0; cntr4 < CardReturnNum; cntr4++) {
      srtDeck.add(Main.stock.remove(CardAssignmentCntr));
      if (Main.stock.size() == 0){
        System.out.println("Could only add "+ cntr4 + " cards. There are no more cards left.");
        return false;
      }
    }
    return true;
  }
  public static void CardDisplay(ArrayList<CardMkr> dspDeck) {
    String TempLy1 = new String();
    String TempLy2 = new String();
    String TempLy3 = new String();
    String TempLy4 = new String();
    int cardcntr = 0;
    if (dspDeck.size() == 0){
      return;
    }
    if (dspDeck.size() / 4 >= 1) {
      for (int cntr1 = 0; cntr1 < (dspDeck.size() / 4); cntr1++) {
        for (int cntr2 = 0; cntr2 < 4; cntr2++) {
          TempLy1 += (dspDeck.get(cardcntr).SuiteGraphicLy1);
          TempLy2 += (dspDeck.get(cardcntr).SuiteGraphicLy2);
          TempLy3 += (dspDeck.get(cardcntr).SuiteGraphicLy3);
          TempLy4 += (dspDeck.get(cardcntr).SuiteGraphicLy4);
          cardcntr++;
        }
        System.out.print(TempLy1 + "\n" + TempLy2 + "\n" + TempLy3 + "\n" + TempLy4 + "\n");
        TempLy1 = "";
        TempLy2 = "";
        TempLy3 = "";
        TempLy4 = "";
      }
      if (dspDeck.size() % 4 != 0) {
        for (int cntr3 = 0; cntr3 < dspDeck.size() % 4; cntr3++) {
          TempLy1 += (dspDeck.get(cardcntr).SuiteGraphicLy1);
          TempLy2 += (dspDeck.get(cardcntr).SuiteGraphicLy2);
          TempLy3 += (dspDeck.get(cardcntr).SuiteGraphicLy3);
          TempLy4 += (dspDeck.get(cardcntr).SuiteGraphicLy4);
          cardcntr++;
        }
        System.out.print(TempLy1 + "\n" + TempLy2 + "\n" + TempLy3 + "\n" + TempLy4 + "\n");
        TempLy1 = "";
        TempLy2 = "";
        TempLy3 = "";
        TempLy4 = "";
      }
    } 
    else {
      for (int cntr1 = 0; cntr1 < dspDeck.size() % 4; cntr1++) {
        TempLy1 += (dspDeck.get(cardcntr).SuiteGraphicLy1);
        TempLy2 += (dspDeck.get(cardcntr).SuiteGraphicLy2);
        TempLy3 += (dspDeck.get(cardcntr).SuiteGraphicLy3);
        TempLy4 += (dspDeck.get(cardcntr).SuiteGraphicLy4);
        cardcntr++;
      }
      System.out.println(TempLy1 + "\n" + TempLy2 + "\n" + TempLy3 + "\n" + TempLy4 + "\n");
      TempLy1 = "";
      TempLy2 = "";
      TempLy3 = "";
      TempLy4 = "";
    }
  }
}