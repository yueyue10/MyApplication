package encdata.com.smart_refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.header.DropboxHeader;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.FunGameBattleCityHeader;
import com.scwang.smartrefresh.header.FunGameHitBlockHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.header.StoreHouseHeader;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;

/**
 * Created by zhaoyuejun on 2018/9/27.
 */

public class SmartRfActivity extends AppCompatActivity {

    RefreshLayout refreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smartrf);
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_normal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_normal_1:
                //设置 Header 为 贝塞尔雷达 样式
                refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
                //设置 Footer 为 球脉冲 样式
                refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
                return true;
            case R.id.option_normal_2:
                refreshLayout.setRefreshHeader(new PhoenixHeader(this));
                return true;
            case R.id.option_normal_3:
                refreshLayout.setRefreshHeader(new TaurusHeader(this));
                return true;
            case R.id.option_normal_4:
                refreshLayout.setRefreshHeader(new DeliveryHeader(this));
                return true;
            case R.id.option_normal_5:
                refreshLayout.setRefreshHeader(new DropboxHeader(this));
                return true;
            case R.id.option_normal_6:
                refreshLayout.setRefreshHeader(new BezierCircleHeader(this));
                return true;
            case R.id.option_normal_7:
                refreshLayout.setRefreshHeader(new FlyRefreshHeader(this));
                return true;
            case R.id.option_normal_8:
                refreshLayout.setRefreshHeader(new FunGameBattleCityHeader(this));
                return true;
            case R.id.option_normal_9:
                refreshLayout.setRefreshHeader(new FunGameHitBlockHeader(this));
                return true;
            case R.id.option_normal_10:
                refreshLayout.setRefreshHeader(new WaveSwipeHeader(this));
                return true;
            case R.id.option_normal_11:
                refreshLayout.setRefreshHeader(new MaterialHeader(this));
                return true;
            case R.id.option_normal_12:
                refreshLayout.setRefreshHeader(new StoreHouseHeader(this));
                return true;
            case R.id.option_normal_13:
                refreshLayout.setRefreshHeader(new WaterDropHeader(this));
                return true; case R.id.option_normal_14:
                refreshLayout.setRefreshHeader(new LottieHeader(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
