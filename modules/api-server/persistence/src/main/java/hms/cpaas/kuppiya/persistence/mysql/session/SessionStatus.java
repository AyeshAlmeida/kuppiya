package hms.cpaas.kuppiya.persistence.mysql.session;

public enum SessionStatus {
    ONLINE,
    UNKNOWN,
    COMPLETED {
        @Override
        public boolean isCompleted() {
            return true;
        }
    }, ABORTED;

    public boolean isCompleted() {
        return false;
    }
}
