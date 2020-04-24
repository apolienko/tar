package tar;

import java.io.*;
import java.util.*;


public class Tar {

    public void spliter(File split) throws IOException {

        workDirectory = split.getPath();
        int numberOfFiles = namesOfFiles.keySet().size();

        try (BufferedReader br = new BufferedReader(new FileReader(workDirectory))) {
            for (int i = 0; i < numberOfFiles; i++) {
                String nowLine = br.readLine();
                File file = new File(workDirectory + "/" + nowLine + "-restored");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
                int numOfLines = namesOfFiles.get(nowLine);
                for (int j = 0; j < numOfLines; j++) {
                    nowLine = br.readLine();
                    writer.write(nowLine);
                }
                br.readLine();
                writer.close();
            }
        }

    }

    public void connect(File total, List<File> inputFilesName) {

        try{
            FileWriter fw = new FileWriter(total.getPath());
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
                    writer.write("Файл " + file + " содержит " + count + " строк.");
                    writer.newLine();
                    namesOfFiles.put(String.valueOf(file), count);
                }
            }
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private String separator = File.separator;

    private String workDirectory = "src" + separator + "main" + separator + "resources";

    private HashMap<String, Integer> namesOfFiles;

}
