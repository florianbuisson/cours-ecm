package fr.cmm.controller
import fr.cmm.controller.form.SearchForm
import fr.cmm.helper.Columns
import fr.cmm.helper.PageQuery
import fr.cmm.helper.Pagination
import fr.cmm.service.RecipeService
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import javax.inject.Inject

@Controller
public class IndexController {
    @Inject
    private RecipeService recipeService;

    @RequestMapping(["/index", "/"])
    public String index(ModelMap model) {
        model.columns = randomColumns();

        "index";
    }

    @RequestMapping("/tags.json")
    @ResponseBody
    public List<String> tags() {
        return recipeService.findAllTags();
    }

    @RequestMapping("/recettes")
    public String recettes(SearchForm searchForm, ModelMap model) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setIndex(searchForm.getPageIndex() - 1);
        pageQuery.setTag(searchForm.getTag());
        if(pageQuery.getIndex()<0){
            pageQuery.setIndex(0);
        }

        Pagination pagination = new Pagination();
        pagination.setPageIndex(searchForm.getPageIndex());
        pagination.setPageSize(pageQuery.getSize());
        pagination.setCount(recipeService.countByQuery(pageQuery));

        model.recipes = recipeService.findByQuery(pageQuery);
        model.pagination = pagination;
        model.tagSearch = searchForm;

        "recettes";
    }

    @RequestMapping("/recette-du-moment")
    public String recettesDuMoment(ModelMap model) {
        model.recipe = recipeService.findRandom(1).next();

        "recette-du-moment";
    }

    private Columns randomColumns() {
        Columns columns = new Columns();

        columns.add(recipeService.findRandom(10));
        columns.add(recipeService.findRandom(10));
        columns.add(recipeService.findRandom(10));

        columns;
    }

    @RequestMapping("/recette/{id}")
    public String recette(@PathVariable("id") String id, ModelMap model) {
        model.recipe = recipeService.findById(id);
        if(!recipeService.findById(id)) {
            throw new RessourceNotFoundException();
        } else {
            "recette";
        }
    }

    @RequestMapping("/contact")
    public String contact() {
        "contact";
    }

    @RequestMapping("/mentions-legales")
    public String mentionsLegales() {
        "mentions-legales";
    }

    @RequestMapping("/404")
    public String errror404() {
        "error";
    }

    @RequestMapping("/500")
    public String error500() {
        "error";
    }
}

