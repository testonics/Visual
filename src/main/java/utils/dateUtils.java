package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateUtils {

    public String getTimeStamp(){
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }
}
