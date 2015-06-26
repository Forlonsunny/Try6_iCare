package theoaktroop.icare.Doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


import theoaktroop.icare.R;

/**
 * Created by Sunny_PC on 6/26/2015.
 */
public class ListDoctorAdapter extends BaseAdapter {
    public static final String TAG = "ListDoctorClassAdapter";

    private List<DoctorClass> mItems;
    private LayoutInflater mInflater;

    public ListDoctorAdapter(Context context, List<DoctorClass> listDoctorClass) {
        this.setItems(listDoctorClass);
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public DoctorClass getItem(int position) {
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
            v = mInflater.inflate(R.layout.doctor_helper_xml, parent, false);
            holder = new ViewHolder();
            holder.txtDoctorClass = (TextView) v.findViewById(R.id.doctor_helper_tv);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        DoctorClass currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtDoctorClass.setText("Name :" + currentItem.getDoctorName() + "\n\n" + "Type: " + currentItem.getDoctorType() + "\n\n" + "Address: " + currentItem.getDoctorAddress() + "\n\n"+ "Phone: " + currentItem.getDoctorPhone() + "\n\n" + "Appointment Date: "+currentItem.getAppointmentDate()+"\n\n");

        }

        return v;
    }

    public List<DoctorClass> getItems() {
        return mItems;
    }

    public void setItems(List<DoctorClass> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtDoctorClass;

    }
}
