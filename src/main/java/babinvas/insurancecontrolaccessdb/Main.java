package babinvas.insurancecontrolaccessdb;

import babinvas.insurancecontrolaccessdb.entities.Member;
import babinvas.insurancecontrolaccessdb.entities.MemberDeclination;
import babinvas.insurancecontrolaccessdb.entities.MemberInsurance;
import babinvas.insurancecontrolaccessdb.services.email.EmailSendingService;
import babinvas.insurancecontrolaccessdb.services.email.TextEmailSendingService;
import babinvas.insurancecontrolaccessdb.services.repository.MemberRepositoryService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
	private static final EmailSendingService emailSendingService = new TextEmailSendingService("username", "password", "host", 465);

	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	public static void main(String[] args) {
		// emailSendingService.setFrom("from@from.ru");
		// emailSendingService.setTo("to@to.ru");
		// emailSendingService.setCc("cc@cc.ru");
		// emailSendingService.setBcc("bcc@bcc.ru");
		// emailSendingService.send();


		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("nkso-members-and-employers");
		entityManager = entityManagerFactory.createEntityManager();

		entityTransaction = entityManager.getTransaction();

		// createEntities();
		// readEntities();
		// updateEntities();
		// deleteEntities();

		MemberRepositoryService memberRepositoryService = new MemberRepositoryService(entityManagerFactory);
		Date date = getDate("03.05.2021");
		List<Member> memberList = memberRepositoryService.getByInsurance(date);

		for (Member member : memberList) {
			System.out.println(member.getIdRegistryNumber() + " " + member.getSurname());
		}

		entityManager.getEntityManagerFactory().close();
		entityManager.close();
	}

	private static Member getMember(String idRegistryNumber, String surname, String name, String patronymic, String mainEmail, String email2) {
		Member member = new Member(idRegistryNumber);
		member.setSurname(surname);
		member.setName(name);
		member.setPatronymic(patronymic);
		member.setMainEmail(mainEmail);
		member.setEmail2(email2);

		return member;
	}

	private static MemberDeclination getMemberDeclination(Member member, String abbreviationToWhom, String respectfulWordEnding) {
		MemberDeclination memberDeclination = new MemberDeclination(member);
		memberDeclination.setAbbreviationToWhom(abbreviationToWhom);
		memberDeclination.setRespectfulWordEnding(respectfulWordEnding);

		return memberDeclination;
	}

	private static MemberInsurance getMemberInsurance(Member member, String expirationDate) {
		MemberInsurance memberInsurance = new MemberInsurance(member);

		Date date = getDate(expirationDate);
		memberInsurance.setExpirationDate(date);

		return memberInsurance;
	}

	private static Date getDate(String date) {
		try{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static void createEntities() {
		entityTransaction.begin();

		Member member1 = getMember("00001", "Babin", "Vas", "Vyach", "babinvas@mail.ru", "babinvas@gmail.com" );
		MemberDeclination memberDeclination1 = getMemberDeclination(member1, "V.V. Babin","ый");
		MemberInsurance memberInsurance1 = getMemberInsurance(member1, "01.04.2021");
		member1.setMemberDeclination(memberDeclination1);
		member1.setMemberInsurance(memberInsurance1);

		Member member2 = getMember("00002", "Babin", "Georg", "Vyach", "georg@mail.ru", "georg@gmail.com" );
		MemberDeclination memberDeclination2 = getMemberDeclination(member2, "G.V. Babin", "ый");
		MemberInsurance memberInsurance2 = getMemberInsurance(member2, "03.05.2021");
		member2.setMemberDeclination(memberDeclination2);
		member2.setMemberInsurance(memberInsurance2);

		Member member3 = getMember("00003", "Lyu", "Stas", "Vlad", "stas@mail.ru", "stas@gmail.com" );
		MemberDeclination memberDeclination3 = getMemberDeclination(member3, "S.V. Lyu", "ый");
		MemberInsurance memberInsurance3 = getMemberInsurance(member3, "30.12.2020");
		member3.setMemberDeclination(memberDeclination3);
		member3.setMemberInsurance(memberInsurance3);

		Member member4 = getMember("00004", "Sava", "Il", "Vlad", "il@mail.ru", "il@gmail.com" );
		MemberDeclination memberDeclination4 = getMemberDeclination(member4, "I.V. Sava", "ый");
		MemberInsurance memberInsurance4 = getMemberInsurance(member4, "03.05.2021");
		member4.setMemberDeclination(memberDeclination4);
		member4.setMemberInsurance(memberInsurance4);

		Member member5 = getMember("00005", "Samed", "Lil", "Tair", "lil@mail.ru", "lil@gmail.com" );
		MemberDeclination memberDeclination5 = getMemberDeclination(member5, "L.T. Samed", "ая");
		MemberInsurance memberInsurance5 = getMemberInsurance(member5, "30.12.2020");
		member5.setMemberDeclination(memberDeclination5);
		member5.setMemberInsurance(memberInsurance5);

		entityManager.persist(member1);
		entityManager.persist(member2);
		entityManager.persist(member3);
		entityManager.persist(member4);
		entityManager.persist(member5);

		if (entityTransaction.isActive()) {
			entityTransaction.commit();
		}
	}

	private static void readEntities() {
		Member member4 = entityManager.find(Member.class, "00001");
		MemberDeclination memberDeclination4 = entityManager.find(MemberDeclination.class, "00002");
		MemberInsurance memberInsurance4 = entityManager.find(MemberInsurance.class, "00003");
	}

	private static void updateEntities() {
		entityTransaction.begin();

		Member member5 = entityManager.find(Member.class, "00002");
		member5.setMainEmail("ppp@mail.ru");

		entityManager.merge(member5);

		if (entityTransaction.isActive()) {
			entityTransaction.commit();
		}
	}

	private static void deleteEntities() {
		entityTransaction.begin();

		Member member6 = entityManager.find(Member.class, "00001");
		entityManager.remove(member6);

		if (entityTransaction.isActive()) {
			entityTransaction.commit();
		}
	}
}
