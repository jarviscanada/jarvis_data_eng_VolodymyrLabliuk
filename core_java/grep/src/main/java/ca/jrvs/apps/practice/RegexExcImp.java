package ca.jrvs.apps.practice;

public class RegexExcImp implements RegexExc {

    @Override
    public boolean matchJpeg(String filename) {
        return filename.matches("(?i)^.*\\.jpe?g$");
    }

    @Override
    public boolean matchIp(String ip) {
        return ip.matches("([0-9]{1,3}\\.){3}[0-9]{1,3}");
    }

    @Override
    public boolean isEmptyLine(String line) {
        return line.matches("^[ \t]*$");
    }

    public static void main(String[] args) {
        RegexExcImp regeExcImp = new RegexExcImp();
        System.out.println("isEmptyLine test");
        System.out.println(regeExcImp.isEmptyLine(""));
        System.out.println(regeExcImp.isEmptyLine(" "));
        System.out.println(regeExcImp.isEmptyLine("\t"));
        System.out.println(regeExcImp.isEmptyLine("1"));
        System.out.println(regeExcImp.isEmptyLine(" 1"));
        System.out.println("matchIp test");
        System.out.println(regeExcImp.matchIp("2.2.2.2"));
        System.out.println(regeExcImp.matchIp("2.0.2.2"));
        System.out.println(regeExcImp.matchIp("222.21.2.23"));
        System.out.println(regeExcImp.matchIp("2.2.2"));
        System.out.println("matchJpeg test");
        System.out.println(regeExcImp.matchJpeg("123.jpg"));
        System.out.println(regeExcImp.matchJpeg("2.jpeg"));
        System.out.println(regeExcImp.matchJpeg("222.JPeg"));
        System.out.println(regeExcImp.matchJpeg("2.JPG"));
        System.out.println(regeExcImp.matchJpeg("2.JPGR"));
        System.out.println(regeExcImp.matchJpeg("2"));
    }
}
