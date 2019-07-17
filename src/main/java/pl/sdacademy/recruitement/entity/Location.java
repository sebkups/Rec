package pl.sdacademy.recruitement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "location")
@Setter
@Getter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String locationName;
    private String city;
    private String street;
    private String buildingNumber;
    private String roomNumber;
    private String locationType;
    private String addressDescription;
}
