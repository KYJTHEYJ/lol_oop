package system.exceptions;

import champion.Champion;

public class MinusHpException extends RuntimeException {
    public MinusHpException(Champion champion) {
        System.err.println("해당 < " + champion.getName() + " > 챔피언의 hp가 - 수치로 설정 되고 있습니다");
        champion.setHp(0);
    }
}
