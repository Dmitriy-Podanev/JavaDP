package com.company;

@AppComponent public class WebProvider {
    public String SendData(String data){
        System.out.println(data);
        return data;

    }
}
