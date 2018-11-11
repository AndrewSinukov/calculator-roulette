package as.calculatorrouletteo.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import as.calculatorrouletteo.Constants;
import as.calculatorrouletteo.R;
import as.calculatorrouletteo.adapters.RouletteMainAdapter;
import as.calculatorrouletteo.models.Roulette;

public class MainActivity extends Activity implements RouletteMainAdapter.ClickListener, View.OnClickListener {

    private static final int RESULT_LOAD_FIELD = 1;
    private static final int RESULT_LOAD_RATE = 2;
    ArrayList<Roulette> products = new ArrayList<>();
    private RouletteMainAdapter rouletteAdapter;
    private Button btnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        View header = getLayoutInflater().inflate(R.layout.header_main, null);
        fillData();

        ListView listView = (ListView) findViewById(R.id.listViewR);
        listView.addHeaderView(header, null, true);

        btnCount = (Button) listView.findViewById(R.id.btnCount);
        btnCount.setOnClickListener(new View.OnClickListener() {
            int counter = 0;

            @Override
            public void onClick(View v) {
                counter++;
                btnCount.setText(String.valueOf(counter));
                for (int i = 0; i < products.size(); i++) {
                    rateCalculation(i);
                }
                rouletteAdapter.notifyDataSetChanged();
            }
        });
        rouletteAdapter = new RouletteMainAdapter(getApplicationContext(), products);
        rouletteAdapter.setClickListener(this);
        listView.setAdapter(rouletteAdapter);
    }


    public void rateCalculation(int i) {
        int curValCount = products.get(i).getCountBtn();
        if (products.get(i).getFieldR() >= 0 && products.get(i).getRateR() >= 0) {

            if (curValCount > 0) {
                if (curValCount % 18 != 0 || products.get(i).getCngVal()) {
                    products.get(i).setCountBtn(products.get(i).getCountBtn() + 1);
                    if (curValCount % 18 == 17) {
                        products.get(i).setCngVal(false);
                    }
                }
                if (curValCount % 18 == 0 && !products.get(i).getCngVal()) {
                    products.get(i).setCountBtn(products.get(i).getCountBtn() * 2);
                    products.get(i).setCngVal(true);
                }
            } else {
                products.get(i).setCountBtn(products.get(i).getCountBtn() + 1);
            }
        }
    }

    void fillData() {
        for (int i = 1; i <= 10; i++) {
            products.add(new Roulette(i, -1, -1.0, true));
        }
        sort();
    }

    void sort() {
        Collections.sort(products, new Comparator<Roulette>() {
            public int compare(Roulette o1, Roulette o2) {
                return o2.getFieldR() - o1.getFieldR();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_LOAD_FIELD) {
            if (resultCode == Activity.RESULT_OK) {
                int result = data.getIntExtra("field_r", 0);
                int position = data.getIntExtra("position", 0);

                Roulette oulette = products.get(position);
                oulette.setFieldR(result);
                sort();
                rouletteAdapter.notifyDataSetChanged();
            }
        }

        if (requestCode == RESULT_LOAD_RATE) {
            if (resultCode == Activity.RESULT_OK) {
                double result = data.getDoubleExtra("field_r", 0);
                int position = data.getIntExtra("position", 0);
                Roulette oulette = products.get(position);
                oulette.setRateR(result);
                sort();
                rouletteAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void itemCliked(View view, int position) {
        switch (view.getId()) {
            case R.id.buttonClearItem:
                Roulette oulette = products.get(position);
                oulette.setRateR(Constants.DEFAULT_VALUES.RATE);
                oulette.setFieldR(Constants.DEFAULT_VALUES.FIELD);
                oulette.setCountBtn(Constants.DEFAULT_VALUES.BUTTON);
                sort();
                rouletteAdapter.notifyDataSetChanged();
                break;
            case R.id.eTField:
                Intent fieldIntent = new Intent(this, FieldActivity.class);
                fieldIntent.putExtra("position", position);
                startActivityForResult(fieldIntent, RESULT_LOAD_FIELD);
                break;
            case R.id.eTRate:
                Intent rateIntent = new Intent(this, RateActivity.class);
                rateIntent.putExtra("position", position);
                startActivityForResult(rateIntent, RESULT_LOAD_RATE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCount:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}