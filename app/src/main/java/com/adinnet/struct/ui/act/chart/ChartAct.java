package com.adinnet.struct.ui.act.chart;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.adinnet.struct.R;
import com.adinnet.struct.ui.act.widget.WaveView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.mikephil.charting.charts.LineChart;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.adinnet.struct.tool.Constants.ACTIVITY_URL_CHART;

/**
 * 图表
 */
@Route(path = ACTIVITY_URL_CHART)
public class ChartAct extends RxFragmentActivity {

    @BindView(R.id.lineChart)
    LineChart lineChart;
    @BindView(R.id.waveView)
    WaveView waveView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_chart);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        waveView.begainAnimation();
    }



}
