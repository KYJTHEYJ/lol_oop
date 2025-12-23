package system.exceptions;

import champion.Champion;

public class DeathException extends RuntimeException {

    public DeathException(Champion champion) {
        System.err.println("< " + champion.getName() + " > 은 사망 하였습니다!");
    }
}
