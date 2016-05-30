package io.xunil.web.util;

import org.junit.Test;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * Created on 5/29/16.
 */
public class JSONTest {

    private final String jsonString = "{\"id\":123,\"name\":\"toaster\"}";
    private final TestObject jsonObject = new TestObject(123, "toaster");

    @Test
    public void testStringToObject() throws Exception {
        assertEquals(jsonObject, JSON.getObject(jsonString, TestObject.class));
    }

    @Test
    public void testObjectToString() throws Exception {
        // So that order won't matter in String
        assertEquals(jsonObject, JSON.getObject(JSON.getString(jsonObject), TestObject.class));
    }

    @XmlRootElement
    private static class TestObject {
        private Integer id;
        private String name;

        public TestObject() {}

        public TestObject(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestObject that = (TestObject) o;
            return Objects.equals(id, that.id) &&
                    Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("TestObject{");
            sb.append("id=").append(id);
            sb.append(", name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }

    }

}
