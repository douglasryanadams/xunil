package io.xunil.web.presentation.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Created on 7/13/16.
 */
@XmlRootElement
public class RosterMessage {
    private String senderId;
    private String type;
    private List<String> visibleRoster;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getVisibleRoster() {
        return visibleRoster;
    }

    public void setVisibleRoster(List<String> visibleRoster) {
        this.visibleRoster = visibleRoster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RosterMessage that = (RosterMessage) o;
        return Objects.equals(senderId, that.senderId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(visibleRoster, that.visibleRoster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderId, type, visibleRoster);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RosterMessage{");
        sb.append("senderId='").append(senderId).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", visibleRoster=").append(visibleRoster);
        sb.append('}');
        return sb.toString();
    }
}
