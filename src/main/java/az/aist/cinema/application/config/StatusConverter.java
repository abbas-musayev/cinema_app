package az.aist.cinema.application.config;

import az.aist.cinema.application.enums.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        return Status.fromValue(value);
    }
}
