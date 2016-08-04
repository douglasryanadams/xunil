package io.xunil.web.presentation.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created on 7/31/16.
 */
@XmlRootElement
public class ErrorMessage {
    private String error;
    
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessage that = (ErrorMessage) o;
        return Objects.equals(error, that.error);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(error);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorMessage{");
        sb.append("error='").append(error).append('\'');
        sb.append('}');
        return sb.toString();
    }
    
}
