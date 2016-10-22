package ese.protocolo.model;

import java.time.LocalDateTime;

/**
 * Created by francisco on 10/22/16.
 */
public class Variable {
    private LocalDateTime timestamp;
    private int value;

    public Variable(LocalDateTime timestamp, int valor) {
        this.timestamp = timestamp;
        this.value = valor;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return ""+ value;
    }
}
