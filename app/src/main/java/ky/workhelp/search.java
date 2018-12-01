package ky.workhelp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import static ky.workhelp.main.deviceDetailName;

public class search extends AppCompatActivity {

    private String[] deviceDetailValue = new String[deviceDetailName.length];
    private RecyclerView recyclerView;
    private device_adapter deviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
    }

    public void search_button(View view) throws SnappydbException {
        TextView tv = findViewById(R.id.Device_Name);
        String str = tv.getText().toString();

        //check the Device Name format is right
        if (main.checkDeviceName(str)) {
            Toast.makeText(this, "设备名称格式错误", Toast.LENGTH_SHORT).show();
            return;
        }

        //check the Device is exist
        DB database = DBFactory.open(this, "DeviceName");
        if (!database.exists(str)) {
            Toast.makeText(this, "设备不存在", Toast.LENGTH_SHORT).show();
            return;
        }

        //search the Device Detail
        deviceDetailValue = database.getObjectArray(str, String.class);
        database.close();

        //put the data to scrollView_search
        recyclerView = findViewById(R.id.Device_detail);
        recyclerView.setItemViewCacheSize(20);
        deviceAdapter = new device_adapter(deviceDetailName,deviceDetailValue);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(deviceAdapter);
        Toast.makeText(this, "设备已找到", Toast.LENGTH_SHORT).show();
    }
}
