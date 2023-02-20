import java.util.Random;
import java.util.ArrayList;

class CardMkr {
  public String SuiteType;
  public String SuiteName;
  public String SuiteValue;
  public int SuiteNval;
  public String SuiteGraphicLy1;
  public String SuiteGraphicLy2;
  public String SuiteGraphicLy3;
  public String SuiteGraphicLy4;
  String[] suites = { "♥", "♠", "♦", "♣" };
  public static String[] suiteStrings = { "ACE","TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN",
    "JACK", "QUEEN", "KING"};
  String[] suiteNums = {"A ","2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10", "J ", "Q ", "K "};
  int[] sweets = {1,2,3,4,5,6,7,8,9,10,11,12,13};
  CardMkr() {
    Random rand = new Random();
    int randsuite = rand.nextInt(4);
    int randval = rand.nextInt(13);
    SuiteType = suites[randsuite];
    SuiteName = suiteStrings[randval];
    SuiteValue = suiteNums[randval];
    SuiteNval = sweets[randval];
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
  // public static void CardSorter(ArrayList<CardMkr> srtDeck){
  //   int lowest, bestplace;
  //   bestplace = 0;
  //   for (int sorted = 0; sorted<srtDeck.size();sorted++){
  //     lowest = srtDeck.get(0).SuiteNval;
  //     for (int prog = sorted+1; prog<srtDeck.size();){
  //       if (lowest>srtDeck.get(prog).SuiteNval){
  //         lowest = srtDeck.get(prog).SuiteNval;
  //       }
  //     }
  //     for (int x = 0;x<sorted+1;x++){
  //       if (srtDeck.get(x).SuiteNval>srtDeck.get(lowest).SuiteNval){
  //         bestplace = x;
  //       }
  //     }
  //     srtDeck.add(bestplace, srtDeck.remove(lowest));
  //   }
  // }
  public static void CardSorter(ArrayList<CardMkr> srtDeck){
    int smolindex = 0;
    for (int cntr1 = 0; cntr1<srtDeck.size(); cntr1++){
      for (int cntr2 = cntr1+1; cntr2<srtDeck.size(); cntr2++){
        if (srtDeck.get(cntr2).SuiteNval<srtDeck.get(smolindex).SuiteNval){
          smolindex = cntr2;
        }
      }
      
    }
  }
  public static void DealCard(ArrayList<CardMkr> srtDeck, int CardReturnNum) {
    for (int cntr4 = 0; cntr4 < CardReturnNum; cntr4++) {
      srtDeck.add(Main.stock.remove(0));
      if (Main.stock.size() == 0){
        System.out.println("THERE ARE NO MORE CARDS LEFT");
        Main.cRemaining = false;
      }
    }
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