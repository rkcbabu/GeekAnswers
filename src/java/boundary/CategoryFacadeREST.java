/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Category;
import entities.Question;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Chaulagai
 */
@Stateless
@Path("category")
public class CategoryFacadeREST extends AbstractFacade<Category> {
    @PersistenceContext(unitName = "GeekAnswersPU")
    private EntityManager em;

    public CategoryFacadeREST() {
        super(Category.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Category entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Category entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Category find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Path("questionsById/{categoryId}")
    @Produces({"application/xml", "application/json"})
    public List<Question> questionsById(@PathParam("categoryId") Long categoryId) {
        Category category = super.find(categoryId); 
          return (category !=null) ? (super.find(categoryId)).getQuestions(): new ArrayList<Question>();
    }
    
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Category> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Category> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
