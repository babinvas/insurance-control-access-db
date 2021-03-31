package babinvas.insurancecontrolaccessdb.entities;

import javax.persistence.*;

@Entity
@Table(name = "01_Члены_НКСО")
public class Member {
	@Id
	@Column(name = "Id_По_Реестру", nullable = false)
	private String idRegistryNumber;

	@Column(name = "Фамилия")
	private String surname;

	@Column(name = "Имя")
	private String name;

	@Column(name = "Отчество")
	private String patronymic;

	@Column(name = "E_mail_члена_НКСО")
	private String mainEmail;

	@Column(name = "E_mail_члена_НКСО_второй")
	private String email2;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_По_Реестру")
	MemberInsurance memberInsurance;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_По_Реестру")
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

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
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
