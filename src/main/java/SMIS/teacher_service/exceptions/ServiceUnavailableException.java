package SMIS.teacher_service.exceptions;

public class ServiceUnavailableException extends  RuntimeException {
    public ServiceUnavailableException(String message) {
        super(message);
    }
}
