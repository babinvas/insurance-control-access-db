package babinvas.insurancecontrolaccessdb.entities;

import javax.persistence.*;

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
	MemberInsurance memberInsurance;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_Po_Reestru")
	MemberDeclination memberDeclination;

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

	public String secondEmail() {
		return secondEmail;
	}

	public void secondEmail(String secondEmail) {
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
}
