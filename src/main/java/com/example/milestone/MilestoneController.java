package com.example.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MilestoneController {
  private final MilestoneDao dao;

  @Autowired
  MilestoneController(MilestoneDao dao) {
    this.dao = dao;
  }

  @GetMapping("milestones")
  public String getMilestones(Model model) {
    model.addAttribute("milestones", dao.findAll());
    return "milestone/milestone-list";
  }

  @GetMapping("milestones/add")
  public String getAddMilestone(Model model) {
    model.addAttribute("input", new AddMilestoneRequest());
    return "milestone/milestone-add";
  }

  @PostMapping("milestones/add")
  public String postAddMilestone(@ModelAttribute AddMilestoneRequest input) {
    dao.create(input);
    return "redirect:/milestones";
  }

  @GetMapping("milestones/{id}")
  public String getMilestoneDetail(@PathVariable String id, Model model) {
    Milestone milestone = dao.findById(id);
    model.addAttribute("milestone", milestone);
    return "milestone/milestone-detail";
  }

  @GetMapping("milestones/{id}/delete")
  public String getMilestoneDelete(@PathVariable String id) {
    dao.delete(id);
    return "redirect:/milestones";
  }

  @GetMapping("milestones/{id}/edit")
  public String getMilestoneEdit(@PathVariable String id, Model model) {
    Milestone milestone = dao.findById(id);
    EditMilestoneRequest input = new EditMilestoneRequest();
    input.setTitle(milestone.getTitle());
    input.setDescription(milestone.getDescription());
    input.setDeadline(milestone.getDeadline().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    model.addAttribute("input", input);
    model.addAttribute("id", id);
    return "milestone/milestone-edit";
  }

  @PostMapping("milestones/{id}/edit")
  public String postMilestoneEdit(@PathVariable String id, @ModelAttribute EditMilestoneRequest input) {
    dao.update(id, input);
    return "redirect:/milestones/" + id;
  }
}
