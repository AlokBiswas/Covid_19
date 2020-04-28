package com.alokbiswas.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.alokbiswas.covid_19.api.ApiClient;
import com.alokbiswas.covid_19.api.ApiInterface;
import com.alokbiswas.covid_19.model.Mundial;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String Tag = "alok";
    private ProgressBar pb;
    PieChart chart;
    ArrayList<Integer> valores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = findViewById(R.id.progressBar);
        chart = findViewById(R.id.pieChart);

        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);
        chart.setDragDecelerationFrictionCoef(0.95f);
        chart.setCenterText("COVID_19");
        chart.setCenterTextSize(20);
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);
        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);
        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);
        chart.setDrawCenterText(true);
        chart.setRotationAngle(0);



        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);
        chart.animateY(2400, Easing.EaseInOutQuad);

        Legend leg = chart.getLegend();
        leg.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        leg.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        leg.setOrientation(Legend.LegendOrientation.VERTICAL);
        leg.setDrawInside(false);
        leg.setXEntrySpace(8f);
        leg.setYEntrySpace(0f);
        leg.setYOffset(0f);

        chart.setEntryLabelColor(Color.BLUE);
        chart.setEntryLabelTextSize(14);


        obtenerdatos();


    }

    private void obtenerdatos() {
        pb.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mundial> call = apiInterface.getInfoMundial();

        call.enqueue(new Callback<Mundial>() {
            @Override
            public void onResponse(Call<Mundial> call, Response<Mundial> response) {
                Mundial datos = response.body();
                Log.d(Tag, "response value" + response);
                assert datos != null;
                valores.add(Integer.parseInt(datos.getRecovered()));
                valores.add(Integer.parseInt(datos.getCases()));
                valores.add(Integer.parseInt(datos.getDeaths()));

                enviorDatos();


            }

            @Override
            public void onFailure(Call<Mundial> call, Throwable t) {
                pb.setVisibility(View.GONE);
                Log.d("error", t.getMessage());

            }
        });
    }

    private void enviorDatos() {
        ArrayList<PieEntry> arrayList = new ArrayList<>();
        // final String[] types = new String[]{"Infections,Recoveries,Deaths"};
        ArrayList<String> types = new ArrayList<String>();
        types.add("Infections");
        types.add("Recoveries");
        types.add("Deaths");

        for (int i = 0; i < types.size(); i++) {
            arrayList.add(new PieEntry(valores.get(i), types.get(i)));
        }

        PieDataSet pieDataSet = new PieDataSet(arrayList, "chart");
        pieDataSet.setDrawIcons(false);
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setIconsOffset(new MPPointF(0, 10));
        pieDataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        pieDataSet.setColors(colors);


        PieData data = new PieData(pieDataSet);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(14f);


        chart.setData(data);
        chart.highlightValue(null);
        chart.invalidate();



    }


}

