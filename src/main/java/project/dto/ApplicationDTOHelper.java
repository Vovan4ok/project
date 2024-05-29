package project.dto;

import project.domain.Application;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDTOHelper {
    public static List<ApplicationDTO> createList(List<Application> applications) {
        List<ApplicationDTO> applicationDTOS = new ArrayList<>();
        int place = 1;
        for(Application application : applications) {
            applicationDTOS.add(new ApplicationDTO(application, place));
            place++;
        }
        return applicationDTOS;
    }
}
