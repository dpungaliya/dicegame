package androidsamples.java.dicegames;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class WalletActivity extends AppCompatActivity {
    private static final int TWO_OR_MORE_GAME_REQUEST_CODE = 0;

  private Die mDie;
  private TextView mTxtBalance;
  private Button mBtnDie;

  private WalletViewModel mWalletVM;

  public static final String TAG="WalletActivity";

  static final String KEY_BALANCE="KEY_BALANCE";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Log.d(TAG, "onCreate");
      setContentView(R.layout.activity_wallet);

      mTxtBalance = findViewById(R.id.txt_balance);
      mBtnDie = findViewById(R.id.btn_die);

      mWalletVM=new ViewModelProvider(this).get(WalletViewModel.class);
      mDie = new Die6();
      mWalletVM.setDie(mDie);
      updateUI();


      mBtnDie.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              //roll the die
              try{
              mWalletVM.rollDie();
              }
              catch(IllegalStateException e){
                  String toastMsg = e.getMessage();
                  int duration = Toast.LENGTH_SHORT;
                  Toast toast = Toast.makeText(WalletActivity.this, toastMsg, duration);
                  toast.show();
              }

              updateUI();

          }
      });


  }

    private void updateUI() {
      try {
          mTxtBalance.setText(Integer.toString(mWalletVM.balance()));
          mBtnDie.setText(Integer.toString(mWalletVM.dieValue()));
      }
      catch(Exception e){
          mTxtBalance.setText(0);
        }
    }

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
      startActivityForResult(intent,TWO_OR_MORE_GAME_REQUEST_CODE);
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
