package babinvas.insurancecontrolaccessdb;

import babinvas.insurancecontrolaccessdb.entities.*;
import babinvas.insurancecontrolaccessdb.services.email.EmailSendingService;
import babinvas.insurancecontrolaccessdb.services.email.TextEmailSendingService;
import babinvas.insurancecontrolaccessdb.services.repository.MemberRepositoryService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO Необходимо удалить все ненужные методы перед запуском

public class Main {
	private static final EmailSendingService emailSendingService = new TextEmailSendingService("username", "password", "host", 465);

	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("nkso-members-and-employers");
		entityManager = entityManagerFactory.createEntityManager();

		entityTransaction = entityManager.getTransaction();

		//TODO Необходимо удалить запуском перед запуском
		// createEntities();
		// readEntities();
		// updateEntities();
		// deleteEntities();

		MemberRepositoryService memberRepositoryService = new MemberRepositoryService(entityManagerFactory);
		// Date date = getDate(new Date());

		//TODO Необходимо удалить запуском перед запуском
		// Date date = date1.
		Date date = getDate("03.05.2021");

		List<Member> memberList = memberRepositoryService.getByInsurance(date);

		for (Member member : memberList) {
			System.out.println(member.getIdRegistryNumber() + " " + member.getSurname());
		}

		//TODO Необходимо удалить запуском перед запуском
		Company company = readCompany("00001", "1");

		entityManager.getEntityManagerFactory().close();
		entityManager.close();
	}

	//TODO Необходимо удалить запуском перед запуском
	private static Company readCompany(String idRegistryNumber, String idCompany) {
		return entityManager.find(Company.class, new CompanyKey(idRegistryNumber, idCompany));
	}

	//TODO Необходимо удалить запуском перед запуском
	private static Member getMember(String idRegistryNumber, String surname, String name, String patronymic, String mainEmail, String secondEmail) {
		Member member = new Member(idRegistryNumber);
		member.setSurname(surname);
		member.setName(name);
		member.setPatronymic(patronymic);
		member.setMainEmail(mainEmail);
		member.setSecondEmail(secondEmail);

		return member;
	}

	//TODO Необходимо удалить запуском перед запуском
	private static MemberDeclination getMemberDeclination(Member member, String abbreviationToWhom, String respectfulWordEnding) {
		MemberDeclination memberDeclination = new MemberDeclination(member);
		memberDeclination.setAbbreviationToWhom(abbreviationToWhom);
		memberDeclination.setRespectfulWordEnding(respectfulWordEnding);

		return memberDeclination;
	}

	//TODO Необходимо удалить запуском перед запуском
	private static MemberInsurance getMemberInsurance(Member member, String expirationDate) {
		MemberInsurance memberInsurance = new MemberInsurance(member);

		Date date = getDate(expirationDate);
		memberInsurance.setExpirationDate(date);

		return memberInsurance;
	}

	//TODO Необходимо удалить запуском перед запуском
	private static Date getDate(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

		try{
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	//TODO Необходимо удалить запуском перед запуском
	private static Date getDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

		try{
			String day = simpleDateFormat.format(date);
			return simpleDateFormat.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	//TODO Необходимо удалить запуском перед запуском
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

	//TODO Необходимо удалить запуском перед запуском
	private static void readEntities() {
		Member member4 = entityManager.find(Member.class, "00001");
		MemberDeclination memberDeclination4 = entityManager.find(MemberDeclination.class, "00002");
		MemberInsurance memberInsurance4 = entityManager.find(MemberInsurance.class, "00003");
	}

	//TODO Необходимо удалить запуском перед запуском
	private static void updateEntities() {
		entityTransaction.begin();

		Member member5 = entityManager.find(Member.class, "00002");
		member5.setMainEmail("ppp@mail.ru");

		entityManager.merge(member5);

		if (entityTransaction.isActive()) {
			entityTransaction.commit();
		}
	}

	//TODO Необходимо удалить запуском перед запуском
	private static void deleteEntities() {
		entityTransaction.begin();

		Member member6 = entityManager.find(Member.class, "00001");
		entityManager.remove(member6);

		if (entityTransaction.isActive()) {
			entityTransaction.commit();
		}
	}

	private static String getTo(Member member) {
		String mails = member.getMainEmail() == null || member.getMainEmail().isEmpty() ?
				"" : member.getMainEmail();
		mails = member.getSecondEmail() == null || member.getSecondEmail().isEmpty() ?
				"" : mails.isEmpty() ?
				member.getSecondEmail() : mails + ", " + member.getSecondEmail();

		return mails;
	}

	private static String getCc(Member member) {
		if (member.getCompanies() == null || member.getCompanies().isEmpty()) return "";

		List<Company> companies = member.getCompanies();
		List<String> emailList = new ArrayList<>();

		for (Company company : companies) {
			String mainEmail = company.getMainEmail();
			String secondEmail = company.getSecondEmail();

			if (mainEmail != null || !mainEmail.isEmpty()) {
				emailList.add(mainEmail);
			}

			if (secondEmail != null || !secondEmail.isEmpty()) {
				emailList.add(secondEmail);
			}
		}

		StringBuilder mails = new StringBuilder(emailList.get(0));

		for (int i = 1; i < emailList.size(); i++) {
			mails.append(", ").append(emailList.get(i));
		}

		return mails.toString();
	}

	private static void sendEmail(Member member) {
		String abbreviationToWhom = member.getMemberDeclination().getAbbreviationToWhom();
		String respectfulWordEnding = member.getMemberDeclination().getRespectfulWordEnding();
		String name = member.getName() + " " + member.getPatronymic();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = member.getMemberInsurance().getExpirationDate();
		String expirationDate = simpleDateFormat.format(date);

		String subject = "Об окончании срока действия договора обязательного страхования";
		String text = "Члену Организации\n" +
				abbreviationToWhom + "\n" +
				"\n" +
				"\n" +
				"\n" +
				"Уважаем"+ respectfulWordEnding + " " + name + "!\n" +
				"\n" +
				"Напоминаем Вам, что срок действия Вашего договора (полиса) обязательного страхования ответственности оценщика истек " + expirationDate + ".\n" +
				"\n" +
				"В соответствии с пунктом 3.2 Положения о членстве в Ассоциации СРО «НКСО» просим Вас представить в Ассоциации СРО «НКСО» копию нового договора (полиса) обязательного страхования ответственности оценщика в течение трех дней после даты его заключения.\n" +
				"\n" +
				"Обращаем Ваше внимание на то, что ответственность оценщика должна быть обязательно застрахована без разрыва срока страхования, в том числе и в период выходных и праздничных дней.\n" +
				"\n" +
				"С уважением,\n" +
				"Организация\n" +
				"тел. (000) 000-00-00";

		emailSendingService.setFrom("from@from.com");
		emailSendingService.setTo(getTo(member));
		emailSendingService.setCc(getCc(member));
		emailSendingService.setBcc("bcc1@bcc1.com, bcc2@bcc2.com");

		emailSendingService.send(subject, text);
	}
}
