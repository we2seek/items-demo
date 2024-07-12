package com.we2seek.items_demo.controller;

import com.we2seek.items_demo.command.ItemForm;
import com.we2seek.items_demo.converter.ItemFormToItemConverter;
import com.we2seek.items_demo.converter.ItemToItemFromConverter;
import com.we2seek.items_demo.dao.ItemDAO;
import com.we2seek.items_demo.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final ItemDAO itemDAO;
    private final ItemFormToItemConverter itemFormToItemConverter;
    private final ItemToItemFromConverter itemToItemFromConverter;

    public HomeController(ItemDAO itemDAO, ItemFormToItemConverter itemFormToItemConverter, ItemToItemFromConverter itemToItemFromConverter) {
        this.itemDAO = itemDAO;
        this.itemFormToItemConverter = itemFormToItemConverter;
        this.itemToItemFromConverter = itemToItemFromConverter;
    }

    @GetMapping({"/", "index"})
    public String index(Model model) {
        model.addAttribute("items", itemDAO.getAll());

        return "index";
    }

    @GetMapping(value = "/items/create")
    public String showCreateForm(Model model) {
        model.addAttribute("itemForm", new ItemForm());

        return "item/form";
    }

    @PostMapping("/items/create")
    public String saveItem(ItemForm form, BindingResult bindingResult) {
        Item item = itemFormToItemConverter.convert(form);
        itemDAO.create(item);

        return "redirect:/index";
    }

    @GetMapping("/items/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Item item = itemDAO.get(id);
        ItemForm itemForm = itemToItemFromConverter.convert(item);
        model.addAttribute("itemForm", itemForm);

        return "item/form";
    }

    @GetMapping("/items/delete/{id}")
    public String showDeleteForm(@PathVariable Integer id, Model model) {
        Item item = itemDAO.get(id);
        ItemForm itemForm = itemToItemFromConverter.convert(item);
        model.addAttribute("itemForm", itemForm);

        return "item/deleteForm";
    }

    @PostMapping(value="/items/delete", params="action=delete")
    public String deleteItem(ItemForm form) {
        itemDAO.delete(form.getId());

        return "redirect:/index";
    }

    @PostMapping(value="/items/delete", params="action=cancel")
    public String deleteItemCancel() {
        return "redirect:/index";
    }
}
