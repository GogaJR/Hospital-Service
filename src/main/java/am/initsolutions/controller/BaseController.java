package am.initsolutions.controller;


import am.initsolutions.models.usersParentModel.ParentModel;

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
}
