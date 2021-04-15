package babinvas.insurancecontrolaccessdb.entities;

import javax.persistence.*;

@Entity
@Table(name = "11_Склонение_Членов_НКСО")
public class MemberDeclination {
	@Id
	@Column(name = "Id_Po_Reestru", nullable = false)
	private String idRegistryNumber;

	@Column(name = "Komu_Sokr_Fio_Pismo")
	private String abbreviationToWhom;

	@Column(name = "Uvajaemiy_Aya")
	private String respectfulWordEnding;

	@OneToOne(optional = false, mappedBy = "memberDeclination")
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
