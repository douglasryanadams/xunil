package io.xunil.web.presentation.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created on 5/28/16.
 */
@XmlRootElement
public class ChatMessage {
    private String type;
    private String to;
    private String from;
    private String content; // See if I can make this binary later
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getTo() {
        return to;
    }
    
    public void setTo(String to) {
        this.to = to;
    }
    
    public String getFrom() {
        return from;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(to, that.to) &&
                Objects.equals(from, that.from) &&
                Objects.equals(content, that.content);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(type, to, from, content);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChatMessage{");
        sb.append("type='").append(type).append('\'');
        sb.append(", to='").append(to).append('\'');
        sb.append(", from='").append(from).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
    
}
