package pl.sdacademy.recruitement.dto;

import lombok.Data;

@Data
public class ErrorResponse {

    private Long locationId;
    private String cause;
}
