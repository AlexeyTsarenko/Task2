package github.SsItAcademy.Task2;

import org.reflections.Reflections;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ParserConfigurationException, IOException, SAXException, NoSuchFieldException {

        DocumentBuilderFactory dbf;
        DocumentBuilder db ;
        Document doc;

        dbf = DocumentBuilderFactory.newInstance();
        db  = dbf.newDocumentBuilder();
        doc = db.parse(new File("config.xml"));
        NodeList nList = doc.getElementsByTagName("config");

        Reflections reflections = new Reflections("github.SsItAcademy.Task2");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(MyAnnotation.class);
        List list = new ArrayList<>();

        for (Class<?> controller : annotated) {
            Constructor<?> cons = controller.getConstructor();
            list.add(cons.newInstance());
       }
        for(Object o : list){
            System.out.println(o.getClass().toString());
            Field field = o.getClass().getDeclaredField("a");
            System.out.println(o.getClass().getDeclaredField("a").get(o));
            field.set(o,(String)nList.item(0).getTextContent());
            System.out.println(o.getClass().getDeclaredField("a").get(o));
        }
    }
}
