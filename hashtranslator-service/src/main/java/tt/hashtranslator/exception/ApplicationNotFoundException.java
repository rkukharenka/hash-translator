package tt.hashtranslator.exception;

public class ApplicationNotFoundException extends RuntimeException {

    public ApplicationNotFoundException(String id) {
        super(String.format("Application with id \"%s\" not found.", id));
    }

}
