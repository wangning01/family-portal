package home.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sale implements Comparable<Sale>{
	
	public static void main(String[] args) {
		
		CompareAlgorithm<Sale> algorithm = new CompareBySaleItem();
		
		List<Sale> sales = new ArrayList<>();
		
		Sale sale1 = new Sale();
		sale1.setItem("item9");
		sale1.setQauntity(1);
		
		Sale sale2 = new Sale();
		sale2.setItem("item1");
		sale2.setQauntity(9);
		
		sales.add(sale1);
		sales.add(sale2);
		
		for(Sale sale: sales) {
			sale.setCompareAlgorithm(algorithm);
		}
		
		Collections.sort(sales);
		assert(sales.get(0).getItem().equals("item9"));
		
	}
	
	private String item;
	

	private Integer qauntity;
	private CompareAlgorithm<Sale> compareAlgorithm;
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getQauntity() {
		return qauntity;
	}
	public void setQauntity(Integer qauntity) {
		this.qauntity = qauntity;
	}
	
	
	public CompareAlgorithm<Sale> getCompareAlgorithm() {
		return compareAlgorithm;
	}
	public void setCompareAlgorithm(CompareAlgorithm<Sale> compareAlgorithm) {
		this.compareAlgorithm = compareAlgorithm;
	}
	@Override
	public int compareTo(Sale otherObj) {
		return this.compareAlgorithm.compare(this, otherObj);
	}
	
	
	
	

}
