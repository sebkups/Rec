package pl.sdacademy.recruitement.exception;

import lombok.Getter;

@Getter
public class LocationNotFoundException extends RuntimeException {

    private Long locationId;

    public LocationNotFoundException(Long id) {
        super("Location with id " + id + " not found");
        locationId = id;
    }
}
