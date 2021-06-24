package eu.mhsl.openquiz.screen;

import eu.mhsl.openquiz.OpenQuiz;
import eu.mhsl.openquiz.out.Terminal;
import eu.mhsl.openquiz.question.Question;
import eu.mhsl.openquiz.question.QuestionSet;
import eu.mhsl.openquiz.question.QuizDifficulty;

import java.util.ArrayList;

public class ScreenQuizCreator implements Screen {

    public Screen display() {
        Terminal t = OpenQuiz.getTerminal();

        String name = t.text("Quizname:");
        String description = t.text("Quiz Beschreibung:");

        QuestionSet custom = new QuestionSet(name, description, QuizDifficulty.NORMAL);

        /*
        Create questions...
         */
        do {

            String quest = t.text("Frage:");

            /*
            Create answers...
             */
            ArrayList<String> answerlist = new ArrayList<String>();
            do {

                answerlist.add(t.text("Antwort:"));

            } while(t.bool("Weitere Antwortmöglichkeit hinzufügen?"));

            Question question = new Question(quest, answerlist.toArray(new String[0]), t.number("Welche antwort war die richtige? (von 0 beginnend)"));
            custom.add(question);

        } while(t.bool("Möchtest du eine weitere Frage hinzufügen?"));

        OpenQuiz.getFileHandler().importQuestionSet(custom);

        return new ScreenQuizList();
    }
}
