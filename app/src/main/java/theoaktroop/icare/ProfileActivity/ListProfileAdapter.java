package theoaktroop.icare.ProfileActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

import theoaktroop.icare.R;


public class ListProfileAdapter extends BaseAdapter {
	
	public static final String TAG = "ListProfilesAdapter";
	
	private List<Profile> mItems;
	private LayoutInflater mInflater;
	
	public ListProfileAdapter(Context context, List<Profile> listProfiles) {
		this.setItems(listProfiles);
		this.mInflater = LayoutInflater.from(context);
	}
	

	@Override
	public int getCount() {
		return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
	}

	@Override
	public Profile getItem(int position) {
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
			v = mInflater.inflate(R.layout.list_item_profile, parent, false);
			holder = new ViewHolder();
			holder.txtProfileName = (TextView) v.findViewById(R.id.txt_profile_name);
            holder.imageView=(ImageView)v.findViewById(R.id.profile_picinListAdapter);
			v.setTag(holder);
		}
		else {
			holder = (ViewHolder) v.getTag();
		}
		
		// fill row data
		Profile currentItem = getItem(position);
		if(currentItem != null) {
			holder.txtProfileName.setText(currentItem.getProfileName());
            if(currentItem.getFinalImages()!=null)
            {
                byte[] outImages=currentItem.getFinalImages();
                ByteArrayInputStream imagesStream= new ByteArrayInputStream(outImages);
                Bitmap theImages= BitmapFactory.decodeStream(imagesStream);
                holder.imageView.setImageBitmap(theImages);
            }
            else {
                holder.imageView.setImageResource(R.drawable.hasanvhai);
            }

		}
		
		return v;
	}
	
	public List<Profile> getItems() {
		return mItems;
	}

	public void setItems(List<Profile> mItems) {
		this.mItems = mItems;
	}

	class ViewHolder {
		TextView txtProfileName;
          ImageView imageView;
			}

}
