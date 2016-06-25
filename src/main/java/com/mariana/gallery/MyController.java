package com.mariana.gallery;

import com.mariana.gallery.exeptions.FileErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/")
public class MyController {
    static final int DEFAULT_GALLERY_ID = -1;

    private Map<String, String> files = new HashMap<String, String>();


    @Autowired
    private GalleryService galleryService;

    @RequestMapping("/")
    public String onIndex(Model model) {
        List<String> img = new ArrayList<>();
        img.clear();
        img.add("https://cdn3.artstation.com/p/assets/images/images/002/458/843/large/nivanh-chanthara-test-175.jpg?1461960570");
        img.add("https://cdn0.artstation.com/p/assets/images/images/002/449/700/large/nivanh-chanthara-test-174.jpg?1461864109");
        img.add("https://cdn3.artstation.com/p/assets/images/images/002/435/115/large/nivanh-chanthara-test-173.jpg?1461699348");
        img.add("https://cdn0.artstation.com/p/assets/images/images/002/760/744/large/nivanh-chanthara-test-180-02.jpg?1465399592");
        img.add("https://cdn2.artstation.com/p/assets/images/images/001/824/086/large/nivanh-chanthara-robot-recap-03b.jpg?1453311002");
        img.add("https://cdn0.artstation.com/p/assets/images/images/001/928/956/large/nivanh-chanthara-test-154.jpg?1454712456");
        img.add("https://cdn2.artstation.com/p/assets/images/images/001/963/098/large/nivanh-chanthara-test-157.jpg?1455222895");

        model.addAttribute("file_name", img.toArray());


        return "/upload_art";
    }

//    @RequestMapping(value = "/upload_art", method = RequestMethod.POST)
//    public String onAddPhoto(Model model, @RequestParam MultipartFile picture) {
//        if (picture.isEmpty())
//            throw new FileErrorException();
//
//        try {
//           picture.getBytes();
//            return "result";
//        } catch (IOException e) {
//            throw new FileErrorException();
//        }
//    }

    @RequestMapping("/gallery/{id}")
    public String listUserGallerys(@PathVariable(value = "id") long galleryId, Model model) {
        UserGallery gallery = (galleryId != DEFAULT_GALLERY_ID) ? galleryService.findUserGallery(galleryId) : null;

        model.addAttribute("gallerys", galleryService.listUserGallerys());
        model.addAttribute("currentGallery", gallery);
        model.addAttribute("pictures", galleryService.listUserGallerys());
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String pictureAdd(@RequestParam("file") MultipartFile file) throws IOException {
        // UserGallery userGallery = (galleryId != DEFAULT_GALLERY_ID) ? galleryService.findUserGallery(galleryId) : null;

        Picture picture = new Picture(file.getBytes());

        galleryService.addPicture(picture);

        //   model.addAttribute("gallerys", galleryService.listUserGallerys());
        //  model.addAttribute("picture", galleryService.listPictures(null));
        return "artist_gallery";
    }

    @RequestMapping(value = "/gallery/add", method = RequestMethod.POST)
    public String groupAdd(@RequestParam String name, Model model) {
        galleryService.addUserGallery(new UserGallery(name));
        model.addAttribute("pictures", galleryService.listUserGallerys());
        return "index";
    }

}
