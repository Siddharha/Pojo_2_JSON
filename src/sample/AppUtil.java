package sample;

public class AppUtil {

    String JsonFromPojo(String strClass){
        String[] stringLines = strClass.split("\\R", 3);

        String className = checkNameAfterAndBefore(stringLines[0],"class","{");
        String veriableNameList = checkNameAfterAndBefore(stringLines[1],"String",";");
        return veriableNameList;
    }

    private String checkNameAfterAndBefore(String strFLine, String afterWords, String beforeWords){
        System.out.println(strFLine.lastIndexOf(afterWords) + afterWords.length()+1);
        return strFLine.substring(strFLine.lastIndexOf(afterWords) + afterWords.length()+1,strFLine.lastIndexOf(beforeWords)).trim();
    }

    private String checkNameAfterAndBefore(String strFLine, String afterWords){
        System.out.println(strFLine.lastIndexOf(afterWords) + afterWords.length()+1);
        return strFLine.substring(strFLine.lastIndexOf(afterWords) + afterWords.length()+1).trim();
    }
}

