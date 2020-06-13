package com.company;


import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
      ObjectContext objectContext = new ObjectContext("com.company");
      objectContext.Init();
      DataServise dataServise = objectContext.GetComponent(DataServise.class);
      dataServise.ProcessData("TEST");
    }
}
