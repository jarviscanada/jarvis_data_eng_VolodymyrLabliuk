package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class JavaGrepImp implements JavaGrep{

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    @Override
    public void process() throws IOException {
        Stream<File> steamFiles = listFiles(rootPath);
        steamFiles.flatMap(this::readLines).forEach(line -> {
            try {
                writeToFile(line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void listFilesRecursive(File rootDir, List<File> files) {
        Stream.of(Objects.requireNonNull(rootDir.listFiles()))
                .forEach(file -> {
                    if (file.isDirectory()) {
                        listFilesRecursive(file, files);
                    } else {
                        files.add(file);
                    }
                });
    }

    @Override
    public Stream<File> listFiles(String rootPath) {
        Stream<File> files;
        try {
            Stream<Path> stream = Files.walk(Paths.get(rootPath));
            files = stream.filter(Files::isRegularFile)
                    .map(Path::toFile).onClose(stream::close);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return files;

    }

    @Override
    public Stream<String> readLines(File inputFile) {
        Stream<String> stream;
        try {
            stream = Files.lines(Paths.get(inputFile.getAbsolutePath())).filter(this::containsPattern);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stream;
    }

    @Override
    public boolean containsPattern(String line) {
        return line.matches(regex);
    }

    @Override
    public void writeToFile(String line) throws IOException {
        File out = new File(outFile);
            try(FileWriter grepWriter = new FileWriter(out, true)) {
                grepWriter.append(line).append(String.valueOf('\n'));
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
