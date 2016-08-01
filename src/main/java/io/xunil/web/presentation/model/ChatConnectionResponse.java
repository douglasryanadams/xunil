package io.xunil.web.presentation.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created on 7/31/16.
 */
@XmlRootElement
public class ChatConnectionResponse {
    private String answer;
    private String publicKey;
    private String connectedTo;
    
    public String getAnswer() {
        return answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public String getPublicKey() {
        return publicKey;
    }
    
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
    
    public String getConnectedTo() {
        return connectedTo;
    }
    
    public void setConnectedTo(String connectedTo) {
        this.connectedTo = connectedTo;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatConnectionResponse that = (ChatConnectionResponse) o;
        return Objects.equals(answer, that.answer) &&
                Objects.equals(publicKey, that.publicKey) &&
                Objects.equals(connectedTo, that.connectedTo);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(answer, publicKey, connectedTo);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChatConnectionResponse{");
        sb.append("answer='").append(answer).append('\'');
        sb.append(", publicKey=").append(publicKey);
        sb.append(", connectedTo='").append(connectedTo).append('\'');
        sb.append('}');
        return sb.toString();
    }
    
}
