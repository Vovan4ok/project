package project.dto;

import lombok.Getter;
import lombok.Setter;

public class MarksDTO {
    @Getter
    @Setter
    private Short mathsMark;

    @Getter
    @Setter
    private Short ukrainianMark;

    @Getter
    @Setter
    private Short englishMark;

    @Getter
    @Setter
    private Float certificateMark;
}
