package androidsamples.java.dicegames;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class WalletActivity extends AppCompatActivity {
    private static final int TWO_OR_MORE_GAME_REQUEST_CODE = 0;

//  private static final int WIN_VALUE = 6;
//  private static final int INCREMENT = 5;
//
//  private int mBalance;
//  private Die mDie;

  private TextView mTxtBalance;
  private Button mBtnDie;

  private WalletViewModel mWalletVM;


  public static final String TAG="WalletActivity";

  static final String KEY_BALANCE="KEY_BALANCE";
//  private static final String KEY_DIE_VALUE="KEY_DIE_VALUE";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Log.d(TAG, "onCreate");
      setContentView(R.layout.activity_wallet);

      //mDie = new Die6();
      mTxtBalance = findViewById(R.id.txt_balance);
      mBtnDie = findViewById(R.id.btn_die);

      mWalletVM=new ViewModelProvider(this).get(WalletViewModel.class);
      updateUI();
//
//      if (savedInstanceState != null) {
//        mBalance = savedInstanceState.getInt(KEY_BALANCE, 0);
//        int dieValue = savedInstanceState.getInt(KEY_DIE_VALUE, 0);
//        mTxtBalance.setText(Integer.toString(mBalance));
//        mBtnDie.setText(Integer.toString(dieValue));
//        Log.d(TAG, "Restored: balance = " + mBalance + ", die = " + dieValue);
//      }

      mBtnDie.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              //roll the die
              mWalletVM.rollDie();
              updateUI();

//              mDie.roll();
//              Log.d(TAG, "Die Roll= " + mDie.value());
//              //add coins if win_value is rolled
//              if (mDie.value() == WIN_VALUE) {
//                  mBalance += INCREMENT;
//                  Log.d(TAG, "New Balance= " + mBalance);
//              }
//              //update the UI
//              updateUI();
//          }
//          private void updateUI() {
//              mBtnDie.setText(Integer.toString(mDie.value()));
//              mTxtBalance.setText(Integer.toString(mBalance));
//          }
          }
      });

  }

    private void updateUI() {
        mTxtBalance.setText(Integer.toString(mWalletVM.balance()));
        mBtnDie.setText(Integer.toString(mWalletVM.dieValue()));
    }
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Log.d(TAG, "onSaveInstanceState");
//        outState.putInt(KEY_BALANCE, mBalance);
//        outState.putInt(KEY_DIE_VALUE, mDie.value());
//        Log.d(TAG, "Saved: balance = " + mBalance + ", die = " + mDie.value());
//    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onStart");
      }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume");
      }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"onPause");
      }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"onStop");
      }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy");
      }

    public void launchTwoOrMore(View view) {
      Intent intent = new Intent(this, TwoOrMoreActivity.class);
      intent.putExtra(KEY_BALANCE, mWalletVM.balance());
      startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
      super.onActivityResult(requestCode,resultCode,data);
      Log.d(TAG,"onActivityResult");
      if(requestCode==TWO_OR_MORE_GAME_REQUEST_CODE && (resultCode==RESULT_OK || resultCode==RESULT_CANCELED)){
          if(data!=null){
              int balance=data.getIntExtra(TwoOrMoreActivity.KEY_BALANCE_RETURN,0);
              Log.d(TAG,"Balance after result: "+balance);
              mWalletVM.setBalance(balance);
              updateUI();
          }
      }
    }
}
