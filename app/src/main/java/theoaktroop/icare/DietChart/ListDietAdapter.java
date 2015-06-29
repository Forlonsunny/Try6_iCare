package theoaktroop.icare.DietChart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import theoaktroop.icare.R;

/**
 * Created by Sunny_PC on 6/23/2015.
 */
public class ListDietAdapter extends BaseAdapter {
    public static final String TAG = "ListDietChartClasssAdapter";

    private List<DietChartClass> mItems;
    private LayoutInflater mInflater;

    public ListDietAdapter(Context context, List<DietChartClass> listDietChartClasss) {
        this.setItems(listDietChartClasss);
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public DietChartClass getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.diet_helper_xml, parent, false);
            holder = new ViewHolder();
            holder.txtDietDay=(TextView)v.findViewById(R.id.tv_diet_day);
            holder.txtDietMtype=(TextView)v.findViewById(R.id.tv_diet_mealType);
            holder.txtDietMenu=(TextView)v.findViewById(R.id.tv_diet_menu);
            holder.txDietTime = (TextView)v.findViewById(R.id.tv_diet_time);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        DietChartClass currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtDietDay.setText(currentItem.getDay());
            holder.txtDietMtype.setText(currentItem.getMealType());
            holder.txtDietMenu.setText(currentItem.getFoodMenu());
            holder.txDietTime.setText(currentItem.getDietTime());
        }

        return v;
    }

    public List<DietChartClass> getItems() {
        return mItems;
    }

    public void setItems(List<DietChartClass> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtDietDay,txtDietMtype,txtDietMenu,txDietTime;

    }
}
