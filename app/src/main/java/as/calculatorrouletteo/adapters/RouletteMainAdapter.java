package as.calculatorrouletteo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import as.calculatorrouletteo.R;
import as.calculatorrouletteo.models.Roulette;

public class RouletteMainAdapter extends BaseAdapter implements View.OnClickListener {

    ArrayList<Roulette> objects;
    Context ctx;
    private LayoutInflater layoutInflater;
    private ClickListener clickListener;

    public RouletteMainAdapter(Context context, ArrayList<Roulette> list) {
        this.objects = list;
        this.ctx = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    Roulette getProduct(int position) {
        return ((Roulette) getItem(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_main_row, parent, false);
        }

        Roulette p = getProduct(position);
        DecimalFormat form = new DecimalFormat("0.00");

        TextView eTField = (TextView) view.findViewById(R.id.eTField);
        eTField.setOnClickListener(this);
        eTField.setTag(position);
        eTField.setText(String.valueOf(p.getFieldR()));

        TextView eTRate = (TextView) view.findViewById(R.id.eTRate);
        eTRate.setOnClickListener(this);
        eTRate.setTag(position);

        if (p.getFieldR() != -1) {
            eTField.setText(String.valueOf(p.getFieldR()));
        } else {
            eTField.setText(String.valueOf(""));
        }

        if (p.getRateR() != -1.0) {
            eTRate.setText(String.valueOf(form.format(p.getRateR())));
        } else {
            eTRate.setText(String.valueOf(""));
        }

        Button buttonClearItem = (Button) view.findViewById(R.id.buttonClearItem);
        if (p.getCountBtn() > 0) {
            buttonClearItem.setText(String.valueOf(p.getCountBtn()));
        } else {
            buttonClearItem.setText("");
        }

        buttonClearItem.setOnClickListener(this);
        buttonClearItem.setTag(position);
        return view;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View v) {
        int currentPosition = (Integer) v.getTag();
        if (clickListener != null) {
            clickListener.itemCliked(v, currentPosition);
        }
    }

    public interface ClickListener {
        void itemCliked(View view, int position);
    }
}