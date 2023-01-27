package com.example.autosub_translate.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JLabel;

public class AutosubController {

    private JLabel txtLoading;
    private String txtPath;

    public AutosubController(JLabel txtLoading, String txtPath) {
        this.txtLoading = txtLoading;
        this.txtPath = txtPath;
    }

    public void autosub() throws IOException {
        StringBuffer sb = new StringBuffer();
        
        sb.append(txtPath.subSequence(0, txtPath.length() - 4));
        sb.append(".vi.srt");
        ProcessBuilder build_test
                = // new ProcessBuilder("/usr/local/bin/autosub","autosub -F srt -o D:\\\\subttitle\\\\input.srt -S ko -D ko D:\\\\input.mp4");
            //    new ProcessBuilder("cmd.exe", "/c", "autosub -F srt -o "+ sb.toString() +" -S ko -D ko " + txtPath);
                new ProcessBuilder("/usr/local/bin/autosub", "autosub -F srt -o "+ sb.toString() +" -S ko -D ko " + txtPath);

        build_test.redirectErrorStream(true);
        Process p = build_test.start();
        printResults(p);
    }

    private void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            txtLoading.setText(line);
        }
    }

}
