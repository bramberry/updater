package by.vsu.bramberry.updatechecker.model.exception;

public class FileStoreException extends RuntimeException {
    public FileStoreException(String message, Throwable ex) {
        super(message, ex);
    }

    public FileStoreException(String message) {
        super(message);
    }
}
