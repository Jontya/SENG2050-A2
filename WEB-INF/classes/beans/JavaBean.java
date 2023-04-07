package beans;

import java.io.Serializable;

public class JavaBean implements Serializable{
    private String test;

    public JavaBean(){}

    public String getTest(){
        return test;
    }

    public void setTest(String test){
        this.test = test;
    }
}