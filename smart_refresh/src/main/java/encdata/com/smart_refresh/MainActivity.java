package encdata.com.smart_refresh;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startToSmartRefresh(View view) {
        startActivity(new Intent(this, SmartRfActivity.class));
    }

    public void startToLottie(View view) {
        startActivity(new Intent(this, LottieActivity.class));
    }
}
