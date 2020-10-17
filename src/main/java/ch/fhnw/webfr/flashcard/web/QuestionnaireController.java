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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public String findById(@PathVariable String id, Model model) {
		Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);
		model.addAttribute("questionnaire", questionnaire.get());
		return "questionnaires/show";
    }
    
    @PostMapping(value=".../questionnaires?form")
    public Questionnaire postMethodName(@RequestBody Questionnaire entity) {
        //TODO: process POST request
        
        return entity;
    }
}
