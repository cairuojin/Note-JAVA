package com.gjsyoung.controller;

import com.gjsyoung.domain.Spittle;
import com.gjsyoung.service.SpittleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * @author cairuojin
 * @create 2019-01-14 22:59
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    public SpittleController(SpittleService spittleService) {
        this.spittleService = spittleService;
    }

    @Autowired
    private SpittleService spittleService;

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String showRegistrationForm(Model model){
        Spittle spittle = new Spittle();
        spittle.setMessage("hello");
        spittle.setTime(new Date());
        spittle.setLatitude(10.0);
        spittle.setLongitude(30.0);
        model.addAttribute("spitter", spittle);
        return "registerForm";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String processRegistration(@Valid Spittle spittle, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute(errors);
            return "registerForm";
        }
        spittleService.save(spittle);
        return "redirect:/spitters/" + spittle.getMessage();
    }


    @RequestMapping("/sendRedirect")
    public String sendRedirect(RedirectAttributes model, HttpServletRequest request){
        Spittle spittle = new Spittle();
        spittle.setTime(new Date());
        spittle.setMessage("hello");
        spittle.setLongitude(30.0);
        spittle.setLatitude(10.0);
        model.addFlashAttribute("Spittle",spittle);
        System.out.println();
        return "redirect:/spittles/getRedirect";
    }

    @RequestMapping("/getRedirect")
    public String getRedirect(Model model, HttpServletRequest request){
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        String spittle1 = request.getParameter("Spittle");
        if(model.containsAttribute("Spittle")){

        } else {
            Spittle spittle = new Spittle();
            model.addAttribute("Spittle",spittle);
        }
        return "test123";
    }



}