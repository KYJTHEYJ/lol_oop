package system.exceptions;

import champion.Champion;

public class DeathException extends RuntimeException {
    private Champion champion;

    public DeathException(Champion champion, String message) {
        super(message);

        this.champion = champion;
    }

    public Champion getChampion() {
        return champion;
    }
}
