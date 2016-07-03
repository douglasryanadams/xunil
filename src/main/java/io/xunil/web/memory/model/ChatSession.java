package io.xunil.web.memory.model;

import io.xunil.web.presentation.model.PublicKey;

import javax.websocket.Session;
import java.util.Objects;

/**
 * Created on 6/5/16.
 */

public class ChatSession {
    private String uuid;
    private String publicName; // TODO: use later
    private PublicKey publicKey;
    private Session session;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatSession that = (ChatSession) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(publicName, that.publicName) &&
                Objects.equals(publicKey, that.publicKey) &&
                Objects.equals(session, that.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, publicName, publicKey, session);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChatSession{");
        sb.append("uuid='").append(uuid).append('\'');
        sb.append(", publicName='").append(publicName).append('\'');
        sb.append(", publicKey=").append(publicKey);
        sb.append(", session=").append(session);
        sb.append('}');
        return sb.toString();
    }

}
