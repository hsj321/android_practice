package com.hsjnetworkproject.networkproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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


public class ShopActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<SearchItem> shopData = null;
    ListView listview;

    TextView pageText;

    int page;
    Button nextButton;
    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Intent keyIntent = getIntent();

        listview = findViewById(R.id.shop_list);
        nextButton = findViewById(R.id.shop_next);
        backButton = findViewById(R.id.shop_back);
        nextButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        pageText = findViewById(R.id.shop_page);
        page = Integer.parseInt(pageText.getText().toString());


        StringBuilder urlBuilder = new StringBuilder("https://openapi.11st.co.kr/openapi/OpenApiService.tmall");
        try {
            urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=fedbeea5efc8f687da2910636c778f1b");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(urlBuilder);

        try {
            urlBuilder.append("&" + URLEncoder.encode("apiCode","UTF-8") + "=ProductSearch");
            System.out.println("success1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(urlBuilder);
        /*
        try {
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            System.out.println("success2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        */
        if(keyIntent.getStringExtra("catalog") != null) {
            try {
                urlBuilder.append("&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode(keyIntent.getStringExtra("catalog"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        System.out.println(urlBuilder);
        try {
            urlBuilder.append("&" + URLEncoder.encode("pageNum","UTF-8") + "=" + URLEncoder.encode(Integer.toString(page), "UTF-8"));
            System.out.println("success2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(urlBuilder);

        try {
            urlBuilder.append("&" + URLEncoder.encode("pageSize","UTF-8") + "=10");
            System.out.println("success3");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(urlBuilder);
        String a = urlBuilder.toString();
        System.out.println(a);

        try {
            URL url = new URL(a);
            System.out.println("success4");


            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            Document doc = documentBuilder.parse(url.openStream());

            Element element = doc.getDocumentElement();
            NodeList nameList = element.getElementsByTagName("ProductName");
            NodeList imageList = element.getElementsByTagName("ProductImage");
            NodeList priceList = element.getElementsByTagName("ProductPrice");
            NodeList urlList = element.getElementsByTagName("DetailPageUrl");




            shopData = new ArrayList<>();
            System.out.println("success5");

            int n = nameList.getLength();
            System.out.print(n);
            for(int i = 0; i < n; i++){
                System.out.print(i);
                String nameValue = nameList.item(i).getFirstChild().getNodeValue();
                String imageValue = imageList.item(i).getFirstChild().getNodeValue();
                String priceValue = priceList.item(i).getFirstChild().getNodeValue();
                String urlValue = urlList.item(i).getFirstChild().getNodeValue();
                System.out.println(nameValue);



                //Drawable drawable = LoadImageFromWebOperations( "https://t1.daumcdn.net/cfile/tistory/1946B11A4C5606ED3C" );
                //System.out.println(itemValue);

                SearchItem searchItem = new SearchItem(nameValue, priceValue, imageValue, null, null,null, null, urlValue);
                shopData.add(searchItem);
            }

            SearchAdapter adapter = new SearchAdapter(this, R.layout.item_search, shopData);
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
                Intent internetintent = new Intent(Intent.ACTION_VIEW, Uri.parse(shopData.get(i).getGroup()));
                startActivity(internetintent);

            }
        });



    }


    @Override
    public void onClick(View view) {
        StrictMode.enableDefaults();
        StringBuilder urlBuilder;
        ListView listview = findViewById(R.id.shop_list);

        Intent keyIntent = getIntent();
        String a;
        switch (view.getId()){
            case R.id.shop_next:


                page = Integer.parseInt(pageText.getText().toString()) + 1;
                pageText.setText(Integer.toString(page));
                urlBuilder = new StringBuilder("https://openapi.11st.co.kr/openapi/OpenApiService.tmall");
                try {
                    urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=fedbeea5efc8f687da2910636c778f1b");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println(urlBuilder);

                try {
                    urlBuilder.append("&" + URLEncoder.encode("apiCode","UTF-8") + "=ProductSearch");
                    System.out.println("success1");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println(urlBuilder);
        /*
        try {
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            System.out.println("success2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        */
                if(keyIntent.getStringExtra("catalog") != null) {
                    try {
                        urlBuilder.append("&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode(keyIntent.getStringExtra("catalog"), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(urlBuilder);
                try {
                    urlBuilder.append("&" + URLEncoder.encode("pageNum","UTF-8") + "=" + URLEncoder.encode(Integer.toString(page), "UTF-8"));
                    System.out.println("success2");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println(urlBuilder);

                try {
                    urlBuilder.append("&" + URLEncoder.encode("pageSize","UTF-8") + "=10");
                    System.out.println("success3");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                System.out.println(urlBuilder);
                a = urlBuilder.toString();
                System.out.println(a);

                try {
                    URL url = new URL(a);
                    System.out.println("success4");


                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = factory.newDocumentBuilder();

                    Document doc = documentBuilder.parse(url.openStream());

                    Element element = doc.getDocumentElement();
                    NodeList nameList = element.getElementsByTagName("ProductName");
                    NodeList imageList = element.getElementsByTagName("ProductImage");
                    NodeList priceList = element.getElementsByTagName("ProductPrice");
                    NodeList urlList = element.getElementsByTagName("DetailPageUrl");




                    shopData = new ArrayList<>();
                    System.out.println("success5");

                    int n = nameList.getLength();
                    System.out.print(n);
                    for(int i = 0; i < n; i++){
                        System.out.print(i);
                        String nameValue = nameList.item(i).getFirstChild().getNodeValue();
                        String imageValue = imageList.item(i).getFirstChild().getNodeValue();
                        String priceValue = priceList.item(i).getFirstChild().getNodeValue();
                        String urlValue = urlList.item(i).getFirstChild().getNodeValue();
                        System.out.println(nameValue);



                        //Drawable drawable = LoadImageFromWebOperations( "https://t1.daumcdn.net/cfile/tistory/1946B11A4C5606ED3C" );
                        //System.out.println(itemValue);

                        SearchItem searchItem = new SearchItem(nameValue, priceValue, imageValue, null, null,null, null, urlValue);
                        shopData.add(searchItem);
                    }

                    SearchAdapter adapter = new SearchAdapter(this, R.layout.item_search, shopData);
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

                break;
            case R.id.shop_back:
                if(page == 1){
                    break;
                }
                page = Integer.parseInt(pageText.getText().toString()) - 1;
                pageText.setText(Integer.toString(page));
                urlBuilder = new StringBuilder("https://openapi.11st.co.kr/openapi/OpenApiService.tmall");
                try {
                    urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=fedbeea5efc8f687da2910636c778f1b");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println(urlBuilder);

                try {
                    urlBuilder.append("&" + URLEncoder.encode("apiCode","UTF-8") + "=ProductSearch");
                    System.out.println("success1");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println(urlBuilder);
        /*
        try {
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            System.out.println("success2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        */
                if(keyIntent.getStringExtra("catalog") != null) {
                    try {
                        urlBuilder.append("&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode(keyIntent.getStringExtra("catalog"), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(urlBuilder);
                try {
                    urlBuilder.append("&" + URLEncoder.encode("pageNum","UTF-8") + "=" + URLEncoder.encode(Integer.toString(page), "UTF-8"));
                    System.out.println("success2");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println(urlBuilder);

                try {
                    urlBuilder.append("&" + URLEncoder.encode("pageSize","UTF-8") + "=10");
                    System.out.println("success3");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println(urlBuilder);
                a = urlBuilder.toString();
                System.out.println(a);

                try {
                    URL url = new URL(a);
                    System.out.println("success4");


                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = factory.newDocumentBuilder();

                    Document doc = documentBuilder.parse(url.openStream());

                    Element element = doc.getDocumentElement();
                    NodeList nameList = element.getElementsByTagName("ProductName");
                    NodeList imageList = element.getElementsByTagName("ProductImage");
                    NodeList priceList = element.getElementsByTagName("ProductPrice");
                    NodeList urlList = element.getElementsByTagName("DetailPageUrl");




                    shopData = new ArrayList<>();
                    System.out.println("success5");

                    int n = nameList.getLength();
                    System.out.print(n);
                    for(int i = 0; i < n; i++){
                        System.out.print(i);
                        String nameValue = nameList.item(i).getFirstChild().getNodeValue();
                        String imageValue = imageList.item(i).getFirstChild().getNodeValue();
                        String priceValue = priceList.item(i).getFirstChild().getNodeValue();
                        String urlValue = urlList.item(i).getFirstChild().getNodeValue();
                        System.out.println(nameValue);



                        //Drawable drawable = LoadImageFromWebOperations( "https://t1.daumcdn.net/cfile/tistory/1946B11A4C5606ED3C" );
                        //System.out.println(itemValue);

                        SearchItem searchItem = new SearchItem(nameValue, priceValue, imageValue, null, null,null, null, urlValue);
                        shopData.add(searchItem);
                    }

                    SearchAdapter adapter = new SearchAdapter(this, R.layout.item_search, shopData);
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

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
