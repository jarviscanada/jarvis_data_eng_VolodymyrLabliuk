package ca.jrvs.apps.practice;

public class RegeExcImp implements RegexExc {

    @Override
    public boolean matchJpeg(String filename) {
        return filename.matches("(?i)^.*/.jpe?g$");
    }

    @Override
    public boolean matchIp(String ip) {
        return ip.matches("^(([0-9]{1,3}/.){3})([0-9]{1,3})");
    }

    @Override
    public boolean isEmptyLine(String line) {
        return line.matches("^[ \t]*$");
    }

    public static void main(String[] args) {

    }
}
