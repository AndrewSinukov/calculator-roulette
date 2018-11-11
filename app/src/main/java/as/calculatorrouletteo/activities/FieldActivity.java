package as.calculatorrouletteo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

import as.calculatorrouletteo.Constants;
import as.calculatorrouletteo.R;
import as.calculatorrouletteo.adapters.RouletteFieldAdapter;
import as.calculatorrouletteo.models.Roulette;

public class FieldActivity extends Activity implements RouletteFieldAdapter.ClickListener {

    private ArrayList<Roulette> products = new ArrayList<>();
    private int positionMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);

        fillData(Constants.DRAW_GRIDS.GRIDS_FIELD);

        positionMain = getIntent().getIntExtra("position", 0);

        GridView gridView = (GridView) findViewById(R.id.gridViewR);

        RouletteFieldAdapter rouletteAdapter = new RouletteFieldAdapter(getApplicationContext(), products);
        rouletteAdapter.setClickListener(this);
        gridView.setAdapter(rouletteAdapter);
    }

    private void fillData(int[] data) {
        for (int q : data) {
            products.add(new Roulette(q));
        }
    }

    @Override
    public void itemCliked(View view, int position) {
        switch (view.getId()) {
            case R.id.eTField:
                Roulette oulette = products.get(position);
                int fld = oulette.getFieldR();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("field_r",fld);
                returnIntent.putExtra("position",positionMain);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
        }
    }
}