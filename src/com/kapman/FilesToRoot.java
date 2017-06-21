package com.kapman;

import java.io.File;

public class FilesToRoot {

    private File root;

    public static void main(String[] args) {
        File dir = new File(args[0]);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Args not a directory");
            return;
        }

        FilesToRoot ftr = new FilesToRoot(dir);
    }

    private FilesToRoot(File dir) {
        this.root = dir;
        walk(dir.getAbsolutePath());
    }

    private void walk(String path) {
        try{
            File dir = new File(path);
            File[] list = dir.listFiles();

            System.out.println("Dir: " + dir.getAbsoluteFile());

            if (list == null) return;

            for (File f : list) {
                if (f.isDirectory()) {
                    walk(f.getAbsolutePath());
                }
                else {
                    f.renameTo(new File(root.getAbsolutePath() + "\\" + f.getName()));
                    System.out.println("File: " + f.getAbsoluteFile() + " move " + root.getAbsolutePath());
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
