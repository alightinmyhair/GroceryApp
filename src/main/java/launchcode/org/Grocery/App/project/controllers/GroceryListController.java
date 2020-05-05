package launchcode.org.Grocery.App.project.controllers;

import launchcode.org.Grocery.App.project.data.GroceryItemData;
import launchcode.org.Grocery.App.project.models.GroceryCategory;
import launchcode.org.Grocery.App.project.models.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("grocerylist")
public class GroceryListController {

//    @Autowired(required = false)
//    ArrayList<GroceryItem> groceryItems1 = new ArrayList<>();

    @GetMapping
    public String displayGroceryList(Model model) {

        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());
        model.addAttribute(new GroceryItem());

        return "groceryList/index";

    }

    // display a form in each row on groceryList
    //loop through the grocery items in a table,
    //with each table row being a form
    //in the form my input fields would be my th field
    @GetMapping("/editgrocery")
    public String displayEditForm(Model model, int[] itemIds, String edit) {

        //If edit button is clicked, the below happens
        if (edit != null) {
            ArrayList<GroceryItem> groceryItems1 = new ArrayList<GroceryItem>();
            for (int i = 0; i < itemIds.length; i++) {
                System.out.println(itemIds[i]);
                groceryItems1.add(GroceryItemData.getById(itemIds[i]));
            }
            model.addAttribute("items", groceryItems1);
            //is this really getting the id?
            GroceryItem groceryItem = GroceryItemData.getById(itemIds[0]);
//            GroceryItem groceryItem1 = GroceryItemData.getAll();
//            System.out.println(groceryItem, groceryItem1);
            return "edit";
        }
        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());

        return "groceryList/edit";
    }

    @PostMapping("/editgrocery")
    public String editGroceryItem(int id, String name, String description, GroceryCategory category, Model model) {
//
//        if(GroceryItemData.getAll() != null){
//            for (){
//                System.out.println(GroceryItemData.getById(id));
//                GroceryItemData.edit(id, name, description, category);
//            }
//        }

        System.out.println("hi");
        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());

        return "groceryList/index";
    }

    @PostMapping("/add")
    public String addGroceryItem(@ModelAttribute @Valid GroceryItem newGroceryItem, Model model) {

        GroceryItemData.add(newGroceryItem);
        model.addAttribute("items", GroceryItemData.getAll());
        model.addAttribute("categories", GroceryCategory.values());

        return "groceryList/index";
    }

    @PostMapping("/update")
    public String updateGroceryItem(Model model, @RequestParam int[] itemIds, @ModelAttribute GroceryItem groceryItem, String delete, String edit) {

        // TODO: RK - create new class to handle itemIds and edit/delete buttons?

        if (edit != null) {


            ArrayList<GroceryItem> groceryItems1 = new ArrayList<>();

            for (int i = 0; i < itemIds.length; i++) {
                System.out.println(itemIds[i]);
                GroceryItem tempGroceryItem = GroceryItemData.getById(itemIds[i]);
                System.out.println(tempGroceryItem);
                groceryItems1.add(tempGroceryItem);
            }
            //GroceryItem groceryItem = groceryItems1.get(0); //GroceryItemData.getById(itemIds[0]);
            model.addAttribute("groceryItems", groceryItems1);
            model.addAttribute("groceryItem", groceryItem);
            model.addAttribute("itemIds", itemIds);
//            model.addAttribute("items", groceryItems1.get(0));
            model.addAttribute("categories", GroceryCategory.values());
//            model.addAttribute(new GroceryItem());
//            model.addAttribute(groceryItems1);

            return "groceryList/edit";
        }
//        if (edit != null){
//            System.out.println("edit button clicked");
//
//            GroceryItem groceryItems = GroceryItemData.getById(itemIds[0]);
//            model.addAttribute("groceryItems",groceryItems);
//            model.addAttribute("categories", GroceryCategory.values());
//
//            return "groceryList/edit";
//        }

            if (delete != null) {
                System.out.println("delete clicked!");
                if (itemIds != null) {
                    for (int id : itemIds) {
                        System.out.println(GroceryItemData.getById(id));
                        GroceryItemData.remove(id);
                    }
                }
            }

            model.addAttribute("items", GroceryItemData.getAll());
            model.addAttribute("categories", GroceryCategory.values());
            model.addAttribute(new GroceryItem());

            return "groceryList/index";
        }
    }

