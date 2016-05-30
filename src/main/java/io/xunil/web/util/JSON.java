package io.xunil.web.util;

import io.xunil.web.exception.UserInputException;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created on 5/29/16.
 */
public class JSON {

    public static <T> T getObject(String s, Class<T> intendedClass) {
        try {
            JAXBContext c = JAXBContextFactory.createContext(new Class[]{intendedClass}, null);
            Unmarshaller um = c.createUnmarshaller();
            um.setProperty(UnmarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            um.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
            StreamSource ss = new StreamSource(new StringReader(s));
            return um.unmarshal(ss, intendedClass).getValue();
        }
        catch (JAXBException e) {
            throw new UserInputException("Get Object from JSON String failed.", e);
        }
    }

    public static <T> String getString(T object) {
        try {
            JAXBContext c = JAXBContextFactory.createContext(new Class[]{object.getClass()}, null);
            Marshaller m = c.createMarshaller();
            m.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            m.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter sw = new StringWriter();
            m.marshal(object, sw);
            return sw.toString();
        }
        catch (JAXBException e) {
            throw new UserInputException("Get JSON String from Object Failed.", e);
        }
    }
}
