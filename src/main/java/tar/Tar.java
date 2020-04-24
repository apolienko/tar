package tar;

import java.io.*;
import java.util.*;


public class Tar {

    public void connect(File total, List<File> inputFilesName) {

        workDirectory = total.getPath();

        try{
            FileWriter fw = new FileWriter(workDirectory);
            BufferedWriter writer = new BufferedWriter(fw);
            for (File file : inputFilesName) {
                writer.write(String.valueOf(file));
                writer.newLine();
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    Integer count = 0;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                        count++;
                    }
                    writer.write("Файл " + file + " содержит " + count + " строк(и).");
                    writer.newLine();
                    namesOfFiles.put(String.valueOf(file), count);
                }
            }
            writer.close();
            //Создание файла, внутри которого находятся имена файлов и кол-во строк
            File fileOfNames = new File(total.getParent() + "\\fileOfNames.txt");
            fileOfNames.createNewFile();
            FileWriter fw2 = new FileWriter(total.getParent() + "\\fileOfNames.txt");
            BufferedWriter writer2 = new BufferedWriter(fw2);
            for (Map.Entry<String, Integer> pair : namesOfFiles.entrySet()) {
                writer2.write(pair.getKey() + " " + pair.getValue());
                writer2.newLine();
            }
            writer2.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void spliter(File split) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(split.getParent() + "\\fileOfNames.txt" ))) {
            String nowLine;
            while ((nowLine = br.readLine()) != null) {
                String[] fileNameAndLines = nowLine.split(" ");
                namesOfFiles.put(fileNameAndLines[0], Integer.parseInt(fileNameAndLines[1]));
            }
        }

        workDirectory = split.getPath();

        int countOfFiles = namesOfFiles.keySet().size();

        try (BufferedReader br = new BufferedReader(new FileReader(workDirectory))) {
            for (int i = 0; i < countOfFiles; i++) {
                String nowLine = br.readLine();
                File resroredFile = new File(nowLine + "-restored");
                resroredFile.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(resroredFile.getPath()));
                int numOfLines = namesOfFiles.get(nowLine);
                for (int j = 0; j < numOfLines; j++) {
                    nowLine = br.readLine();
                    writer.write(nowLine);
                    writer.newLine();
                }
                br.readLine();
                writer.close();
            }
        }

    }

    private String workDirectory;

    private Map<String, Integer> namesOfFiles = new HashMap<String, Integer>();

}
