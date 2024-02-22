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
            if(!file.isDirectory()){
                writeToFile(readLines(file));
            }else{
                listFiles(file.getName());
            }
        }

    }

    @Override
    public List<File> listFiles(String rootDir) {
        return Stream.of(Objects.requireNonNull(new File(rootDir).listFiles()))
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> lines;
        try (Scanner grepReader = new Scanner(inputFile)) {
            lines = new ArrayList<>();
            while (grepReader.hasNextLine()) {
                lines.add(grepReader.nextLine());
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
        File out = new File(rootPath + '/' + outFile);
        //out.createNewFile(); // if file already exists will do nothing
        if(out.createNewFile()){
            try(FileWriter grepWriter = new FileWriter(outFile)) {
                for (String line : lines) {
                    grepWriter.write(line);
                }
            }
        }else{
            logger.error("File doesn't exist");
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
        ///home/centos/dev/jarvis_data_eng_vlabliuk/core_java/grep
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try{
            javaGrepImp.process();
        }catch(Exception ex){
            javaGrepImp.logger.error("Error: Unable to process", ex);
        }
    }
}
