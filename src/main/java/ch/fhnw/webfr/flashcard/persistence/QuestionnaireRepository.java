package ch.fhnw.webfr.flashcard.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;

public interface QuestionnaireRepository extends MongoRepository<Questionnaire, String> { // #1: Das QuestionnaireRepository ist nun ein MongoRepository. Dabei muss die Entität, hier Quesionnaire, und der Typ der ID, hier String, angegeben werden.
	List<Questionnaire> findByTitle(String title);    // #2: Das MongoRepository stellt alle elementaren CRUD-Operation automatisch bereit. Hier wird eine neue Methode definiert, um Questionnaire-Entitäten nach dem Titel suchen zu können.
}