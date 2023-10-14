package api.report;

public interface StudentSummaryInterface {


    public String getProfessorName();
    public String getDisciplineCode();
    public String getDiscipline();

    public float getMeanUnits();

    public void setProfessorName(String professorName);

    public void setDisciplineCode(String disciplineCode);

    public void setDiscipline(String discipline);

    public void setMeanUnits(float meanUnits);
}
