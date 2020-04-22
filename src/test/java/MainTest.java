
import github.SsItAcademy.Task2.Main;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {
    @Test
    public void getStrFromConfigTest() throws ParserConfigurationException, SAXException, IOException {

        String actual = Main.getStrFromConfig().trim();
        String expected = "someParameter";
        assertEquals(expected, actual);
    }
    @Test
    public void getObjAndSetFieldsTest() throws IllegalAccessException, SAXException, InstantiationException,
                                                IOException, NoSuchMethodException, ParserConfigurationException,
                                                InvocationTargetException, NoSuchFieldException {
        String expected = "class of object: class github.SsItAcademy.Task2.SomeAnotherClass\n" +
                "before reflection set: sthAnother\n" +
                "after reflection set: someParameter\n" +
                "class of object: class github.SsItAcademy.Task2.SomeClass\n" +
                "before reflection set: sth\n" +
                "after reflection set: someParameter\n";
        String actual = Main.getObjAndSetFields();
        assertEquals(expected, actual);
    }

}
