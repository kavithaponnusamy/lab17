package lab17;

public class Country {
	private String countryName;
	private int population;
	
	public Country() {
		super();
	}

	public Country(String countryName, int population) {
		super();
		this.countryName = countryName;
		this.population = population;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "Country [countryName=" + countryName + ", population=" + population + "]";
	}
	
	

}
