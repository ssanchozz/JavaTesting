package logic;

public class TestLog {
    private String testTheme;
    private String question;
    private int questionId;
    private String answer;
    private String answerCorrect;
    private int tryNumber;

    public String getTestTheme() {
        return testTheme;
    }

    public void setTestTheme(String testTheme) {
        this.testTheme = testTheme;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getTryNumber() {
        return tryNumber;
    }

    public void setTryNumber(int tryNumber) {
        this.tryNumber = tryNumber;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public String toString() {
        String s = "Test theme: " + testTheme + "\nTry number: " + tryNumber+ "\nQuestion id: " + questionId + ". " + question +
                "\nYou answered: " + answer + "\nYour answer is " + answerCorrect + "\n\n";
        return s;
    }
}
