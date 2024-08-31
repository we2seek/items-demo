package com.we2seek.items_demo.controller;

import com.we2seek.items_demo.command.ItemForm;
import com.we2seek.items_demo.converter.ItemFormToItemConverter;
import com.we2seek.items_demo.converter.ItemToItemFromConverter;
import com.we2seek.items_demo.dao.ItemDAO;
import com.we2seek.items_demo.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private final ItemDAO itemDAO;
    private final ItemFormToItemConverter itemFormToItemConverter;
    private final ItemToItemFromConverter itemToItemFromConverter;

    public HomeController(ItemDAO itemDAO, ItemFormToItemConverter itemFormToItemConverter, ItemToItemFromConverter itemToItemFromConverter) {
        this.itemDAO = itemDAO;
        this.itemFormToItemConverter = itemFormToItemConverter;
        this.itemToItemFromConverter = itemToItemFromConverter;
    }

    private void getLog(Model model) {
        log.info("Title = {}", model.getAttribute("title"));
        log.info("Header = {}", model.getAttribute("header"));
        log.info("Fragment = {}", model.getAttribute("fragment"));
    }

    @GetMapping({"/"})
    public String index(Model model) {
        model.addAttribute(
                "items",
                itemDAO.getAll().stream()
                        .map(itemToItemFromConverter::convert)
                        .collect(Collectors.toList())
        );

        model.addAttribute("title", "Items");
        model.addAttribute("header", "Application main page");
        model.addAttribute("fragment", "tableItems");

        getLog(model);

        return "index";
    }

    @GetMapping(value = "/create")
    public String showCreateForm(Model model) {
        ItemForm itemForm = new ItemForm();
        itemForm.setActive(true);
        model.addAttribute("itemForm", itemForm);
        model.addAttribute("title", "Create");

        model.addAttribute("header", "Create item");
        model.addAttribute("fragment", "editForm");

        getLog(model);

        return "index";
    }

    @PostMapping("/create")
    public String saveItem(ItemForm form, BindingResult bindingResult) {
        Item item = itemFormToItemConverter.convert(form);
        itemDAO.create(item);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Item item = itemDAO.get(id);
        ItemForm itemForm = itemToItemFromConverter.convert(item);
        model.addAttribute("itemForm", itemForm);

        model.addAttribute("title", "Edit");
        model.addAttribute("header", "Edit item");
        model.addAttribute("fragment", "editForm");

        getLog(model);

        return "index";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Integer id, Model model) {
        Item item = itemDAO.get(id);
        ItemForm itemForm = itemToItemFromConverter.convert(item);
        model.addAttribute("itemForm", itemForm);

        model.addAttribute("title", "Delete");
        model.addAttribute("header", "Delete item");
        model.addAttribute("fragment", "deleteForm");

        getLog(model);

        return "index";
    }

    @PostMapping(value = "/delete", params = "action=delete")
    public String deleteItem(ItemForm form) {
        itemDAO.delete(form.getId());

        return "redirect:/";
    }

    @PostMapping(value = "/delete", params = "action=cancel")
    public String deleteItemCancel() {
        return "redirect:/";
    }
}
