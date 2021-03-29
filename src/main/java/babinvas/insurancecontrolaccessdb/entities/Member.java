package babinvas.insurancecontrolaccessdb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "01_Члены_НКСО")
public class Member {
	@Id
	@Column(name = "№_По_Реестру", nullable = false)
	private String idRegistryNumber;

	@Column(name = "Фамилия")
	private String surname;

	@Column(name = "Имя")
	private String name;

	@Column(name = "Отчество")
	private String patronymic;

	@Column(name = "E-mail_члена_НКСО")
	private String mainEmail;

	@Column(name = "E-mail_члена_НКСО_2")
	private String email2;

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
}
