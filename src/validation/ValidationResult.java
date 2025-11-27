package validation;

import java.util.List;

public class ValidationResult {

    private List<String> messages;

    public ValidationResult(List<String> messages) {
        this.messages = messages;
    }

    public boolean isValid() {
        return messages.isEmpty();
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
