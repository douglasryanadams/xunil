package io.xunil.web.presentation.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created on 7/3/16.
 */
@XmlRootElement
public class PublicKey {

    private String xAsString;
    private String yAsString;

    public String getxAsString() {
        return xAsString;
    }

    public void setxAsString(String xAsString) {
        this.xAsString = xAsString;
    }

    public String getyAsString() {
        return yAsString;
    }

    public void setyAsString(String yAsString) {
        this.yAsString = yAsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicKey publicKey = (PublicKey) o;
        return Objects.equals(xAsString, publicKey.xAsString) &&
                Objects.equals(yAsString, publicKey.yAsString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xAsString, yAsString);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PublicKey{");
        sb.append("xAsString='").append(xAsString).append('\'');
        sb.append(", yAsString='").append(yAsString).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
