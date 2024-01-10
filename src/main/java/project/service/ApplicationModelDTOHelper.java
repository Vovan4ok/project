package project.service;

import project.domain.ApplicationModel;

public class ApplicationModelDTOHelper {
    public static ApplicationModel createEntityForUser(int place, String name, String surname, int maths,
                                                int english, int physics, float certificate, float rating) {
        ApplicationModel applicationModel = new ApplicationModel();
        applicationModel.setPlace(place);
        applicationModel.setApplicantName(name);
        applicationModel.setApplicantSurname(surname);
        applicationModel.setMathsMark(maths);
        applicationModel.setEnglishMark(english);
        applicationModel.setPhysicsMark(physics);
        applicationModel.setCertificateMark(certificate);
        applicationModel.setRatingMark(rating);
        return applicationModel;
    }

    public static ApplicationModel createEntityForAdmin(Integer id, String name, String surname, int maths, int english, int physics,
                                                        float certificate, float rating, String facultyName) {
        ApplicationModel applicationModel = new ApplicationModel();
        applicationModel.setApplicationId(id);
        applicationModel.setApplicantName(name);
        applicationModel.setApplicantSurname(surname);
        applicationModel.setMathsMark(maths);
        applicationModel.setEnglishMark(english);
        applicationModel.setPhysicsMark(physics);
        applicationModel.setCertificateMark(certificate);
        applicationModel.setRatingMark(rating);
        applicationModel.setFacultyName(facultyName);
        return applicationModel;
    }
}

