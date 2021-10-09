package androidsamples.java.dicegames;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {

  private static final String TAG="WalletViewModel";
  private static final int WIN_VALUE = 6;
  private static final int INCREMENT = 5;

  private int mBalance;
  private Die mDie;

  /**
   * The no argument constructor.
   */

  public WalletViewModel() {
    // TODO implement method
    mBalance=0;
    mDie=new Die6();
  }

  /**
   * Reports the current balance.
   *
   * @return the balance
   */

  public int balance() {
    // TODO implement method
    return mBalance;
  }

  /**
   * Sets the balance to the given amount.
   *
   * @param amount the new balance
   */
  public void setBalance(int amount) {
    // TODO implement method
    amount=mBalance;
  }

  /**
   * Rolls the {@link Die} in the wallet.
   */
  public void rollDie() {
    // TODO implement method
    mDie.roll();
    Log.d(TAG, "Die Roll= " + mDie.value());

    //add coins if win_value is rolled
    if (mDie.value() == WIN_VALUE) {
      mBalance += INCREMENT;
      Log.d(TAG, "New Balance= " + mBalance);
    }
  }

  @Override
  protected void onCleared(){
    super.onCleared();
    Log.d(TAG,"onCleared");
  }


  /**
   * Reports the current value of the {@link Die}.
   *
   * @return current value of the die
   */
  public int dieValue() {
    // TODO implement method
    return mDie.value();
  }

  /**
   * Sets the increment value for earning in the wallet.
   *
   * @param increment amount to add to the balance
   */
  public void setIncrement(int increment) {
    // TODO implement method
  }

  /**
   * Sets the value which when rolled earns the increment.
   *
   * @param winValue value to be set
   */
  public void setWinValue(int winValue) {
    // TODO implement method
  }

  /**
   * Sets the {@link Die} to be used in this wallet.
   *
   * @param d the Die to use
   */
  public void setDie(Die d) {
    // TODO implement method
  }
}
