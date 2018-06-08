package mx.hackaton.nutriagro.nutripob;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class Ovino extends AppCompatActivity {
    private BarChart barChart;
    //Eje X
    private String[]alimentos=new String[]{"Proteína","Humedad","Calcio","M. seca", "Fósforo", "Fibra", "Grasa", "Cenizas"};
    //Eje Y
    private float[]porcentaje=new float[]{31.42f,15.61f,4.45f,65.39f,0.16f,2.22f,0.78f,15.26f};

    //Colores
    private int[]colors=new int[]{Color.rgb(254,234,0), Color.rgb(118,255,3),
            Color.rgb(0,230,118), Color.rgb(0,229,255),
            Color.rgb(41,121,255), Color.rgb(101,31,255),
            Color.rgb(245,0,87), Color.rgb(255,23,68)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovino);
        barChart=(BarChart)findViewById(R.id.barChart_ovino);
        createChar();
    }

    private Chart getSameChart(Chart chart, String description, int textColor, int background, int animateY, boolean leyenda){
        chart.getDescription().setText(description);
        chart.getDescription().setTextColor(textColor);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(Color.rgb(255,255,255));
        chart.animateY(animateY);

        //Validar porque la grafica de radar y dispersion tiene dos datos especificos y la leyenda se crea de acuerdo a esos datos.
        if(leyenda)
            legend(chart);
        return chart;
    }

    private void legend(Chart chart) {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries = new ArrayList<>();
        for (int i = 0; i < alimentos.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors[i];
            entry.label = alimentos[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }

    //Eje horizontal o eje X
    private void axisX(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(alimentos));
    }
    //Eje Vertical o eje Y lado izquierdo
    private void axisLeft(YAxis axis){
        axis.setSpaceTop(31);
        axis.setAxisMinimum(0);
        axis.setGranularity(10);
    }
    //Eje Vertical o eje Y lado Derecho
    private void axisRight(YAxis axis){
        axis.setEnabled(false);
    }

    public void createChar(){
        barChart=(BarChart)getSameChart(barChart,"",Color.RED,Color.CYAN,3000,true);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(true);
        barChart.setData(getBarData());
        barChart.invalidate();
        barChart.getLegend().setEnabled(false);

        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());
    }

    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(10);
        return dataSet;
    }
    public BarData getBarData() {
        BarDataSet barDataSet=(BarDataSet)getData(new BarDataSet(getBarEntries(),""));
        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData=new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }

    private ArrayList<BarEntry>getBarEntries(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < porcentaje.length; i++)
            entries.add(new BarEntry(i, porcentaje[i]));
        return entries;
    }
}
