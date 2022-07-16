package ua.gov.mkip.craft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.mkip.craft.input.ImageDownloadInput;
import ua.gov.mkip.craft.models.Record;
import ua.gov.mkip.craft.models.UserCraftsMan;
import ua.gov.mkip.craft.services.ImageServises;
import ua.gov.mkip.craft.services.RecordService;
import ua.gov.mkip.craft.services.UserCraftsManService;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;
    private final ImageServises imageServises;
    private final UserCraftsManService userCraftsManService;

    @RequestMapping("/records")
    public String records(Model model) {
        model.addAttribute("records", recordService.findAll());
        return "records";
    }

    @RequestMapping("/addrecord")
    public String addrecord(Model model) {
        model.addAttribute("record", new Record());
        return "addrecord";
    }

   @PostMapping(path = "/saveReord")
    public String createRecord(@Valid @ModelAttribute Record record, BindingResult bindingResult, Model model) {
       if (bindingResult.hasErrors()) {
           return "addrecord";
       }
       UserCraftsMan userCraftsMan = (UserCraftsMan) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       record.setUserCraftsMan(userCraftsMan);
       recordService.save(record);
       model.addAttribute("records", recordService.findAll());
       return "records";
   }

    @RequestMapping(path = "/deleterecord/{recordId}")
    public String removePerson(@PathVariable("recordId") Long recordId, Model model) {
        recordService.delete(recordId);
        model.addAttribute("records", recordService.findAll());
        return "records";
    }

    @RequestMapping("/editrecord/{recordId}")
    public String editPerson(@PathVariable("recordId") Long recordId, Model model) {
        Optional <Record>  optionalRecord = recordService.findOne(recordId);
        if (optionalRecord.isPresent()) {
            model.addAttribute("record", optionalRecord.get());
            return "addrecord";
        } else return "index";
    }

    @RequestMapping("/recordimages/{recordId}")
    public String phohorecord(@PathVariable("recordId") Long recordId, Model model) {
        model.addAttribute("images", imageServises.findAll(recordId));
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
            model.addAttribute("records", recordService.findAll());
            return "records";
        } else {
            return "recordimages";
        }
    }
    @RequestMapping(path = "/recordviewer/{id}")
    public String recordviewer ( @PathVariable ("id")  Long id, Model model) {
        Optional<Record> record = recordService.findOne(id);
        if (record.isPresent()){
            model.addAttribute("record", record.get());
            model.addAttribute("userCraftsMan",userCraftsManService.findById(record.get().getUserCraftsMan().getId()).get());
            model.addAttribute("rcordImages", imageServises.findAll(record.get().getRecordId()));
            return "recordviewer";
        }
        return "/index";
    }
    @RequestMapping (path = "/userRecords/{userId}")
    public String userRecords (Model model, @PathVariable ("userId") Long userId){
        model.addAttribute("records", recordService.findAllByUserId(userId));
        return "records";
    }
}
