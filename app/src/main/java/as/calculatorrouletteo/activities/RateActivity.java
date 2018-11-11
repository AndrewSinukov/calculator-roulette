package as.calculatorrouletteo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

import as.calculatorrouletteo.Constants;
import as.calculatorrouletteo.R;
import as.calculatorrouletteo.adapters.RoletteRateAdapter;
import as.calculatorrouletteo.models.Roulette;

public class RateActivity extends Activity implements RoletteRateAdapter.ClickListener {

    ArrayList<Roulette> products = new ArrayList<>();
    private int positionMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        positionMain = getIntent().getIntExtra("position", 0);

        fillData(Constants.DRAW_GRIDS.GRIDS_RATE);
        GridView gridView = (GridView) findViewById(R.id.gridViewR);
        RoletteRateAdapter roletteRateAdapter = new RoletteRateAdapter(getApplicationContext(), products);
        roletteRateAdapter.setClickListener(this);
        gridView.setAdapter(roletteRateAdapter);
    }

    private void fillData(double[] data) {
        for (double q : data) {
            products.add(new Roulette(q));
        }
    }

    @Override
    public void itemCliked(View view, int position) {
        switch (view.getId()) {
            case R.id.eTRate:
                Roulette oulette = products.get(position);
                double fld = oulette.getRateR();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("field_r", fld);
                returnIntent.putExtra("position", positionMain);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
        }
    }
}