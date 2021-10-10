package androidsamples.java.dicegames;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;

//WalletActivity|WalletViewModel|TwoOrMoreActivity|TwiOrMoreViewModel

public class TwoOrMoreActivity extends AppCompatActivity {

  private static final String TAG="TwoOrMoreActivity";
  static final String KEY_BALANCE_RETURN="KEY_BALANCE_RETURN";
  private TwoOrMoreViewModel mTwoOrMoreVM;

  private TextView mTwoOrMoreBalance;
  private EditText editTextWager;
  private TextView mBtnDie1;
  private TextView mBtnDie2;
  private TextView mBtnDie3;
  private TextView mBtnDie4;
  private List<Integer> mDieVal;
  private Button mBtnGo;
  private Button mBtnInfo;
  private Button mBtnBack;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two_or_more);

    mTwoOrMoreVM=new ViewModelProvider(this).get(TwoOrMoreViewModel.class);
    mTwoOrMoreBalance=findViewById(R.id.txt_balance_twoormore);
    mBtnGo=findViewById(R.id.btn_go);
    mBtnInfo = findViewById(R.id.btn_info);
    mBtnBack = findViewById(R.id.btn_back);

    Die d1=new Die6();
    Die d2=new Die6();
    Die d3=new Die6();
    Die d4=new Die6();

    mTwoOrMoreVM.addDie(d1);
    mTwoOrMoreVM.addDie(d2);
    mTwoOrMoreVM.addDie(d3);
    mTwoOrMoreVM.addDie(d4);

    mBtnDie1 = findViewById(R.id.txt_die1);
    mBtnDie2 = findViewById(R.id.txt_die2);
    mBtnDie3 = findViewById(R.id.txt_die3);
    mBtnDie4 = findViewById(R.id.txt_die4);

    editTextWager=findViewById(R.id.edit_wager);

    if(savedInstanceState==null){
      int balance=getIntent().getIntExtra(WalletActivity.KEY_BALANCE,0);
      mTwoOrMoreVM.setBalance(balance);
      mTwoOrMoreBalance.setText(Integer.toString(balance));
      Log.d(TAG,"Initial Balance: "+balance);
    }

    //updateUI();


    mBtnGo.setOnClickListener(v->{

      String wage=editTextWager.getText().toString();
      String toastMsg;
      int duration = Toast.LENGTH_SHORT;

      try{
        int wager=Integer.parseInt(wage);
        mTwoOrMoreVM.setWager(wager);
      }
      catch(NumberFormatException e){
        return;
      }

      int wager=Integer.parseInt(wage);
      mTwoOrMoreVM.setWager(wager);


      try{
        GameResult gr = mTwoOrMoreVM.play();
        Log.d(TAG,"Result");
        toastMsg = (gr==GameResult.WIN) ? "Congratulations! You win!" : "Hard luck! You lost.";
      }
      catch(IllegalStateException e){
        toastMsg=e.getMessage();
      }

       displayToast(duration,toastMsg);

    });

  }

   private void displayToast(int duration, String toastMsg) {
    Toast toast = Toast.makeText(TwoOrMoreActivity.this, toastMsg, duration);
    toast.show();
  }

  public void updateUI(){
//    mDieVal=mTwoOrMoreVM.diceValues();
//    mBtnDie1.setText(Integer.toString(mDieVal.get(0)));
//    mBtnDie2.setText(Integer.toString(mDieVal.get(1)));
//    mBtnDie3.setText(Integer.toString(mDieVal.get(2)));
//    mBtnDie4.setText(Integer.toString(mDieVal.get(3)));
//    mTwoOrMoreBalance.setText(Integer.toString(mTwoOrMoreVM.balance()));

  }

  public void onRadioButtonClicked(View view) {

    boolean checked = ((RadioButton) view).isChecked();


    if(view.getId()==R.id.btn_2alike){
      if(checked){
        mTwoOrMoreVM.setGameType(GameType.TWO_ALIKE);
      }
    }
    else if(view.getId()==R.id.btn_3alike){
      if(checked){
        mTwoOrMoreVM.setGameType(GameType.THREE_ALIKE);
      }
    }
    else if(view.getId()==R.id.btn_4alike){
      if(checked){
        mTwoOrMoreVM.setGameType(GameType.FOUR_ALIKE);
      }
    }
  }

  public void returnToWallet(View view){
    Intent resultIntent=new Intent();
    resultIntent.putExtra(KEY_BALANCE_RETURN,TwoOrMoreViewModel.balance());
    setResult(RESULT_OK,resultIntent);
    finish();
  }


  @Override
  public void onBackPressed(){
    Log.d(TAG,"onBackPressed");
    Intent resultIntent=new Intent();
    resultIntent.putExtra(KEY_BALANCE_RETURN,TwoOrMoreViewModel.balance());
    setResult(RESULT_CANCELED,resultIntent);
    super.onBackPressed();
  }

//  public void launchWalletActivity(View view) {
//      Intent intent = new Intent(this, WalletActivity.class);
//      intent.putExtra(KEY_BALANCE_RETURN, String.valueOf(mTwoOrMoreVM));
//      startActivity(intent);
//    }

  public void showGameInfo(View view) {
    String url=getResources().getString(R.string.two_or_more_activity_url);
    Uri twoOrMoreWiki=Uri.parse(url);
    Intent intent = new Intent(Intent.ACTION_VIEW,twoOrMoreWiki);
    PackageManager pm = getPackageManager();
    boolean isIntentSafe = false;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
      List<ResolveInfo> activities = pm.queryIntentActivities(intent, PackageManager.MATCH_ALL);
      isIntentSafe = activities.size() > 0;
      Log.d(TAG, "Build version >= M; isIntentSafe = " + isIntentSafe);
    }
    else {
      isIntentSafe = intent.resolveActivity(pm) != null;
      Log.d(TAG, "Build version < M; isIntentSafe = " + isIntentSafe);
    }

    if (isIntentSafe) {
    Log.d(TAG, "Launching web browser");
    startActivity(intent);
    }
    else {
    Log.d(TAG, "Cannot launch web browser");
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
}