package ch.fhnw.webfr.flashcard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@Controller
@RequestMapping("/hello") // könnte durch value im RequestMapping ersetzt werden
public class HellloWordController {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody // Print Writer kann vermieden werden, dank ResponseBody (Konverter von Spring wird zur Verfügung gestellt)
    // ResponseBody versicht in dem vom Browser gewünschten Typ umzuwandeln, wenn nicht passt -> Error.
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name + "<br> You have " + questionnaireRepository.count() + " questionnaires in your repo.";
    }

}
