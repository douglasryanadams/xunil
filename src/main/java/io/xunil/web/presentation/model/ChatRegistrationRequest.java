package io.xunil.web.presentation.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created on 7/3/16.
 */
@XmlRootElement
public class ChatRegistrationRequest {
    private String randomSeed;
    private String publicKey;

    public String getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(String randomSeed) {
        this.randomSeed = randomSeed;
    }
    
    public String getPublicKey() {
        return publicKey;
    }
    
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRegistrationRequest that = (ChatRegistrationRequest) o;
        return Objects.equals(randomSeed, that.randomSeed) &&
                Objects.equals(publicKey, that.publicKey);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(randomSeed, publicKey);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChatRegistrationRequest{");
        sb.append("randomSeed='").append(randomSeed).append('\'');
        sb.append(", publicKey='").append(publicKey).append('\'');
        sb.append('}');
        return sb.toString();
    }
    
}
