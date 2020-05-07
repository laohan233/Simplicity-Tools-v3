package Simplicity.LT.Tools;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import miui.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class WifiActivity extends Activity {
    ArrayList<Map<String, String>> mainList;
    Context context = this;
    String sPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(miui.R.style.Theme_Light_Settings);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        Intent intent = getIntent();
            if (Build.VERSION.SDK_INT >= 26) {
                intent.putExtra("path", "/data/misc/wifi/WifiConfigStore.xml");
            } else {
                intent.putExtra("path", "/data/misc/wifi/wpa_supplicant.conf");
            }
            if(Intent.ACTION_VIEW.equals(getIntent().getAction())) {
                sPath = intent.getDataString().substring(8);
                if(sPath.contains("%20")) {
                    finish();
                    System.exit(0);
                } else {
                    mainList = get(sPath);
                }
            } else {
                Bundle bundle = intent.getExtras();
                sPath = bundle.getString("path");
                mainList = get(sPath);
            }
            onWork();
    }

    private void onWork() {
        if (mainList == null) {
            Toast.makeText(WifiActivity.this, "未发现密码", Toast.LENGTH_LONG).show();
        } else {
            if (mainList.size() == 0) {
                Toast.makeText(WifiActivity.this, "未发现密码", Toast.LENGTH_LONG).show();
            } else {
                doWork();
            }
        }
    }


    private void doWork() {
        ListView lv = findViewById(R.id.WIFI);
        lv.setAdapter(new WiFiAdapter(this, mainList));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText(null, mainList.get(position).get("psk")));
                Toast.makeText(WifiActivity.this, "已复制密码到剪切板", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public ArrayList<Map<String, String>> get(String path) {
        try {
            ReadFile file = new ReadFile(path);
            return file.getList(this.context);
        } catch (Exception e) {
            Toast.makeText(this, "ReadFile:" + e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }
}
