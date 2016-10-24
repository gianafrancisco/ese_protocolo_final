package ese.protocolo.model;

import java.time.LocalDateTime;

/**
 * Created by francisco on 10/22/16.
 */
public class Variable {
    private String timestamp;
    private double value;
    private String img;

    public Variable(String timestamp, double valor) {
        this.timestamp = timestamp;
        this.value = valor;
        this.img = "/assets/sensor.png";
    }

    public String getTimestamp() {
        return timestamp;
    }

    public double getValue() {
        return value;
    }

    public String getImg() {
        return img;
    }

    @Override
    public String toString() {
        return ""+ value;
    }
}
