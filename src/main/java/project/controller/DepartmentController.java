package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.domain.Department;
import project.domain.User;
import project.service.DepartmentService;
import project.service.FacultyService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    FacultyService facultyService;

    Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @GetMapping(value="/departments")
    public String departments(@RequestParam("faculty_id") Integer faculty_id, HttpServletRequest request) {
        request.setAttribute("departments", departmentService.readAllByFaculty(facultyService.readById(faculty_id)));
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("role", user.getRole().toString());
        return "departments";
    }

    @GetMapping(value="/departments/{department_id}")
    public String department(@PathVariable Integer department_id, HttpServletRequest request) {
        request.setAttribute("department", departmentService.readById(department_id));
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("role", user.getRole().toString());
        return "department";
    }

    @GetMapping(value = "/addDepartment")
    public String addDepartment(HttpServletRequest request) {
        logger.info("Admin is on the addDepartment page.");
        request.setAttribute("departmentForm", new Department());
        request.setAttribute("faculties", facultyService.readAll());
        request.setAttribute("mode", "MODE_ADD");
        return "departmentForm";
    }

    @PostMapping(value = "/addDepartment")
    public String addDepartment(@ModelAttribute("departmentForm") Department department, BindingResult bindingResult, @RequestParam("facultyId") Integer facultyId, HttpServletRequest request) {
        logger.info("Admin is trying to add a new department.");
        if(bindingResult.hasErrors()) {
            logger.info("The department wasn't added. There were some errors.");
            return "departmentForm";
        }
        department.setFaculty(facultyService.readById(facultyId));
        if(departmentService.save(department)) {
            logger.info("Admin successfully added a new department.");
            return "redirect:/departments?faculty_id=" + facultyId;
        }
        request.setAttribute("msg", "The department  with the name " + department.getName() + " is already exists");
        logger.info("The department wasn't added.");
        return "departmentForm";
    }

    @GetMapping(value = "/updateDepartment")
    public String updateDepartment(@RequestParam Integer id, HttpServletRequest request) {
        logger.info("Admin is on the update page.");
        request.setAttribute("departmentForm", departmentService.readById(id));
        request.setAttribute("faculties", facultyService.readAll());
        request.setAttribute("mode", "MODE_UPDATE");
        return "departmentForm";
    }

    @PostMapping(value = "/updateDepartment")
    public String updateDepartment(@ModelAttribute("departmentForm") Department department, BindingResult bindingResult, @RequestParam("facultyId") Integer facultyId) {
        logger.info("Admin is updating the department.");
        if(bindingResult.hasErrors()) {
            logger.info("The department wasn't updated. There were some errors.");
            return "departmentForm";
        }
        department.setFaculty(facultyService.readById(facultyId));
        departmentService.update(department);
        logger.info("The department was successfully updated.");
        return "redirect:/departments?faculty_id=" + facultyId;
    }

    @GetMapping(value = "/deleteDepartment")
    public String deleteDepartment(@RequestParam Integer id) {
        logger.info("Admin is deleting the department with id = " + id + ".");
        Department department = departmentService.readById(id);
        departmentService.delete(department);
        return "redirect:/departments?faculty_id=" + department.getFaculty().getId();
    }
}
