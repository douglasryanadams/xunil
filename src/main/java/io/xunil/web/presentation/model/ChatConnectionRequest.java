package io.xunil.web.presentation.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created on 7/31/16.
 */
@XmlRootElement
public class ChatConnectionRequest {
    private String from;
    private String connectWith;
    
    public String getFrom() {
        return from;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public String getConnectWith() {
        return connectWith;
    }
    
    public void setConnectWith(String connectWith) {
        this.connectWith = connectWith;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatConnectionRequest that = (ChatConnectionRequest) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(connectWith, that.connectWith);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(from, connectWith);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChatConnectionRequest{");
        sb.append("from='").append(from).append('\'');
        sb.append(", connectWith='").append(connectWith).append('\'');
        sb.append('}');
        return sb.toString();
    }
    
}
