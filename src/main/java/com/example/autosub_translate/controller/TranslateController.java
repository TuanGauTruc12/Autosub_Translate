package com.example.autosub_translate.controller;

import com.example.autosub_translate.models.Item;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import org.json.JSONArray;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TranslateController implements Runnable {

    private String fileName;
    private String fileDirectory;
    private JProgressBar prcbLoading;
    private List<Item> listItem;
    private String languegeCode;
    private int check;
    private JFrame view;
    private int width, height;

    public TranslateController(String fileName, JProgressBar prcbLoading, String languegeCode, int check, String fileDirectory, JFrame view, int width, int height) {
        this.fileName = fileName;
        this.prcbLoading = prcbLoading;
        this.languegeCode = languegeCode;
        this.check = check;
        this.fileDirectory = fileDirectory;
        listItem = new ArrayList<>();
        this.view = view;
        this.height = height;
        this.width = width;
    }

    public void translateAuto() throws InterruptedException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        try {
            FileInputStream fileInputStream = new FileInputStream(fileDirectory + fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String str;
            List<String> list = new ArrayList<>();
            while ((str = reader.readLine()) != null) {
                if (str.isEmpty()) {
                    list.clear();
                } else {
                    if (!str.isEmpty()) {
                        list.add(str);
                        if (list.size() == 3) {
                            listItem.add(new Item(list.get(0), list.get(1), list.get(2)));
                        }
                    }
                }
            }
            getItems(restTemplate, entity);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getItems(RestTemplate restTemplate, HttpEntity<String> entity) throws IOException {
        int length = listItem.size();
        int index = 0;
        int num = 1;
        StringBuffer sb = new StringBuffer();

        sb.append(fileName.substring(0, fileName.length() - 4));
        String at = sb.toString();
        String suffixFile = "." + languegeCode + ".srt";
        String save = at + suffixFile;

        File file = new File(fileDirectory, save);
        while (file.exists()) {
            save = at + (num++) + suffixFile;
            file = new File(fileDirectory, save);
        }
        if ((listItem.size()) > 0) {
            BufferedWriter fw = new BufferedWriter(new FileWriter(fileDirectory + save));
            for (int i = 0; i < length; i++) {
                try {
                    index = i + 1;
                    fw.write(listItem.get(i).getItem1());//STT
                    fw.newLine();
                    fw.write(listItem.get(i).getItem2());//Time
                    fw.newLine();
                    if (check == 0) {
                        fw.write(listItem.get(i).getItem3());//Languege Source
                        fw.newLine();
                    } else if (check == -1) {
                        return;
                    }
                    String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl=" + languegeCode + "&dt=t&q=" + listItem.get(i).getItem3();
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
                            new ParameterizedTypeReference<String>() {
                    });

                    JSONArray jsonArray1 = new JSONArray(responseEntity.getBody());
                    JSONArray jsonArray2 = jsonArray1.getJSONArray(0);
                    JSONArray jsonArray3 = jsonArray2.getJSONArray(0);

                    fw.write(jsonArray3.getString(0));
                    fw.newLine();
                    fw.newLine();

                    double phanTram = ((double) i / length) * 100;
                    prcbLoading.setValue((int) phanTram);
                    fw.flush();
                    Thread.sleep(150);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TranslateController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            fw.close();
            prcbLoading.setValue(100);
        }
    }

    @Override
    public void run() {
        try {
            translateAuto();
            if (prcbLoading.getValue() == 100) {
                view.setSize(width, height + 100);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TranslateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TranslateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
