package ch.fhnw.webfr.flashcard.domain;

import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="questionnaires")    //#1: Diese Annotation definiert diese Klasse als MongoDB Dokument in der genannten Kollektion
public class Questionnaire {
    @Id                 // #2: Mit dieser Annotation wird folgende Property als ID festgelegt
	private String id;  // #3: Die ID einer MongoDB ist immer vom Typ String
	//@Size(min=2, max=10)
	private String  title;
    private String description;
    
    public void setId(String id) {
		this.id = id;
	}
    
    public String getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
    public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
