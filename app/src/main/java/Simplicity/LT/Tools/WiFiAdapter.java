package Simplicity.LT.Tools;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class WiFiAdapter extends BaseAdapter {
		private Context context;
		ArrayList<Map<String, String>> mainList;
		public WiFiAdapter(Context context, ArrayList<Map<String, String>> list) {
			super();
			this.context = context;
			this.mainList = list;
		}

		@Override
		public int getCount() {
			return mainList.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public View getView(int position, View view, ViewGroup parent) {
			ViewHolder holder;
			Map<String, String> m = mainList.get(position);
			if (view != null) {
				holder = (ViewHolder) view.getTag();
			} else {
				view = View.inflate(context, R.layout.wifi_info, null);
				holder = new ViewHolder();
				holder.ssid = view.findViewById(R.id.ssid);
				holder.password = view.findViewById(R.id.password);
				view.setTag(holder);
			}
			holder.ssid.setText(m.get("ssid"));
			holder.password.setText(m.get("psk"));
			return view;
		}
		
		class ViewHolder {
			public TextView ssid;
			public TextView password;
		}
	
	}
