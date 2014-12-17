package presentation;

import entities.Question;
import presentation.util.JsfUtil;
import presentation.util.PaginationHelper;
import boundary.QuestionFacade;
import common.Functor;
import common.ListFilter;
import common.Predicate;
import common.QuestionAuthorCheckPredicate;
import entities.Category;
import entities.User;
import java.io.IOException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.TypedQuery;

@ManagedBean(name = "questionController")

@RequestScoped
public class QuestionController implements Serializable {

    private Question current;
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    private DataModel items = null;
    @EJB
    private boundary.QuestionFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<Question> questions;

    @PostConstruct
    public void addUserId() {
        try {
            User u = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logged_in_user");

            setCurrentUser(u);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User is not logged in yet", "not logged in"));

        }
    }

    public QuestionController() {
    }

    public Question getSelected() {
        if (current == null) {
            current = new Question();
            selectedItemIndex = -1;
        }
        return current;
    }

    private QuestionFacade getFacade() {
        return ejbFacade;
    }
    public List<Question> getNewQuestions(){
        return ejbFacade.getQuestions();
    }
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    List<Question> mylist = getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});

                    Predicate pred = new QuestionAuthorCheckPredicate();
                    ListFilter<Question, User> functor = new ListFilter();

                    User curuser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logged_in_user");

                    if (curuser == null) {
                        try {

                            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                            context.redirect(context.getRequestContextPath() + "/faces/user/login.xhtml");

                        } catch (IOException ex) {
                            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    //System.out.println("userid from cc="+getCurrentUser().getId());
                    List<Question> filteredList = functor.execute(mylist, curuser, pred);

                    return new ListDataModel(filteredList);
                    // return new ListDataModel(mylist);
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Question) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public void prepareCreate() throws IOException {
        current = new Question();
        selectedItemIndex = -1;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
    }

    public String create() {
        try {

            current.setCreatedDate(new Date());
            current.setUser(currentUser);
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Resource/Bundle").getString("QuestionCreated"));
            prepareCreate();
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Resource/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Question) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.setModifiedDate(new Date());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Resource/Bundle").getString("QuestionUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Resource/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Question) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Resource/Bundle").getString("QuestionDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Resource/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public List<Question> getAll() {
        String query = "SELECT e FROM Question e ORDER BY e.createdDate DESC ";
        TypedQuery<Question> q1 = ejbFacade.getEM().createQuery(query, Question.class);
        return q1.getResultList();
    }
    public List<Question> getPopularAll() {
        return getFacade().getPopular();
    }
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Question getQuestion(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Question.class)
    public static class QuestionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            QuestionController controller = (QuestionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "questionController");
            return controller.getQuestion(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Question) {
                Question o = (Question) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Question.class.getName());
            }
        }

    }

}
