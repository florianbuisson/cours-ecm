package fr.cmm.service;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
import org.jongo.MongoCollection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static fr.cmm.SpringProfiles.INTEG;
import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ImageServiceTestConfig.class)
@ActiveProfiles(INTEG)
public class RecipeServiceTest {
    @Inject
    private RecipeService recipeService;

    @Inject
    private MongoCollection recipeCollection;

    @Before
    @After
    public void clean() {
        recipeCollection.remove();
    }

    @Test
    void save() {
        recipeService.save(new Recipe(title: 'test recipe'))

        assert recipeCollection.findOne().as(Recipe).title == 'test recipe'
    }

    @Test
    public void findById() {
        Recipe recipe = new Recipe();
        recipe.setTitle("test recipe");

        recipeService.save(recipe);

        Assert.assertEquals("test recipe", recipeService.findById(recipe.getId()).getTitle());
    }

    @Test
    public void 'findById with invalid id'() {
        Assert.assertEquals(null, recipeService.findById("ghsdlgsndlkj"));
    }

    @Test
    public void findByQuery() {
        5.times {
            recipeService.save(new Recipe())
        }
        assert recipeService.findByQuery(new PageQuery()).size() == 5;
    }

    @Test
    public void countByQuery() {
        Recipe recipe1 = new Recipe();
        recipe1.setTags(asList("alsace"));
        recipeService.save(recipe1);

        Recipe recipe2 = new Recipe();
        recipe2.setTags(asList("alsace"));
        recipeService.save(recipe2);

        Recipe recipe3 = new Recipe();
        recipe3.setTags(asList("choucroute"));
        recipeService.save(recipe3);

        PageQuery query = new PageQuery();
        query.setTag("alsace");

        Assert.assertEquals(2, recipeService.countByQuery(query));
    }

    @Test
    public void findByQueryWithCustomPageSize() {
        5.times {
            recipeService.save(new Recipe())
        }

        PageQuery pageQuery = new PageQuery();
        pageQuery.setSize(2);

        Assert.assertEquals(2, stream(recipeService.findByQuery(pageQuery).spliterator(), false).count());
    }

    @Test
    public void findByQueryWithTag() {
        recipeService.save(new Recipe().withTags("tag1"));
        recipeService.save(new Recipe().withTags("tag1"));
        recipeService.save(new Recipe().withTags("tag2"));
        recipeService.save(new Recipe().withTags("tag2"));
        recipeService.save(new Recipe().withTags("tag3"));

        PageQuery pageQuery = new PageQuery();
        pageQuery.setTag("tag1");

        Assert.assertEquals(2, stream(recipeService.findByQuery(pageQuery).spliterator(), false).count());
    }

    @Test
    public void findAllTags() {
        recipeService.save(new Recipe(tags: ['tag1', 'tag2']));
        recipeService.save(new Recipe(tags: ['tag2', 'tag3']));

        Assert.assertEquals(asList("tag1", "tag2", "tag3"), recipeService.findAllTags());
    }
}