package io.xunil.web.presentation.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created on 5/28/16.
 */
@XmlRootElement
public class ChatMessage {
    private String to;
    private String content; // See if I can make this binary later


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
        return Objects.equals(to, that.to) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, content);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChatMessage{");
        sb.append("to='").append(to).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
