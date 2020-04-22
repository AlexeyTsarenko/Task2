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
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
                                                  InstantiationException, ParserConfigurationException, IOException,
                                                  SAXException, NoSuchFieldException {
        System.out.println(getObjAndSetFields());
    }

    public static String getStrFromConfig() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbf;
        DocumentBuilder db;
        Document doc;

        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();
        doc = db.parse(new File("config.xml"));
        NodeList nList = doc.getElementsByTagName("config");
        return (String) nList.item(0).getTextContent();
    }

    public static String getObjAndSetFields() throws NoSuchFieldException, IllegalAccessException, InvocationTargetException,
                                                     InstantiationException, NoSuchMethodException, ParserConfigurationException,
                                                     SAXException, IOException {
        Reflections reflections = new Reflections("github.SsItAcademy.Task2");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(MyAnnotation.class);
        List list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (Class<?> controller : annotated) {
            Constructor<?> cons = controller.getConstructor();
            list.add(cons.newInstance());
        }
        for (Object o : list) {
            stringBuilder.append("class of object: " + o.getClass().toString() + "\n");
            Field field = o.getClass().getDeclaredField("a");
            stringBuilder.append("before reflection set: " + o.getClass().getDeclaredField("a").get(o) + "\n");
            field.set(o, getStrFromConfig());
            stringBuilder.append("after reflection set: " + o.getClass().getDeclaredField("a").get(o).toString().trim() + "\n");
        }
        return stringBuilder.toString();
    }
}
