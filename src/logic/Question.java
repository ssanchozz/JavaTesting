package logic;

public class Question {
    public static final int NUMBER_OF_ANSWERS = 4;

    private int questionId;
    private String questionText = "";
    private Answer[] answers= new Answer[NUMBER_OF_ANSWERS];
    private boolean isAnswered = false;
    private Answer chosenAnswer = new Answer();

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public Answer getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(Answer chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

    public String toString() {
        return questionId + " " + questionText;
    }
}
