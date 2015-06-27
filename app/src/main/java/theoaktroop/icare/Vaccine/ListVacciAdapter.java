package theoaktroop.icare.Vaccine;

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
public class ListVacciAdapter extends BaseAdapter {
    public static final String TAG = "ListVacciAdapter";

    private List<VaccinationClass> mItems;
    private LayoutInflater mInflater;

    public ListVacciAdapter(Context context, List<VaccinationClass> listvVaccinationClasses) {
        this.setItems(listvVaccinationClasses);
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public VaccinationClass getItem(int position) {
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
            v = mInflater.inflate(R.layout.vacci_helper_xml, parent, false);
            holder = new ViewHolder();
            holder.txtVacciName=(TextView)v.findViewById(R.id.tv_vacci_name);
            holder.txtVacciReason=(TextView)v.findViewById(R.id.tv_vacci_reason);
            holder.txtVacciDate=(TextView)v.findViewById(R.id.tv_vacci_date);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        VaccinationClass currentItem = getItem(position);
        if(currentItem != null) {
           holder.txtVacciName.setText(currentItem.getVaccineName());
            holder.txtVacciReason.setText(currentItem.getReason());
            holder.txtVacciDate.setText(currentItem.getVaccineDate());
            System.out.println(" From List Vaccine Name= "+currentItem.getVaccineName());
        }

        return v;
    }

    public List<VaccinationClass> getItems() {
        return mItems;
    }

    public void setItems(List<VaccinationClass> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtVacciName,txtVacciReason,txtVacciDate;

    }
}
