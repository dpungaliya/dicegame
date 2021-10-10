package androidsamples.java.dicegames;

import static androidsamples.java.dicegames.GameType.NONE;
import static androidsamples.java.dicegames.GameType.TWO_ALIKE;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModel;



/**
 * A {@link ViewModel} for the gambling game that allows the user to choose a game type, set a wager, and then play.
 */
public class TwoOrMoreViewModel extends ViewModel {

  private static int twoOrMoreBalance;
  private Die d1,d2,d3,d4;
  private GameType mGameType;
  private int mWage;
  public String msg;
  //private List<Integer> mDiceValue;
  private List<Die>mTwoOrMoreDie;
  private int numDie;
  private int betAmt;
  private GameResult mGR;


  /**
   * No argument constructor.
   */
  public TwoOrMoreViewModel() {
    // TODO implement method
    twoOrMoreBalance = 0;
    mTwoOrMoreDie = new ArrayList<>();
    mGameType=NONE;
    mWage=0;
    numDie=0;
  }

  /**
   * Reports the current balance.
   *
   * @return the balance
   */
  public static int balance() {
    // TODO implement method
    return twoOrMoreBalance;
  }

  /**
   * Sets the balance to the given amount.
   *
   * @param balance the given amount
   */
  public static void setBalance(int balance) {
    // TODO implement method
    twoOrMoreBalance=balance;
  }

  /**
   * Reports the current game type as one of the values of the {@code enum} {@link GameType}.
   *
   * @return the current game type
   */
  public GameType gameType() {
    // TODO implement method
    return mGameType;
  }

  /**
   * Sets the current game type to the given value.
   *
   * @param gameType the game type to be set
   */
  public void setGameType(GameType gameType) {
    // TODO implement method
    mGameType=gameType;

  }

  /**
   * Reports the wager amount.
   *
   * @return the wager amount
   */
  public int wager() {
    // TODO implement method
    return mWage;
  }

  /**
   * Sets the wager to the given amount.
   *
   * @param wager the amount to be set
   */
  public void setWager(int wager) {
    // TODO implement method
      mWage=wager;
  }

  /**
   * Reports whether the wager amount is valid for the given game type and current balance.
   * For {@link GameType#TWO_ALIKE}, the balance must be at least twice as much, for {@link GameType#THREE_ALIKE}, at least thrice as much, and for {@link GameType#FOUR_ALIKE}, at least four times as much.
   * The wager must also be more than 0.
   *
   * @return {@code true} iff the wager set is valid
   */
  public boolean isValidWager() {
    //use mWager
  boolean flag=false;
  int minBal=0;

  if(mWage==0)
    return false;

  if(mGameType==TWO_ALIKE){
    if(twoOrMoreBalance>=2*mWage) {
      minBal=2*mWage;
      flag = true;
    }
  }
  else if(mGameType.name()=="THREE_ALIKE"){
    if(twoOrMoreBalance>=3*mWage){
      flag=true;
      minBal=3*mWage;
    }

  }
  else if(mGameType.name()=="FOUR_ALIKE"){
    if(twoOrMoreBalance>=4*mWage){
      flag=true;
      minBal=4*mWage;
    }
  }
  betAmt=minBal;
    return flag;
  }

  /**
   * Returns the current values of all the dice.
   *
   * @return the values of dice
   */
  public List<Integer> diceValues() {
    // TODO implement method
    List<Integer>diceValue=new ArrayList<>();
    for(Die d:mTwoOrMoreDie){
      diceValue.add(d.value());
    }

    return diceValue;

  }

  /**
   * Adds the given {@link Die} to the game.
   *
   * @param d the Die to be added
   */
  public void addDie(Die d) {
    // TODO implement method
    mTwoOrMoreDie.add(d);
  }

  /**
   * Simulates playing the game based on the type and the wager and reports the result as one of the values of the {@code enum} {@link GameResult}.
   *
   * @return result of the current game
   * @throws IllegalStateException if the wager or the game type was not set to a proper value.
   */
  public GameResult play() throws IllegalStateException {
    // TODO implement method

     if(mGameType== NONE){
      throw new IllegalStateException("Game Type not set, can't play!");
    }
      if(!isValidWager()){
      throw new IllegalStateException("Wager not set, can't play!");
    }
      if(mTwoOrMoreDie.size()!=4)
      {
        throw new IllegalStateException("Not enough dice, can't play!");
      }


//    if(mGameType == GameType.TWO_ALIKE){
//      if(maxCount==2){
//        mGR = GameResult.WIN;
//      }
//      else{
//        mGR = GameResult.LOSS;
//      }
//    }
//    else if(mGameType == GameType.THREE_ALIKE){
//      if(maxCount==3){
//        mGR = GameResult.WIN;
//      }
//      else{
//        mGR = GameResult.LOSS;
//      }
//    }
//    else if(mGameType == GameType.FOUR_ALIKE){
//      if(maxCount==4){
//        mGR = GameResult.WIN;
//      }
//      else{
//        mGR = GameResult.LOSS;
//      }
//    }




    if(mGR==GameResult.WIN){
      twoOrMoreBalance += betAmt;
    }
    else{
      twoOrMoreBalance -= betAmt;
    }

    return null;
  }
}
