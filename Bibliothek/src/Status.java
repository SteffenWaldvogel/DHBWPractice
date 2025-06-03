
public enum Status {
	AVAILABLE("verfügbar"), LENT("verliehen");

	private String description;

	Status(String description) {
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
