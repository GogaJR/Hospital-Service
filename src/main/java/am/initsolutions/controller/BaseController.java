package am.initsolutions.controller;



import am.initsolutions.models.userParentModel.ParentModel;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class BaseController {

    @ModelAttribute("currentUser")
    public ParentModel login(HttpServletRequest request) {
        ParentModel parentModel=(ParentModel) request.getSession().getAttribute("user");
        return parentModel;
    }

    @ModelAttribute("registered")
    public Boolean registerToDoctor(HttpServletRequest request) {
        return (Boolean) request.getSession().getAttribute("registered");
    }

    @ModelAttribute("added")
    public Boolean addMedicine(HttpServletRequest request) {
        return (Boolean) request.getSession().getAttribute("added");
    }

    @ModelAttribute("exists")
    public Boolean doctorExists(HttpServletRequest request) {
        return (Boolean) request.getSession().getAttribute("exists");
    }
}
