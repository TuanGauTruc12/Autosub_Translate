package com.example.autosub_translate.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;

public class AutosubController implements Runnable {

    private JProgressBar prcbLoading;
    private String languegeCode;
    private String fileName;
    private String fileDirectory;
    private JFrame view;
    private int width, height;

    public AutosubController(JProgressBar prcbLoading, String fileName, String languegeCode, String fileDirectory, JFrame view, int width, int height) {
        this.prcbLoading = prcbLoading;
        this.fileDirectory = fileDirectory;
        this.fileName = fileName;
        this.languegeCode = languegeCode;
        this.view = view;
        this.width = width;
        this.height = height;
    }

    public void autosub() throws IOException {
        ProcessBuilder build_test = null;
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
            String[] cmd = {"/usr/local/bin/autosub", "-S", languegeCode, "-D", languegeCode, fileDirectory + fileName};
            build_test = new ProcessBuilder(cmd);
        }else if (OS.indexOf("win") >= 0) {
            String command = "autosub -S " + languegeCode + " -D " + languegeCode + " \"" + fileDirectory + fileName + "\"";        
            build_test = new ProcessBuilder("cmd.exe", "/c", command);
        }
        if(build_test != null){
            build_test.redirectErrorStream(true);
            Process p = build_test.start();
            printResults(p);
        } else {
            JOptionPane.showMessageDialog(null, "Your Laptop/Desktop doesn't support it");
        }
    }

    private void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.replace("|", "");
            line = line.replace("ETA:", "");
            line = line.replace("  ", "");
            line = line.replace("#", "");
            line = line.replace("--:--:--", "");
            line = line.replace("0:00:00", "");
            if (line.contains("Time:0:")) {
                line = line.replace(line.substring(line.length() - 12, line.length()), "");
            }
            if (line.contains("0:0")) {
                line = line.replace(line.subSequence(line.length() - 8, line.length()), "");
            }
            line = line.replace("%", "");
            if (line.contains("Converting speech regions to FLAC files:")) {
                String str = line.replace("Converting speech regions to FLAC files:", "");
                str = str.replace(" ", "");
                prcbLoading.setValue(Integer.parseInt(str));
                if (prcbLoading.getValue() == 100) {
                    prcbLoading.setValue(0);
                }
            } else if (line.contains("Performing speech recognition:")) {
                String str = line.replace("Performing speech recognition:", "");
                str = str.replace(" ", "");
                prcbLoading.setValue(Integer.parseInt(str));
            }
        }
        prcbLoading.setValue(100);
    }

    @Override
    public void run() {
        try {
            autosub();
            if (prcbLoading.getValue() == 100) {
                view.setSize(width, height + 105);
            }

        } catch (IOException ex) {
            Logger.getLogger(AutosubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
