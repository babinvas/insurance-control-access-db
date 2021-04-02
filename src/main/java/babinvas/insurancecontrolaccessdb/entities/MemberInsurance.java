package babinvas.insurancecontrolaccessdb.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "06_Страховые_Полиса_НКСО")
public class MemberInsurance {
	@Id
	@Column(name = "Id_По_Реестру", nullable = false)
	private String idRegistryNumber;

	@Column(name = "2020_Окончания_Полиса")
	private Date expirationDate;

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
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
