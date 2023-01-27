package com.example.autosub_translate.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import org.json.JSONArray;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TranslateController {

    private String input;
    private JLabel txtLoading;

    public TranslateController(String input, JLabel txtLoading) {
        this.input = input;
        this.txtLoading = txtLoading;
    }

    public void translateAuto() {
        String fileName = input;
        StringBuffer sb = new StringBuffer();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        for (int i = 0; i < fileName.trim().length() - 4; i++) {
            sb.append(fileName.charAt(i));
        }
        sb.append(".vi.srt");
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(sb.toString()));
            FileInputStream fileInputStream = new FileInputStream(fileName);
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
                            fw.write(list.get(0));
                            fw.newLine();
                            fw.write(list.get(1));
                            fw.newLine();

                            String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl=vi&dt=t&q=" + list.get(2);

                            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
                                    new ParameterizedTypeReference<String>() {
                            });

                            JSONArray jsonArray1 = new JSONArray(responseEntity.getBody());
                            JSONArray jsonArray2 = jsonArray1.getJSONArray(0);
                            JSONArray jsonArray3 = jsonArray2.getJSONArray(0);

                            fw.write(jsonArray3.getString(0));
                            fw.newLine();
                            fw.newLine();
                        }
                    }
                }
            }
            txtLoading.setText("Dich thanh cong");
            fw.flush();
            fw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
