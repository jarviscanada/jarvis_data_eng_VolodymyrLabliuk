package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp implements JavaGrepLambda {

    @Override
    public void processWithStream() {
        Stream<File> steamFiles = listFilesWithStream(getRootPath());
        steamFiles.flatMap(this::readLinesWithStream).forEach(line -> {
            try {
                writeToFileWithStream(line);
            } catch (IOException e) {
                logger.error("Error: Unable to process", e);
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Stream<File> listFilesWithStream(String rootPath) {
        try {
            Stream<Path> stream = Files.walk(Paths.get(rootPath));
            return stream.filter(Files::isRegularFile)
                    .map(Path::toFile).onClose(stream::close);
        } catch (IOException e) {
            logger.error("Error: Unable to list files", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Stream<String> readLinesWithStream(File inputFile) {
        Stream<String> stream;
        try {
            stream = Files.lines(Paths.get(inputFile.getAbsolutePath())).filter(this::containsPattern);
        } catch (IOException e) {
            logger.error("Error: Unable to read lines", e);
            throw new RuntimeException(e);
        }
        return stream;
    }

    @Override
    public void writeToFileWithStream(String line) throws IOException {
        File out = new File(getOutFile());
        try(FileWriter grepWriter = new FileWriter(out, true)) {
            grepWriter.append(line).append(String.valueOf('\n'));
        }
    }

    public static void main(String[] args) {
        if(args.length < 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        BasicConfigurator.configure();

        JavaGrepLambda javaGrepLambdaImp = new JavaGrepLambdaImp();
        javaGrepLambdaImp.setRegex(args[0]);
        javaGrepLambdaImp.setRootPath(args[1]);
        javaGrepLambdaImp.setOutFile(args[2]);

        javaGrepLambdaImp.processWithStream();

    }
}
