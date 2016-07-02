package io.xunil.web.presentation.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created on 6/5/16.
 */

@XmlRootElement
public class ChatSession {
    private String id;
    private String publicKey;
    private String publicName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatSession that = (ChatSession) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(publicKey, that.publicKey) &&
                Objects.equals(publicName, that.publicName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publicKey, publicName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChatSession{");
        sb.append("id='").append(id).append('\'');
        sb.append(", publicKey='").append(publicKey).append('\'');
        sb.append(", publicName='").append(publicName).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
