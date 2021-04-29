package babinvas.insurancecontrolaccessdb.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "01_Члены_НКСО")
public class Member {
	@Id
	@Column(name = "Id_Po_Reestru", nullable = false)
	private String idRegistryNumber;

	@Column(name = "Familiya")
	private String surname;

	@Column(name = "Imya")
	private String name;

	@Column(name = "Otchestvo")
	private String patronymic;

	@Column(name = "Email_Chlena_Nkso")
	private String mainEmail;

	@Column(name = "Email_Chlena_Nkso_Vtoroy")
	private String secondEmail;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_Po_Reestru")
	private MemberInsurance memberInsurance;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_Po_Reestru")
	private MemberDeclination memberDeclination;

	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Company> companies;

	public Member() {
	}

	public Member(String idRegistryNumber) {
		this.idRegistryNumber = idRegistryNumber;
	}

	public String getIdRegistryNumber() {
		return idRegistryNumber;
	}

	public void setIdRegistryNumber(String idRegistryNumber) {
		this.idRegistryNumber = idRegistryNumber;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
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

	public MemberInsurance getMemberInsurance() {
		return memberInsurance;
	}

	public void setMemberInsurance(MemberInsurance memberInsurance) {
		this.memberInsurance = memberInsurance;
	}

	public MemberDeclination getMemberDeclination() {
		return memberDeclination;
	}

	public void setMemberDeclination(MemberDeclination memberDeclination) {
		this.memberDeclination = memberDeclination;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
}
