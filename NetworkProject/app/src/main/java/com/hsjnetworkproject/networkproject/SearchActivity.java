package com.hsjnetworkproject.networkproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hsjnetworkproject.networkproject.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<SearchItem> data = null;
    TextView pageText;

    int page;
    Button nextButton;
    Button backButton;
    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        StrictMode.enableDefaults();

        Intent keyIntent = getIntent();

        listview = findViewById(R.id.search_list);
        nextButton = findViewById(R.id.next);
        backButton = findViewById(R.id.back);
        nextButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        pageText = findViewById(R.id.page);
        page = Integer.parseInt(pageText.getText().toString());

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1320000/LosPtfundInfoInqireService/getPtLosfundInfoAccTpNmCstdyPlace");
        try {
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=Ve45QXE9K2njPagNB1FkKqETMfM1C0Iqe3pgq%2F2OCxVont6NxGBqoa2AYq25bRxy2VuQmGM7Afc0c5uj6F%2BI9g%3D%3D");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(Integer.toString(page), "UTF-8"));
            System.out.println("success1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
            System.out.println("success2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(keyIntent.getStringExtra("name") != null) {
            try {
                urlBuilder.append("&" + URLEncoder.encode("PRDT_NM", "UTF-8") + "=" + URLEncoder.encode(keyIntent.getStringExtra("name"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(keyIntent.getStringExtra("area") != null) {
            try {
                urlBuilder.append("&" + URLEncoder.encode("DEP_PLACE", "UTF-8") + "=" + URLEncoder.encode(keyIntent.getStringExtra("area"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.print(urlBuilder.toString());
            URL url = new URL(urlBuilder.toString());
            System.out.println("success3");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            Document doc = documentBuilder.parse(url.openStream());
            Element element = doc.getDocumentElement();
            NodeList nameList = element.getElementsByTagName("fdSbjt");
            NodeList areaList = element.getElementsByTagName("depPlace");
            NodeList imageList = element.getElementsByTagName("fdFilePathImg");
            NodeList numberList = element.getElementsByTagName("rnum");

            NodeList idList = element.getElementsByTagName("atcId");//관리 id
            NodeList groupList = element.getElementsByTagName("prdtClNm");// 물품분류명
            //NodeList colorList = element.getElementsByTagName("clrNm");// 색상명
            NodeList catalogList = element.getElementsByTagName("fdPrdtNm");// 물품명
            NodeList dateList = element.getElementsByTagName("fdYmd");// 습득일자


            data = new ArrayList<>();

            int n = nameList.getLength();
            System.out.print(n);
            for(int i = 0; i < n; i++){
                System.out.print(i);
                String nameValue = nameList.item(i).getFirstChild().getNodeValue();
                String areaValue = areaList.item(i).getFirstChild().getNodeValue();
                String imageValue = imageList.item(i).getFirstChild().getNodeValue();
                String numberValue = numberList.item(i).getFirstChild().getNodeValue();

                String idValue = idList.item(i).getFirstChild().getNodeValue();
                String groupValue = groupList.item(i).getFirstChild().getNodeValue();
                //String colorValue = colorList.item(i).getFirstChild().getNodeValue();
                String catalogValue = catalogList.item(i).getFirstChild().getNodeValue();
                String dateValue = dateList.item(i).getFirstChild().getNodeValue();
                //Drawable drawable = LoadImageFromWebOperations( "https://t1.daumcdn.net/cfile/tistory/1946B11A4C5606ED3C" );
                //System.out.println(itemValue);

                SearchItem searchItem = new SearchItem(nameValue, areaValue, imageValue, numberValue, idValue, dateValue, catalogValue, groupValue);
                data.add(searchItem);
            }

            SearchAdapter adapter = new SearchAdapter(this, R.layout.item_search, data);
            listview.setAdapter(adapter);



            /*

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300){
                System.out.println("success3");
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            System.out.println(rd.readLine());
            while((line = rd.readLine()) != null){

                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            String xml = sb.toString();
            System.out.println(xml);

            if(xml != null){
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();

                InputStream is = new ByteArrayInputStream(xml.getBytes());
                //System.out.println(is);

                Document doc = documentBuilder.parse(url.openStream());
                Element element = doc.getDocumentElement();
                NodeList items = element.getElementsByTagName("depPlace");

                int n = items.getLength();
                for(int i = 0; i < n; i++){
                    String itemValue = items.item(i).getFirstChild().getNodeValue();
                    System.out.println(itemValue);

                }
            }
            */
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent (getApplicationContext(), InformationActivity.class);

                intent.putExtra("name", data.get(i).getName());
                intent.putExtra("area", data.get(i).getArea());
                intent.putExtra("image", data.get(i).getImage());
                intent.putExtra("id", data.get(i).getId());
                intent.putExtra("group", data.get(i).getGroup());
                intent.putExtra("catalog", data.get(i).getCatalog());
                intent.putExtra("date", data.get(i).getDate());



                startActivity(intent);
            }
        });





    }


    @Override
    public void onClick(View view) {
        StrictMode.enableDefaults();
        StringBuilder urlBuilder;
        ListView listview = findViewById(R.id.search_list);

        Intent keyIntent = getIntent();
        switch (view.getId()){
            case R.id.next:


                page = Integer.parseInt(pageText.getText().toString()) + 1;
                pageText.setText(Integer.toString(page));
                urlBuilder = new StringBuilder("http://apis.data.go.kr/1320000/LosPtfundInfoInqireService/getPtLosfundInfoAccTpNmCstdyPlace");
                try {
                    urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=Ve45QXE9K2njPagNB1FkKqETMfM1C0Iqe3pgq%2F2OCxVont6NxGBqoa2AYq25bRxy2VuQmGM7Afc0c5uj6F%2BI9g%3D%3D");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(Integer.toString(page), "UTF-8"));
                    System.out.println("success1");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
                    System.out.println("success2");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if(keyIntent.getStringExtra("name") != null) {
                    try {
                        urlBuilder.append("&" + URLEncoder.encode("PRDT_NM", "UTF-8") + "=" + URLEncoder.encode(keyIntent.getStringExtra("name"), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                if(keyIntent.getStringExtra("area") != null) {
                    try {
                        urlBuilder.append("&" + URLEncoder.encode("DEP_PLACE", "UTF-8") + "=" + URLEncoder.encode(keyIntent.getStringExtra("area"), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    System.out.print(urlBuilder.toString());
                    URL url = new URL(urlBuilder.toString());
                    System.out.println("success3");

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = factory.newDocumentBuilder();

                    Document doc = documentBuilder.parse(url.openStream());
                    Element element = doc.getDocumentElement();
                    NodeList nameList = element.getElementsByTagName("fdSbjt");
                    NodeList areaList = element.getElementsByTagName("depPlace");
                    NodeList imageList = element.getElementsByTagName("fdFilePathImg");
                    NodeList numberList = element.getElementsByTagName("rnum");

                    NodeList idList = element.getElementsByTagName("atcId");//관리 id
                    NodeList groupList = element.getElementsByTagName("prdtClNm");// 물품분류명
                    NodeList catalogList = element.getElementsByTagName("fdPrdtNm");// 물품명
                    NodeList dateList = element.getElementsByTagName("fdYmd");// 습득일자


                    data = new ArrayList<>();

                    int n = nameList.getLength();
                    System.out.print(n);
                    for(int i = 0; i < n; i++){
                        System.out.print(i);
                        String nameValue = nameList.item(i).getFirstChild().getNodeValue();
                        String areaValue = areaList.item(i).getFirstChild().getNodeValue();
                        String imageValue = imageList.item(i).getFirstChild().getNodeValue();
                        String numberValue = numberList.item(i).getFirstChild().getNodeValue();

                        String idValue = idList.item(i).getFirstChild().getNodeValue();
                        String groupValue = groupList.item(i).getFirstChild().getNodeValue();
                        String catalogValue = catalogList.item(i).getFirstChild().getNodeValue();
                        String dateValue = dateList.item(i).getFirstChild().getNodeValue();
                        //Drawable drawable = LoadImageFromWebOperations( "https://t1.daumcdn.net/cfile/tistory/1946B11A4C5606ED3C" );
                        //System.out.println(itemValue);

                        SearchItem searchItem = new SearchItem(nameValue, areaValue, imageValue, numberValue, idValue, dateValue, catalogValue, groupValue);
                        data.add(searchItem);
                    }

                    SearchAdapter adapter = new SearchAdapter(this, R.layout.item_search, data);
                    listview.setAdapter(adapter);


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.back:
                if(page == 1){
                    break;
                }


                page = Integer.parseInt(pageText.getText().toString()) - 1;
                pageText.setText(Integer.toString(page));
                System.out.print(page);
                urlBuilder = new StringBuilder("http://apis.data.go.kr/1320000/LosPtfundInfoInqireService/getPtLosfundInfoAccTpNmCstdyPlace");
                try {
                    urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=Ve45QXE9K2njPagNB1FkKqETMfM1C0Iqe3pgq%2F2OCxVont6NxGBqoa2AYq25bRxy2VuQmGM7Afc0c5uj6F%2BI9g%3D%3D");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(Integer.toString(page), "UTF-8"));
                    System.out.println("success1");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
                    System.out.println("success2");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if(keyIntent.getStringExtra("name") != null) {
                    try {
                        urlBuilder.append("&" + URLEncoder.encode("PRDT_NM", "UTF-8") + "=" + URLEncoder.encode(keyIntent.getStringExtra("name"), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                if(keyIntent.getStringExtra("area") != null) {
                    try {
                        urlBuilder.append("&" + URLEncoder.encode("DEP_PLACE", "UTF-8") + "=" + URLEncoder.encode(keyIntent.getStringExtra("area"), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    System.out.print(urlBuilder.toString());
                    URL url = new URL(urlBuilder.toString());
                    System.out.println("success3");

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = factory.newDocumentBuilder();

                    Document doc = documentBuilder.parse(url.openStream());
                    Element element = doc.getDocumentElement();
                    NodeList nameList = element.getElementsByTagName("fdSbjt");
                    NodeList areaList = element.getElementsByTagName("depPlace");
                    NodeList imageList = element.getElementsByTagName("fdFilePathImg");
                    NodeList numberList = element.getElementsByTagName("rnum");

                    NodeList idList = element.getElementsByTagName("atcId");//관리 id
                    NodeList groupList = element.getElementsByTagName("prdtClNm");// 물품분류명
                    NodeList catalogList = element.getElementsByTagName("fdPrdtNm");// 물품명
                    NodeList dateList = element.getElementsByTagName("fdYmd");// 습득일자


                    data = new ArrayList<>();

                    int n = nameList.getLength();
                    System.out.print(n);
                    for(int i = 0; i < n; i++){
                        System.out.print(i);
                        String nameValue = nameList.item(i).getFirstChild().getNodeValue();
                        String areaValue = areaList.item(i).getFirstChild().getNodeValue();
                        String imageValue = imageList.item(i).getFirstChild().getNodeValue();
                        String numberValue = numberList.item(i).getFirstChild().getNodeValue();

                        String idValue = idList.item(i).getFirstChild().getNodeValue();
                        String groupValue = groupList.item(i).getFirstChild().getNodeValue();
                        String catalogValue = catalogList.item(i).getFirstChild().getNodeValue();
                        String dateValue = dateList.item(i).getFirstChild().getNodeValue();
                        //Drawable drawable = LoadImageFromWebOperations( "https://t1.daumcdn.net/cfile/tistory/1946B11A4C5606ED3C" );
                        //System.out.println(itemValue);

                        SearchItem searchItem = new SearchItem(nameValue, areaValue, imageValue, numberValue, idValue, dateValue, catalogValue, groupValue);
                        data.add(searchItem);
                    }
                    SearchAdapter adapter = new SearchAdapter(this, R.layout.item_search, data);
                    listview.setAdapter(adapter);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
