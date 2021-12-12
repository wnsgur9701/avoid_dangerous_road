package com.example.avoiddangerousroad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/findRoad")
    public String findRoad(HttpServletRequest request, Model model) {
        String startLatitude = request.getParameter("startLatitude");
        String startLongitude = request.getParameter("startLongitude");
        String endLatitude = request.getParameter("endLatitude");
        String endLongitude = request.getParameter("endLongitude");
        String[] preferences = request.getParameterValues("preference[]");
        for (String preference : preferences) {
            System.out.println("preference = " + preference);
        }

        return "findRoad";
    }

}
