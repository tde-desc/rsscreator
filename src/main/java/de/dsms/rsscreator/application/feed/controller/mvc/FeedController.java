package de.dsms.rsscreator.application.feed.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedController {

    private static final String PATH = "/feed/";

    @GetMapping(PATH + "index")
    public String index() {
        return PATH + "index";
    }
}
