package ch.fhnw.webfr.flashcard.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public String findById(@PathVariable String id, Model model) {
		if(questionnaireRepository.existsById(id)){
			Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);
			model.addAttribute("questionnaire", questionnaire.get());
			
			return "questionnaires/show";
		}

		return "404";
	}
	
	@RequestMapping(method = RequestMethod.GET, params = { "form" })
	public String getForm(Model model) {
		
		model.addAttribute("questionnaire", new Questionnaire());
		
		return "questionnaires/create";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Questionnaire questionnaire, BindingResult result) {
		if (result.hasErrors()) {
			return "questionnaires/create";
		} else {
			questionnaireRepository.save(questionnaire);
			
			return "redirect:questionnaires";
		
		}
	}

		
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String id) {

		if (questionnaireRepository.existsById(id)) {
			questionnaireRepository.deleteById(id);
			return "redirect:/questionnaires";
		} else {
			return "404";
		}
	}

	@RequestMapping(value="/{id}", method = RequestMethod.GET, params = { "update-form" })
	public String getUpdateForm(@PathVariable String id, Model model) {

		if(questionnaireRepository.existsById(id)){
			Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);
			model.addAttribute("questionnaire", questionnaire.get());
			
			return "questionnaires/update";
		}

		return "404";
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable String id, @Valid Questionnaire questionnaire, BindingResult result) {
		if (result.hasErrors()) {
			return "questionnaires/update";
		}
		if(questionnaireRepository.existsById(id)){
			Optional<Questionnaire> q = questionnaireRepository.findById(id);

			q.get().setTitle(questionnaire.getTitle());
			q.get().setDescription(questionnaire.getDescription());

			questionnaireRepository.save(q.get());

			return "redirect:/questionnaires";
		
		}
		return "404";

	}

}
