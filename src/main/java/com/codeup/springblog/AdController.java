package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdController {
    private final AdRepository adDao;

    public AdController(AdRepository adDao){
        this.adDao = adDao;
    }

    @GetMapping("/ads")
    public String getAd(Model model){


        model.addAttribute("ads", adDao.findAll());


        return "adPage";
    }

    @GetMapping("/ads/single")
    public String getSingleAd(Model model){


        model.addAttribute("ad", adDao.findByTitle("$0 MUST SEE FJ45"));


        return "singleAdPage";
    }

    @GetMapping("/ads/create")
    public String showCreateForm(Model model) {
        model.addAttribute("newAd", new Ad());
        return "createAd";
    }

    @PostMapping("/ads/create")
    public String create(
            @ModelAttribute Ad newAd
    ) {
//        Ad ad = new Ad();
//        ad.setTitle(title);
//        ad.setDescription(description);


        // save the ad...
        adDao.save(newAd);

        return "redirect:/ads";
    }


}
