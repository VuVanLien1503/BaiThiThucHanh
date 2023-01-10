package action;

import java.util.regex.Pattern;

public class MyRegex {
    private String patternEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private String patternName = "[a-zA-Z0-9_-]{3,15}$";
    private String patternText = "[a-zA-Z0-9\\d]{4,60}$";
    private String patternPhone = "^[0][0-9]{9}$";
    private String patternPassWord = "^[a-zA-Z0-9]{4,15}$";
    private String patternNumber="[0-9]+$";
    private String patternDouble="^[0-9.]+$";
    String patternBegin = "^[0-8]{1}$";

    public String getPatternBegin() {
        return patternBegin;
    }

    public void setPatternBegin(String patternBegin) {
        this.patternBegin = patternBegin;
    }

    public String getPatternText() {
        return patternText;
    }

    public void setPatternText(String patternText) {
        this.patternText = patternText;
    }

    public String getPatternDouble() {
        return patternDouble;
    }

    public void setPatternDouble(String patternDouble) {
        this.patternDouble = patternDouble;
    }

    public MyRegex() {
    }

    public MyRegex(String patternEmail, String patternName, String patternPhone, String patternPassWord) {
        this.patternEmail = patternEmail;
        this.patternName = patternName;
        this.patternPhone = patternPhone;
        this.patternPassWord = patternPassWord;
    }

    public String getPatternEmail() {
        return patternEmail;
    }

    public void setPatternEmail(String patternEmail) {
        this.patternEmail = patternEmail;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public String getPatternPhone() {
        return patternPhone;
    }

    public void setPatternPhone(String patternPhone) {
        this.patternPhone = patternPhone;
    }

    public String getPatternPassWord() {
        return patternPassWord;
    }

    public void setPatternPassWord(String patternPassWord) {
        this.patternPassWord = patternPassWord;
    }

    public String getPatternNumber() {
        return patternNumber;
    }

    public void setPatternNumber(String patternNumber) {
        this.patternNumber = patternNumber;
    }

    public  boolean regex(String input, String pattern) {
        return Pattern.compile(pattern)
                .matcher(input)
                .matches();
    }

}
