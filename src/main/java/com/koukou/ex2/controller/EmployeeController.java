package com.koukou.ex2.controller;

import java.util.List;

import com.koukou.ex2.entity.Employee;
import com.koukou.ex2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

  @Autowired
  private EmployeeRepository eRepo;

  @GetMapping({"/","/showEmployees","/list"})
  public ModelAndView fairVoir(){

    ModelAndView fvoir = new ModelAndView("list-employees");
    List<Employee> list = eRepo.findAll();

    fvoir.addObject("employees", list);

    return fvoir;
  }

  @GetMapping("/addEmployeeForm")
  public ModelAndView addEmployeeForm(){

    ModelAndView fvoir = new ModelAndView("add-employee-form");
    Employee newEmployee = new Employee();

    fvoir.addObject("employee", newEmployee);

    return fvoir;
  }

  @PostMapping("/saveEmployee")
  public String saveEmployee(@ModelAttribute Employee employee){
    eRepo.save(employee);

    return "redirect:/list";
  }

  @GetMapping("/showUpdateForm")
  public ModelAndView showUpdateForm(@RequestParam Long employeeID){

    ModelAndView fvoir = new ModelAndView("add-employee-form");
    Employee employee = eRepo.findById(employeeID).get();

    fvoir.addObject("employee", employee);

    return fvoir;
  }

  @GetMapping("/deleteEmployeeForm")
  public String deleteEmployee(@RequestParam Long employeeID){

    eRepo.deleteById(employeeID);

    return "redirect:/list";

  }

}
