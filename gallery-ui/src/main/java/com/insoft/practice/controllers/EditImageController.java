package com.insoft.practice.controllers;

import com.insoft.practice.bl.services.ImageService;
import com.insoft.practice.bl.services.ImageTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CommonsLog
@RequiredArgsConstructor
public class EditImageController {

    private final ImageService imageService;
    private final ImageTagService imageTagService;

    //    @RequestMapping("/editTag")
//    public String editAddTag(@RequestParam("text") String tagName, @RequestParam("editTag") Long id, HttpServletRequest request) {
//        imageTagService.storeTag(tagName, id);
//        String referer = request.getHeader("Referer");
//        System.out.println("edit" + id);
//        return "redirect:"+ referer;
//    }

    @RequestMapping("/edit")
    public String editImage(Model md, @RequestParam(name = "edit") Long edit) {
        md.addAttribute("entity", imageService.getImageById(edit));
        md.addAttribute("service", imageService);
        return "editImagePage";
    }

    @RequestMapping("/editTag")
    public String editAddTag(@RequestParam("text") String tagName, @RequestParam("editTag") Long id, Model md) {
        imageTagService.storeTag(tagName, id);
        md.addAttribute("entity", imageService.getImageById(id));
        md.addAttribute("service", imageService);
        return "editImagePage";
    }

    @RequestMapping("/deleteTag")
    public String deleteTag(@RequestParam(name = "deleteTag") Long id, Model md) {
        imageTagService.deleteStoredTags(imageService.getImageById(id));
        md.addAttribute("entity", imageService.getImageById(id));
        md.addAttribute("service", imageService);
        return "editImagePage";
    }

    @RequestMapping("/changename")
    public String changeName(@RequestParam(name = "nametext") String name, @RequestParam("editnumber") Long id, Model md) {
        imageService.setImageName(id, name);
        md.addAttribute("entity", imageService.getImageById(id));
        md.addAttribute("service", imageService);
        return "editImagePage";
    }

    @RequestMapping("/deleteImage")
    public String deleteImage(@RequestParam("imageid") Long id, Model md) {
        imageService.deleteImage(id);
        md.addAttribute("msg", "Image #" + id + " was deleted from database.");
        return "imageDeleted";
    }
}