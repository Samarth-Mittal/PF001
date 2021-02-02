package com.example.IMDb_rating.controller;

import com.example.IMDb_rating.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private MovieInfoService movieInfoService;

    @ResponseBody
    @RequestMapping(value = "home/{movie}", method = RequestMethod.GET)
    public String home(@PathVariable String movie) {
        String rating=movieInfoService.getMovieRating(movie);
        return rating;
    }

}
