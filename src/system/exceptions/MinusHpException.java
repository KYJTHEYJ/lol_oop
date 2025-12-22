package system.exceptions;

public class MinusHpException extends RuntimeException {
    public MinusHpException(String message) {
       super(message);
    }

    public int getZeroHp() {
        return 0;
    }
}
