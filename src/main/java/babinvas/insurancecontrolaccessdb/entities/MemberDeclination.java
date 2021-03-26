package babinvas.insurancecontrolaccessdb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MemberDeclination {
	@Id
	private String idRegistryNumber;

	private String abbreviationToWhom;
	private String respectfulWordEnding;

	Member member;

	public MemberDeclination() {
	}

	public MemberDeclination(Member member) {
		this.member = member;
		idRegistryNumber = member.getIdRegistryNumber();
	}

	public String getIdRegistryNumber() {
		return idRegistryNumber;
	}

	public void setIdRegistryNumber(String idRegistryNumber) {
		this.idRegistryNumber = idRegistryNumber;
	}

	public String getAbbreviationToWhom() {
		return abbreviationToWhom;
	}

	public void setAbbreviationToWhom(String abbreviationToWhom) {
		this.abbreviationToWhom = abbreviationToWhom;
	}

	public String getRespectfulWordEnding() {
		return respectfulWordEnding;
	}

	public void setRespectfulWordEnding(String respectfulWordEnding) {
		this.respectfulWordEnding = respectfulWordEnding;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
