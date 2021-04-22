package babinvas.insurancecontrolaccessdb.entities;

import java.util.Objects;

public class CompanyKey {
	static final long serialVersionUID = 1L;

	private String idCompany;
	private String idRegistryNumber;

	public CompanyKey() {
	}

	public String getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(String idCompany) {
		this.idCompany = idCompany;
	}

	public String getIdRegistryNumber() {
		return idRegistryNumber;
	}

	public void setIdRegistryNumber(String idRegistryNumber) {
		this.idRegistryNumber = idRegistryNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CompanyKey that = (CompanyKey) o;
		return idCompany.equals(that.idCompany) &&
				idRegistryNumber.equals(that.idRegistryNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCompany, idRegistryNumber);
	}
}
