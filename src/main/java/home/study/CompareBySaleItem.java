package home.study;


public class CompareBySaleItem implements CompareAlgorithm<Sale> {

	@Override
	public int compare(Sale obj1, Sale obj2) {
		return obj1.getItem().compareTo(obj2.getItem());
	}


}
