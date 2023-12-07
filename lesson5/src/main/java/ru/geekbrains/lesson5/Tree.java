package ru.geekbrains.lesson5;

import java.io.File;

/**
 * TODO: Доработать метод print, необходимо распечатывать директории и файлы
 */
public class Tree {

    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
        }

        if(file.isDirectory()){
            System.out.println(file.getName());
        } else {
            System.out.println(file.getName());
            return;
        }

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        int fileTotal = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory())
                subDirTotal++;
            else
                fileTotal++;
        }

        int subDirCounter = 0;
        int fileCounter = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory()){
                print(files[i], indent, subDirTotal == ++subDirCounter);
            } else {
                print(files[i], indent, fileTotal == ++fileCounter);
            }
        }
    }

}
