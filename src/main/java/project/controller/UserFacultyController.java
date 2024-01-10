package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.domain.User;
import project.service.ApplicationService;
import project.service.FacultyService;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserFacultyController {
    @Autowired
    private UserService userService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value="userFaculties", method = RequestMethod.GET)
    public String userFaculties(HttpServletRequest request) {
        request.setAttribute("user", (User) request.getSession().getAttribute("user"));
        request.setAttribute("faculties", facultyService.readAll());
        return "userFaculties";
    }
}
