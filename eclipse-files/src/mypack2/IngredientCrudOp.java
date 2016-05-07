package mypack2;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class IngredientCrudOp {
	
	public static void main(String[] args) {
		new IngredientCrudOp().insertIngredient("Parsley",10);
		new IngredientCrudOp().getIngredById(5);
		new IngredientCrudOp().deleteIngredById(5);
		new IngredientCrudOp().getIngredients();//getIngredients()
		new IngredientCrudOp().getIngredients();
	    new IngredientCrudOp().getIngredByName("Wheat Flour");
		new IngredientCrudOp().updateIngred(6,"Oregano", -1);
		HibernateUtil.shutdown();
	}
	
	public void getIngredById(int id)
	{
		SessionFactory sessionFactory =
				HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Ingredient ingred = 
		  (Ingredient) session.get(Ingredient.class, new Integer(id));
		
		
		if(ingred != null){
			System.out.println("\n Ingredient with id: " + id);
			System.out.println(ingred.toString());
		
			session.close();
		
		}
		else {
		        System.out.println("\n No Ingredient with id:" + id + " exists.");

			session.close();
		
		}	
		
	}
	
	public void deleteIngredById(int id)
	{
		SessionFactory sessionFactory =
			HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.getTransaction().begin();
		Ingredient ingred = 
				(Ingredient) session.get(Ingredient.class, new Integer(id));
		
		
		if (ingred != null) {
			
			System.out.println("\n Deleting...:");
			System.out.println(ingred.toString());
			
		    session.delete(ingred);
		    session.getTransaction().commit();
		    
		    session.close();
			
		}
		else {
		   System.out.println("No Ingredient with id:" + id + " exists.");
		   session.close();
		   
		}
	}

	public void insertIngredient(String name, int quantity)
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		//begin a transaction
		session.getTransaction().begin();
		
		//creating an Ingredient
		Ingredient ingred = new Ingredient();
		ingred.setName(name);
		ingred.setQuantity(quantity);
		
		// save Ingredient object
		session.save(ingred);
		System.out.println("\n Inserting ingredient: ");
		System.out.println(ingred.toString());
		
		//commit transaction
		session.getTransaction().commit();
		
		session.close();
		
	
	}
    
	@SuppressWarnings("unchecked")
	public void getIngredients()
	{
		SessionFactory sessionFactory = 
				HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(Ingredient.class);
		
		List<Ingredient> ingredList = criteria.list();
		
		System.out.println("\n Current ingredients: ");
		for(Ingredient ingred : ingredList){
			System.out.println(ingred.toString());
		}
	
		session.close();
		
	}

	public void getIngredByName(String name){
		
		SessionFactory sessionFactory = 
				HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(Ingredient.class);
		criteria.add(Restrictions.eq("name", name));
		
		
		@SuppressWarnings("unchecked")
		List<Ingredient> ingredList = criteria.list();
		
		System.out.println("\n Ingredients with name: " + name );
		if(ingredList.size() > 0){
		   for(Ingredient ingred : ingredList){
			   System.out.println(ingred.toString());	
		   }
		   
		  session.close();
		  
		   
		}else {
			
		  System.out.println("No Ingredients by name: " + name);
		    
		  session.close();
		  
		}
	}
    
	public void updateIngred(int id, String name, int quantity)
	{
		SessionFactory sessionFactory =
			HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Ingredient ingred = 
				(Ingredient) session.get(Ingredient.class, new Integer(id));
		
		if(ingred != null){
			
			session.getTransaction().begin();
			
			if(name.length() > 0){
			   ingred.setName(name);
			}
			if(quantity > 0){
				ingred.setQuantity(quantity);
			}
			
			System.out.println("\n Updated Ingredient: \n" + ingred.toString());
			
			session.save(ingred);
			session.getTransaction().commit();
			session.close();
			
			
		}
		else {
			System.out.println("Ingredient with id: " + id + " does not exist.");
			
			session.close();
			
		}
		
	}
}
