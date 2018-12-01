package ky.workhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



public class main extends AppCompatActivity {

    public static String deviceDetailName[] = {"设备类型","序列号","品牌名称","型号","使用人","员工号","资产编号","投运日期","报废日期","办公区","楼层","办公室","备注"};

    //check Device Name is legal
    public static boolean checkDeviceName(String str) {
        if (10 != str.length()) {
            return true;
        }
        if (str.indexOf("ZQPC") == 0) {
            return false;
        }
        if (str.indexOf("ZQPR") == 0) {
            return false;
        }
        if (str.indexOf("ZQSR") == 0) {
            return false;
        }
        return (str.indexOf("ZQMO") != 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void jump_to_search(View view) {
        Intent intent = new Intent(this, search.class);
        startActivity(intent);
    }

    public void jump_to_add(View view) {
        Intent intent = new Intent(this, add.class);
        startActivity(intent);
    }

    public void jump_to_edit(View view) {
        Intent intent = new Intent(this, edit.class);
        startActivity(intent);
    }

    public void jump_to_delete(View view) {
        Intent intent = new Intent(this, delete.class);
        startActivity(intent);
    }
}
