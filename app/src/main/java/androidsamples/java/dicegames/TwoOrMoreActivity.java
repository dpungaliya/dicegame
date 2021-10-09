package androidsamples.java.dicegames;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.io.Serializable;
import java.util.List;

public class TwoOrMoreActivity extends AppCompatActivity {

  private static final String TAG="TwoOrMoreActivity";
  static final String KEY_BALANCE_RETURN="KEY_BALANCE_RETURN";
  private TwoOrMoreViewModel mTwoOrMoreVM;
  private TextView mTwoOrMoreBalance;
  private TextView mBtnDie1;
  private TextView mBtnDie2;
  private TextView mBtnDie3;
  private TextView mBtnDie4;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two_or_more);

    mTwoOrMoreBalance=findViewById(R.id.txt_balance_twoormore);
    mBtnDie1 = findViewById(R.id.txt_die1);
    mBtnDie2 = findViewById(R.id.txt_die2);
    mBtnDie3 = findViewById(R.id.txt_die3);
    mBtnDie4 = findViewById(R.id.txt_die4);

    mTwoOrMoreVM=new ViewModelProvider(this).get(TwoOrMoreViewModel.class);

    if(savedInstanceState==null){
      int balance=getIntent().getIntExtra(WalletActivity.KEY_BALANCE,0);
      TwoOrMoreViewModel.setBalance(balance);
      Log.d(TAG,"Initial Balance: "+balance);
    }

  }

  public void returnToWallet(){
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
//    if(intent.resolveActivity(getPackageManager())!=null) {
//      Log.d(TAG,"Launching Web Browser");
//      startActivity(intent);
//    }
//    else{
//      Log.d(TAG,"Cannot launch Web Browser");
//    }
  }
}