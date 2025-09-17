package model;

public class PlanningModel {
    public PlanningModel(){

    }
    public String concatenateDescription(String[] competences) {
    StringBuilder description = new StringBuilder();
    if (competences != null && competences.length > 0) {
        for (String competence : competences) {
            description.append(competence);
            description.append(", ");
        }
        description.setLength(description.length() - 2);
    }
    return description.toString();
}



}
