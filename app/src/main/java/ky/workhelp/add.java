package ky.workhelp;

import android.annotation.SuppressLint;
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

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static ky.workhelp.main.deviceDetailName;

public class add extends AppCompatActivity {

    private String[] deviceDetailValue = new String[deviceDetailName.length];
    private RecyclerView recyclerView;
    private device_adapter deviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
    }

    public void add_button(View view) throws SnappydbException {
        TextView tv = findViewById(R.id.Device_Name);
        String str = tv.getText().toString();

        //check the Device Name format is right
        if (main.checkDeviceName(str)) {
            Toast.makeText(this, "设备名称格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        //check the Device is exist
        DB database = DBFactory.open(this, "DeviceName");

        if (database.exists(str)) {
            Toast.makeText(this, "设备已存在", Toast.LENGTH_SHORT).show();
            database.close();
            return;
        } else
            database.close();

        //deal with data
        @SuppressLint("SimpleDateFormat") Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String inDate = formatter.format(new Date());
        for (int i = 0; i < deviceDetailName.length; i++) {
            if (i == 7)
                deviceDetailValue[i] = inDate;
            else
                deviceDetailValue[i] = deviceDetailName[i];
        }
        deviceDetailValue[8] = "";

        //put the data to scrollView_search
        recyclerView = findViewById(R.id.Device_detail);
        recyclerView.setItemViewCacheSize(20);
        deviceAdapter = new device_adapter(deviceDetailName,deviceDetailValue);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(deviceAdapter);
        Toast.makeText(this, "请填写设备信息", Toast.LENGTH_SHORT).show();
    }

    public void up_load_button(View view) throws SnappydbException {
        TextView tv = findViewById(R.id.Device_Name);
        String str = tv.getText().toString();

        //check the Device Name format is right
        if (main.checkDeviceName(str)) {
            Toast.makeText(this, "设备名称格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        //check the Device is exist
        DB database = DBFactory.open(this, "DeviceName");

        if (database.exists(str)) {
            Toast.makeText(this, "设备已存在", Toast.LENGTH_SHORT).show();
            database.close();
            return;
        } else
            database.close();

        //check the DeviceName is find
        @SuppressLint("CutPasteId") RecyclerView recyclerView = findViewById(R.id.Device_detail);
        if (Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() == 0) {
            Toast.makeText(this, "请先查找设备", Toast.LENGTH_SHORT).show();
            return;
        }

        //get the value from edit text in the list view
        deviceDetailValue = new String[deviceDetailName.length];
        for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
            deviceDetailValue[i] = deviceAdapter.texts[i];
        }
        @SuppressLint("SimpleDateFormat") Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        deviceDetailValue[7] = formatter.format(new Date());
        deviceDetailValue[8] = "";

        //add to snappyDB
        database = DBFactory.open(this, "DeviceName");
        database.put(str, deviceDetailValue);

        //check the data is in snappyDB or not
        if (database.exists(str))
            Toast.makeText(this, "设备已添加", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "设备添加失败", Toast.LENGTH_SHORT).show();
        database.close();
        recyclerView = findViewById(R.id.Device_detail);
        recyclerView.setAdapter(null);   // clear the RecyclerView
    }
}
