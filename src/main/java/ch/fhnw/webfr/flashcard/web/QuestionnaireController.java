package ch.fhnw.webfr.flashcard.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@Controller
@RequestMapping("/questionnaires")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {

        List<Questionnaire> questionnaires = questionnaireRepository.findAll();

        model.addAttribute("questionnaires", questionnaires);

        return "questionnaires/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void findById(@PathVariable String id, HttpServletResponse response, HttpServletRequest request)
            throws IOException {

        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);
        
		PrintWriter writer = response.getWriter();
		writer.append("<html lang='en'><head><title>Example</title></head><body>");
		writer.append("<h2>Questionnaire</h2>");
		
		if (questionnaire.isPresent()) {
			
			writer.append("<h3>" + questionnaire.get().getTitle() + "</h3>");
			writer.append("<p>" + questionnaire.get().getDescription() + "</p>");	
			
		} else {
			
			writer.append("<p><em>no questionnaire found</em></p>");
		}
		
		writer.append("<a href='" + request.getContextPath() + "/questionnaires'>Back</a>");
		
		writer.append("</body></html>");
    }
}
