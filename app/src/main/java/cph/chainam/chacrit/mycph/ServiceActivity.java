package cph.chainam.chacrit.mycph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private ImageView imageView;
    private ListView listView;
    private String[] loginStrings, nameStrings,
            dateStrings, detailStrings, qrCodeStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        initialView();

        //Get value from Intent
        getValueFromIntent();



        createListView();

        controller();   // เขียนแล้วต้องทำ imprement alt+Enter เลือก imprement อันที่ 2

    } //main method

    @Override  // การสร้าง Override  คือการสือทอด method สร้างโดย alt+enter เลือก onActivityResult
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {  // สแกนแล้วเจอ ก็ เอาค่ามา ได้ค่ามา
            String result = data.getStringExtra("SCAN_RESULT");
            Log.d("28AprilV3", "Result From Scan ==> " + result);

            Intent intent = new Intent(ServiceActivity.this, DetailActivity.class);
            intent.putExtra("QRcode", result);
            startActivity(intent);



        }

    }

    private void controller() {
        imageView.setOnClickListener(ServiceActivity.this);

    }

    private void createListView() {

        final String tag = "27AprilV2";
        String urlPHP = "http://swiftcodingthai.com/cph/getProduct.php";

        try {

            GetData getData = new GetData(ServiceActivity.this);
            getData.execute(urlPHP);
            String strJSON = getData.get();
            Log.d(tag, "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            nameStrings = new String[jsonArray.length()];
            dateStrings = new String[jsonArray.length()];
            detailStrings = new String[jsonArray.length()];
            qrCodeStrings = new String[jsonArray.length()];

            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");
                dateStrings[i] = jsonObject.getString("Date_Receive");
                detailStrings[i] = jsonObject.getString("Description");
                qrCodeStrings[i] = jsonObject.getString("QR_code");

            }


            MyAdapter myAdapter = new MyAdapter(ServiceActivity.this, nameStrings, dateStrings, detailStrings);
            listView.setAdapter(myAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.d(tag, "You Click ==>" + qrCodeStrings[position]);

                    Intent intent = new Intent(ServiceActivity.this, DetailActivity.class);
                    intent.putExtra("QRcode", qrCodeStrings[position]);
                    startActivity(intent);
                }
            });    //setOnItemClickListener คือ คลิ๊กที่ไหนก็ได้  อันนี้คือทำให้ หน้า service สามารรถคลิ๊กได้


        } catch (Exception e) {
            Log.d(tag, "e createListView ==>" + e.toString());

        }
    }



    private void getValueFromIntent() {
        loginStrings = getIntent().getStringArrayExtra("Login"); // รับค่า login มาโชว์
        textView.setText(loginStrings[1]);  // คอลัมชื่อ
    }

    private void initialView() {
        textView = (TextView) findViewById(R.id.txtName);
        imageView = (ImageView) findViewById(R.id.imvQR);
        listView = (ListView) findViewById(R.id.lirProduct);
    }

    @Override
    public void onClick(View v) {
        String tag = "28AprilV3";

        if (v == imageView) {

            try {

                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 10);    //10 คือ inter code



            } catch (Exception e) {
                Log.d(tag, "e onClick ==> " + e.toString());

            }
        }
    }
} // main class