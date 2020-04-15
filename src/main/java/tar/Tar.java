package tar;

import java.io.*;
import java.util.ArrayList;


public class Tar {

    public void split() {

    }

    public void connect(File total, ArrayList<File> inputFilesName) {

        File connectingFile = new File(workDirectory + separator + total);
        try{
            FileWriter fw = new FileWriter(connectingFile.getAbsoluteFile());
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

}
