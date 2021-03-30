package babinvas.insurancecontrolaccessdb.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "11_Склонение_Членов_НКСО")
public class MemberInsurance {
	@Id
	@Column(name = "№_По_Реестру", nullable = false)
	private String idRegistryNumber;

	@Column(name = "2020_Окончания_Полиса")
	private Date ExpirationDate;

	@OneToOne(optional = false, mappedBy = "memberInsurance")
	Member member;

	public MemberInsurance() {
	}

	public MemberInsurance(Member member) {
		this.member = member;
		idRegistryNumber = member.getIdRegistryNumber();
	}

	public String getIdRegistryNumber() {
		return idRegistryNumber;
	}

	public void setIdRegistryNumber(String idRegistryNumber) {
		this.idRegistryNumber = idRegistryNumber;
	}

	public Date getExpirationDate() {
		return ExpirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		ExpirationDate = expirationDate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
