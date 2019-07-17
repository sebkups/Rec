package pl.sdacademy.recruitement.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pl.sdacademy.recruitement.entity.Location;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileDataService {

    private static final String RETAIL_VALUE = "HANDEL";

    @Value("classpath:${data.file.name}")
    private Resource resourceFile;

    public List<Location> fetchRetailLocations() {
        try {
            InputStream is = resourceFile.getInputStream();
            CSVParser parser = CSVFormat.DEFAULT
                    .withDelimiter(";".charAt(0))
                    .parse(new InputStreamReader(is, Charset.forName("Cp1250")));

            return parser.getRecords().stream()
                    .map(this::map)
                    .filter(l -> RETAIL_VALUE.equalsIgnoreCase(l.getLocationType()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error when loading data from file");
        }
    }

    private Location map(CSVRecord record) {
        Location location = new Location();
        location.setCompanyName(record.get(0));
        location.setLocationName(record.get(1));
        location.setCity(record.get(2));
        location.setStreet(record.get(3));
        location.setBuildingNumber(record.get(4));
        location.setLocationType(record.get(5));
        location.setRoomNumber(record.get(6));
        location.setAddressDescription(record.get(7));
        return location;
    }
}
