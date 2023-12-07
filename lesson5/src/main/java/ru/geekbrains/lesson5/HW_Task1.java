package ru.geekbrains.lesson5;
import java.io.*;

/*1. Написать функцию, создающую резервную копию всех файлов в директории во вновь созданную папку ./backup*/

public class HW_Task1 {
    private static final String BACKUP_PATH = "C:/Users/DDDok/JavaCore/lesson5/target/backup";
    private static final String SOURCE_PATH = "C:/Users/DDDok/JavaCore/lesson5/src/main";

    public static void main(String[] args) throws IOException{
        makeBackup(new File(SOURCE_PATH), new File(BACKUP_PATH));
    }

    public static void makeBackup(File sourceFile, File destinationFile) throws IOException
    {
        if (sourceFile.isDirectory())
        {
            if (!destinationFile.exists())
            {
                destinationFile.mkdirs();
            }

            String filesList[] = sourceFile.list();

            for (String file : filesList) {
                File srcFile = new File(sourceFile, file);
                File destFile = new File(destinationFile, file);

                makeBackup(srcFile, destFile);
            }
        }
        else
        {
            try (FileOutputStream fileOutputStream = new FileOutputStream(destinationFile)){
                int f;
                try(FileInputStream fileInputStream = new FileInputStream(sourceFile)){
                    while ((f = fileInputStream.read()) != -1){
                        fileOutputStream.write(f);
                    }
                }
            }
        }
    }
}
