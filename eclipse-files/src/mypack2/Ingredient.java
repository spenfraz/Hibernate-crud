package mypack2;

public class Ingredient {
	private int ingred_id;
	private String name;
        private int quantity;
	public int getIngred_id(){
		return ingred_id;
	}
	public void setIngred_id(int ingred_id){
		this.ingred_id = ingred_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
        public int getQuantity() {
                return quantity;
        }
        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }
	@Override
	public String toString()
	{
		return "\nIngredient:"
		+ "\n id: " + this.getIngred_id()
		+ "\n name: " + this.getName()
		+ "\n quantity: " + this.getQuantity();
		
	}
}
