package system.exceptions;

import champion.Champion;

public class MinusMpException extends RuntimeException {
    public MinusMpException(Champion champion) {
        System.err.println("해당 < " + champion.getName() + " > 챔피언의 mp가 - 수치로 설정 되고 있습니다");
        champion.setMp(0);
    }
}
