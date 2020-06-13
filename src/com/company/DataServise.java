package com.company;

 public @AppComponent class DataServise {
   @Inject public WebProvider webProvider;
   @Inject public DataProvider dataProvider;


    public String ProcessData(String s) {
        String data = dataProvider.GetData().toString();
        data = data +" "+ s;
        return webProvider.SendData(data);

    }

}
