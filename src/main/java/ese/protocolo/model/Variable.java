package ese.protocolo.model;

import java.time.LocalDateTime;

/**
 * Created by francisco on 10/22/16.
 */
public class Variable {
    private String timestamp;
    private int id;
    private int value;
    private String img;

    public Variable(String timestamp, int valor, int id) {
        this.timestamp = timestamp;
        this.value = valor;
        this.img = "/assets/sensor.png";
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    @Override
    public String toString() {
        return ""+ value;
    }
}
