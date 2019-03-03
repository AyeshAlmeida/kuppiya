package hms.cpaas.kuppiya.persistence.mongo.notification;

public enum NotificationStatus {
    SCHEDULED{
        @Override
        boolean isFinished() {
            return false;
        }
    }, PENDING {
        @Override
        boolean isFinished() {
            return false;
        }
    }, FINISHED {
        @Override
        boolean isFinished() {
            return true;
        }
    };

    abstract boolean isFinished();
}
