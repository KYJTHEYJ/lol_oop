package system.exceptions;

public class MinusMpException extends RuntimeException {
  public MinusMpException( String message) {
    super(message);
  }

  public int getZeroMp() {
    return 0;
  }
}
