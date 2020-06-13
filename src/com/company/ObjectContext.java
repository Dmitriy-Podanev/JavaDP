package com.company;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



public class ObjectContext {

    Set<Object> instancesObject = new HashSet<>();

    public ObjectContext(String PackageName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Reflections reflections = new Reflections(PackageName);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(AppComponent.class);//Нашел все классы
        if(annotated.size()==0) throw new NullPointerException("Классы не найдены");
        for (Iterator<Class<?>> iterator = annotated.iterator(); iterator.hasNext(); ) {
            Class<?> next = iterator.next();
            var constructors = next.getConstructor();
            Object o = constructors.newInstance();

            instancesObject.add(o);

        }
    }


    public void Init() throws  IllegalAccessException{
        Object temp = null;
        for (Iterator<Object> iterator = instancesObject.iterator(); iterator.hasNext(); ) {
            temp = iterator.next();
            Field[] field = temp.getClass().getFields();

            if (field.length == 0)
                continue;

            Set<Field> fieldSet = new HashSet<>(Arrays.asList(field));

            for (Iterator<Field> iter = fieldSet.iterator(); iter.hasNext(); ) {
                var fielder = iter.next();
                for (Annotation annotation : fielder.getAnnotations()) {
                    if (annotation.annotationType() != Inject.class)
                        continue;

                    for (Iterator<Object> iterator2 = instancesObject.iterator(); iterator2.hasNext(); ) {
                        Object next = iterator2.next();
                        if (fielder.getType() == next.getClass()) {
                           fielder.set(temp, next);


                        }
                    }

                }
            }
        }
    }

    public <T> T GetComponent(Object Clam){
        if(Clam==null) throw new NullPointerException("Класс не найден");
        T temp = null;
       // Object temp2 = null;
        for (Iterator<Object> iterator = instancesObject.iterator(); iterator.hasNext(); ) {
            Object next = iterator.next();
            if (Clam == next.getClass()) {
               // temp2 = next;
                temp = (T) next;
                break;
            }
        }
        return temp;
    }
}
