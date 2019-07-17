package pl.sdacademy.recruitement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sdacademy.recruitement.entity.Location;
import pl.sdacademy.recruitement.service.FileDataService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileDataServiceTest {

    @Autowired
    private FileDataService fileDataService;

    @Test
    public void loadFileDataTest() throws Exception {
        // given

        // when
        List<Location> fileData = fileDataService.fetchRetailLocations();

        // then
        assertThat(fileData).isNotEmpty();
        assertThat(fileData).hasSize(828);
    }
}
