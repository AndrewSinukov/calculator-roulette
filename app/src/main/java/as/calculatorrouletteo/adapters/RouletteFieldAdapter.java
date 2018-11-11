package as.calculatorrouletteo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import as.calculatorrouletteo.R;
import as.calculatorrouletteo.models.Roulette;


public class RouletteFieldAdapter extends BaseAdapter implements View.OnClickListener {

    Context ctx;
    ArrayList<Roulette> objects;
    private LayoutInflater layoutInflater;
    private ClickListener clickListener;

    public RouletteFieldAdapter(Context context, ArrayList<Roulette> list) {
        this.objects = list;
        this.ctx = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    Roulette getProduct(int position) {
        return ((Roulette) getItem(position));
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_field_row, parent, false);
        }

        Roulette p = getProduct(position);

        TextView eTField = (TextView) view.findViewById(R.id.eTField);
        TextView eTFieldNull = (TextView) view.findViewById(R.id.eTFieldNull);

        if (p.getFieldR() != -1) {
            eTField.setText(String.valueOf(p.getFieldR()));
            eTField.setVisibility(View.VISIBLE);
        } else {
            eTField.setVisibility(View.GONE);
            eTFieldNull.setVisibility(View.VISIBLE);
        }

        eTField.setText(String.valueOf(p.getFieldR()));
        eTField.setOnClickListener(this);
        eTField.setTag(position);
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