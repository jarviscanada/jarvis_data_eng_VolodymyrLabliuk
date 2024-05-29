package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public interface JavaGrepLambda extends JavaGrep{
    /**
     * Top level search workflow
     */
    void processWithStream();

    /**
     * Traverse a given directory and return all files
     * @param rootDir input directory
     * @return files under the rootDir
     */
    Stream<File> listFilesWithStream(String rootDir);

    /**
     * Read a file and return all the lines
     *
     * Explain FileReader, BufferedReader, and character encoding
     *
     * @param inputFile file to be read
     * @return lines
     * @throws IllegalArgumentException if a given inputFile is not a file
     */
    Stream<String> readLinesWithStream(File inputFile);

    /**
     * Write lines to a file
     *
     * Explore: FileOutputStream, OutputStreamWriter, and BufferedWriter
     *
     * @param line matched line
     * @throws IOException if write failed
     */
    void writeToFileWithStream(String line) throws IOException;
}
