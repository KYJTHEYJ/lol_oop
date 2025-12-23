package system.exceptions;

import champion.Champion;

public class MinusDamageException extends RuntimeException {
    public MinusDamageException(Champion champion, int damage) {
        System.out.printf("해당 < " + champion.getName() + " > 챔피언이 대미지를 %d 수치로 받고 있습니다\n", damage);
    }

    public int getOneDamage() { return 1; }
}
