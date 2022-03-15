package ua.gov.mkip.saveheritage.controllers;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ua.gov.mkip.saveheritage.services.RecordService;
import ua.gov.mkip.saveheritage.models.Record;
import ua.gov.mkip.saveheritage.models.User;

@Controller
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @RequestMapping ("/records")
    public String records (Model model){
        model.addAttribute("records", recordService.findAllRecordsCurrentUser());
        return "records";
    }

    @RequestMapping ("/addrecord")
    public String addrecord (Model model){
        model.addAttribute("record", new Record());
        return "addrecord";
    }

    @PostMapping (path = "/saveReord")
    public String createRecord(@Valid @ModelAttribute Record record, BindingResult bindingResult, Model model) {
        System.out.println(bindingResult.toString());
        if (bindingResult.hasErrors()) {
            return "addrecord";
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        record.setUser(user);
        recordService.save(record);
        model.addAttribute("records", recordService.findAllRecordsCurrentUser());
        return "records";
    }

    @RequestMapping(path = "/remove/{id}")
    public String removePerson(@PathVariable("id")Long id){
        recordService.delete(id);
        return "redirect:records";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") Long id, Model model){
        model.addAttribute("records", recordService.findAllRecordsCurrentUser());
        model.addAttribute("record",recordService.findOne(id));
        return "records";
    }
}
