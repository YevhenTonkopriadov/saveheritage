package ua.gov.mkip.saveheritage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.mkip.saveheritage.input.ImageDownloadInput;
import ua.gov.mkip.saveheritage.models.Record;
import ua.gov.mkip.saveheritage.models.User;
import ua.gov.mkip.saveheritage.services.ImageServises;
import ua.gov.mkip.saveheritage.services.RecordService;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;
    private final ImageServises imageServises;

    @RequestMapping("/records")
    public String records(Model model) {
        model.addAttribute("records", recordService.findAllRecordsCurrentUser());
        return "records";
    }

    @RequestMapping("/addrecord")
    public String addrecord(Model model) {
        model.addAttribute("record", new Record());
        return "addrecord";
    }

    @PostMapping(path = "/saveReord")
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

    @RequestMapping(path = "/deleterecord/{recordId}")
    public String removePerson(@PathVariable("recordId") Long recordId, Model model) {
        recordService.delete(recordId);
        model.addAttribute("records", recordService.findAllRecordsCurrentUser());
        return "records";
    }

    @RequestMapping("/editrecord/{recordId}")
    public String editPerson(@PathVariable("recordId") Long recordId, Model model) {
        model.addAttribute("record", recordService.findOne(recordId));
        return "addrecord";
    }

    @RequestMapping("/recordimages/{recordId}")
    public String phohorecord(@PathVariable("recordId") Long recordId, Model model) {
        model.addAttribute("images", imageServises.findAllImagesOfCurrentRecord(recordId));
        ImageDownloadInput imageDownloadInput = new ImageDownloadInput();
        imageDownloadInput.setRecordId(recordId);
        model.addAttribute("imageDownloadInput", imageDownloadInput);
        return "recordimages";
    }

    @PostMapping(path = "/savefile")
    public String upload(@Valid @ModelAttribute ImageDownloadInput imageDownloadInput, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "recordimages";
        }
        if (imageServises.saveImage(imageDownloadInput)) {
            model.addAttribute("records", recordService.findAllRecordsCurrentUser());
            return "records";
        } else {
            return "recordimages";
        }
    }
}
