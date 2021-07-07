package edu.gatech.seclass.jobcompare6300;

public class Utils {

    public Utils() {
    }

    public boolean isEmptyNull(String s){
        if(s.length() == 0 || s == null){
            return true;
        } else{
            return false;
        }
    }

    public boolean containAlphabet(String s){
        if(s.matches(".*[a-zA-Z]+.*")){
            return true;
        }else {
            return false;
        }
    }

    public boolean isNumeric(String s){
        try {
            double d = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isInt(String s){
        try {
            double d = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
