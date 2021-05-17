package com.example.courseWork.validators;

public class StringValidator implements Validator {
    @Override
    public void validate(Object obj) throws IllegalArgumentException{
        String str = (String) obj;
        if(str.length() <=2)throw new IllegalArgumentException("Введені данні закороткі!");
        if(str.length() >=40)throw new IllegalArgumentException("Введені данні занадто довгі!");
        char[] chars = new char[]{'!','@','#','%','^','&','*','(',')','{','}','[',']','<','>','/','\\','|','?',' '};
        for(char ch : str.toCharArray()){
            for(char ch1 : chars){
                if(ch1 == ch)throw new IllegalArgumentException("Введені данні містять недозволені символи!");
            }
        }
    }
}
