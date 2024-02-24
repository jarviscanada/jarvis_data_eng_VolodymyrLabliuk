package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepImp implements JavaGrep{

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    @Override
    public void process() throws IOException {
        List<File> files = listFiles(rootPath);

        for (File file : files){
            List<String> lines = readLines(file);
            if(!lines.isEmpty())
                writeToFile(lines);
        }

    }

    public void listFilesRecursive(File rootDir, List<File> files) {
        List<File> rootDirFiles = Stream.of(Objects.requireNonNull(rootDir.listFiles()))
                .collect(Collectors.toList());
        for (File file : rootDirFiles) {
            if (file.isDirectory()) {
                listFilesRecursive(file, files);
            } else {
                files.add(file);
            }
        }
    }

    @Override
    public List<File> listFiles(String rootPath) {
        List<File> files = new ArrayList<>();
        File rootDir = new File(rootPath);
        listFilesRecursive(rootDir, files);
        return files;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> lines;
        try (Scanner grepReader = new Scanner(inputFile)) {
            lines = new ArrayList<>();
            while (grepReader.hasNextLine()) {
                String nextLine = grepReader.nextLine();
                if(containsPattern(nextLine)) {
                    lines.add(nextLine);
                }
            }
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return lines;
    }

    @Override
    public boolean containsPattern(String line) {
        return line.matches(regex);
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        File out = new File(rootPath + outFile);
            try(FileWriter grepWriter = new FileWriter(out)) {
                for (String line : lines) {
                    grepWriter.append(line).append(String.valueOf('\n'));
                }
            }
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    public static void main(String[] args) {
        if(args.length < 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try{
            javaGrepImp.process();
        }catch(Exception ex){
            javaGrepImp.logger.error("Error: Unable to process", ex);
        }
    }
}
