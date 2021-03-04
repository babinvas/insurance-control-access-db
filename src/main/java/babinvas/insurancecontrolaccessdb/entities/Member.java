package babinvas.insurancecontrolaccessdb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
	@Id
	private long id;

	private String surname;
	private String name;
	private String patronymic;
	private String toWhom;
	private String sexualEnding;

	private String mainEmail;
	private String secondEmail;

	public Member() {
	}

	public Member(long id,
	              String surname,
	              String name,
	              String patronymic,
	              String toWhom,
	              String sexualEnding,
	              String mainEmail,
	              String secondEmail) {
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.toWhom = toWhom;
		this.sexualEnding = sexualEnding;
		this.mainEmail = mainEmail;
		this.secondEmail = secondEmail;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getToWhom() {
		return toWhom;
	}

	public void setToWhom(String toWhom) {
		this.toWhom = toWhom;
	}

	public String getSexualEnding() {
		return sexualEnding;
	}

	public void setSexualEnding(String sexualEnding) {
		this.sexualEnding = sexualEnding;
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
}
