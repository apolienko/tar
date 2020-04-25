package tar;

import java.io.*;
import java.util.*;


public class Tar {

    public static void connect(File total, List<File> inputFilesName) throws IOException {

        String separator = File.separator;
        String workDirectory = total.getPath();
        Map<String, Integer> namesOfFiles = new HashMap<String, Integer>();

        for (File file : inputFilesName) {
            if (!file.exists()) {
                throw new IOException(file + " is not found");
            }
        }

        for (File file: inputFilesName) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                int count = 0;
                String line;
                while ((line = reader.readLine()) != null) {
                    count++;
                }
                namesOfFiles.put(file.toString(), count);
            }
        }

        FileWriter fw = new FileWriter(workDirectory);
        try (BufferedWriter writer = new BufferedWriter(fw)) {
            for (Map.Entry<String, Integer> pair : namesOfFiles.entrySet()) {
                String nameOfFile = pair.getKey();
                int numberOfLines = pair.getValue();
                try (BufferedReader reader = new BufferedReader(new FileReader(nameOfFile))) {
                    writer.write(nameOfFile + " " + numberOfLines);
                    writer.newLine();
                    for (int i=0; i < numberOfLines; i++) {
                        writer.write(reader.readLine());
                        writer.newLine();
                    }
                }
            }
        }
    }

    public static void spliter(File split) throws IOException {

        String separator = File.separator;
        String workDirectory = split.getPath();

        if (!split.exists()) {
            throw new IOException(split + " is not found");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(workDirectory))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fileNameAndLines = line.split(" ");
                String nameOfFile = fileNameAndLines[0];
                int numberOfLines = Integer.parseInt(fileNameAndLines[1]);
                File resroredFile = new File(nameOfFile + "-restored");
                resroredFile.createNewFile();
                FileWriter fw = new FileWriter(resroredFile);
                try (BufferedWriter writer = new BufferedWriter(fw)) {
                    for (int i=0; i < numberOfLines; i++) {
                        writer.write(reader.readLine());
                        writer.newLine();
                    }
                }
            }
        }
    }

}
