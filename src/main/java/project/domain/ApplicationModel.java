package project.domain;

public class ApplicationModel {
    private int place;
    private String applicantName;
    private String applicantSurname;
    private int mathsMark;
    private int englishMark;
    private int physicsMark;
    private float certificateMark;
    private float ratingMark;

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantSurname() {
        return applicantSurname;
    }

    public void setApplicantSurname(String applicantSurname) {
        this.applicantSurname = applicantSurname;
    }

    public int getMathsMark() {
        return mathsMark;
    }

    public void setMathsMark(int mathsMark) {
        this.mathsMark = mathsMark;
    }

    public int getEnglishMark() {
        return englishMark;
    }

    public void setEnglishMark(int englishMark) {
        this.englishMark = englishMark;
    }

    public int getPhysicsMark() {
        return physicsMark;
    }

    public void setPhysicsMark(int physicsMark) {
        this.physicsMark = physicsMark;
    }

    public float getCertificateMark() {
        return certificateMark;
    }

    public void setCertificateMark(float certificateMark) {
        this.certificateMark = certificateMark;
    }

    public float getRatingMark() {
        return ratingMark;
    }

    public void setRatingMark(float ratingMark) {
        this.ratingMark = ratingMark;
    }

}
