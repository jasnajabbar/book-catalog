package com.bookcatalog.util;

import com.bookcatalog.dto.BookEnumParam;

public class ResponseUtil {

    private BookEnumParam bookEnumParam;

    public ResponseUtil(BookEnumParam bookEnumParam) {
        this.bookEnumParam = bookEnumParam;
    }

    public static double getProgressPercentage(int pagesRead, int totalPages){
        return (pagesRead*100.0)/totalPages;
    }
    public static String getReadingStatus(int pagesRead,int totalPages){
        String message;
        if(pagesRead==0){
            message="NOT STARTED";
        }else if (pagesRead < totalPages){
            message="IN PROGRESS";
        }else{
            message="COMPLETED";
        }
        return message;
    }
    public static boolean isRead(int pagesRead,int totalPages){
        String value=getReadingStatus(pagesRead,totalPages);
        return value == "COMPLETED" ? true : false;
    }
}
