package dh.clinica.exceptions;

public class ResourceNotFoundException extends Exception{
    private String resource;
    private String field;
    private Integer valueField;

    public ResourceNotFoundException(String resource, String field, Integer valueField) {
        super(String.format("%s no encontrado con %s : '%s'", resource, field, valueField));
        this.resource = resource;
        this.field = field;
        this.valueField = valueField;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
