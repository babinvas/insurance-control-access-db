package babinvas.insurancecontrolaccessdb.entities;

import javax.persistence.*;

@Entity
@Table(name = "02_Раб_тель_чл_в_НКСО")
@IdClass(CompanyKey.class)
public class Company {
	@Id
	@Column(name = "Id_Po_Reestru")
	private String idRegistryNumber;

	@Id
	@Column(name = "Kod_Rabotodatel")
	private String idCompany;

	@Column(name = "Forma_Poln_Rab")
	private String fullLegalForm;

	@Column(name = "Forma_Sokr_Rab")
	private String abbreviatedLegalForm;

	@Column(name = "Imya_Poln_Rab")
	private String fullName;

	@Column(name = "Imya_Sokr_Rab")
	private String abbreviatedName;

	@Column(name = "Email_Rab")
	private String mainEmail;

	@Column(name = "Email_Vtoroy_Rab")
	private String secondEmail;

	@Column(name = "Komu_Doljnost_Rab")
	private String toHeadForm;

	@Column(name = "Komu_FIO_Sokr_Rukovoditel_Rab")
	private String toAbbreviatedHeadName;

	@Column(name = "IO_Rukovoditel_Rab")
	private String headNamePatronymic;

	@Column(name = "Uvajaemiy_Aya_Rukovoditel_Rab")
	private String headRespectfulWordEnding;

	@ManyToOne(optional = false)
	@JoinColumn(name = "Id_Po_Reestru", updatable = false, insertable = false)
	private Member employee;

	public Company() {
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

	public String getFullLegalForm() {
		return fullLegalForm;
	}

	public void setFullLegalForm(String fullLegalForm) {
		this.fullLegalForm = fullLegalForm;
	}

	public String getAbbreviatedLegalForm() {
		return abbreviatedLegalForm;
	}

	public void setAbbreviatedLegalForm(String abbreviatedLegalForm) {
		this.abbreviatedLegalForm = abbreviatedLegalForm;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAbbreviatedName() {
		return abbreviatedName;
	}

	public void setAbbreviatedName(String abbreviatedName) {
		this.abbreviatedName = abbreviatedName;
	}

	public String getMainEmail() {
		return mainEmail;
	}

	public void setMainEmail(String mainEmail) {
		this.mainEmail = mainEmail;
	}

	public String getSecondEmail() {
		return secondEmail;
	}

	public void setSecondEmail(String secondEmail) {
		this.secondEmail = secondEmail;
	}

	public String getToHeadForm() {
		return toHeadForm;
	}

	public void setToHeadForm(String toHeadForm) {
		this.toHeadForm = toHeadForm;
	}

	public String getToAbbreviatedHeadName() {
		return toAbbreviatedHeadName;
	}

	public void setToAbbreviatedHeadName(String toAbbreviatedHeadName) {
		this.toAbbreviatedHeadName = toAbbreviatedHeadName;
	}

	public String getHeadNamePatronymic() {
		return headNamePatronymic;
	}

	public void setHeadNamePatronymic(String headNamePatronymic) {
		this.headNamePatronymic = headNamePatronymic;
	}

	public String getHeadRespectfulWordEnding() {
		return headRespectfulWordEnding;
	}

	public void setHeadRespectfulWordEnding(String headRespectfulWordEnding) {
		this.headRespectfulWordEnding = headRespectfulWordEnding;
	}

	public Member getEmployee() {
		return employee;
	}

	public void setEmployee(Member employee) {
		this.employee = employee;
	}
}
