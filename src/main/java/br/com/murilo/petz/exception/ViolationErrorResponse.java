package br.com.murilo.petz.exception;

public class ViolationErrorResponse {

    private String fieldName;
    private String message;

    public ViolationErrorResponse(final String fieldName, final String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}

