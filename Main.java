package ElectionsSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Main implements Menu {

	public static void main(String[] args) throws IllegalObjectException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Scanner s = new Scanner(System.in);

		int choice;
		boolean loopC = true;

		Citizen[] votersForTheKalpi = new Citizen[0];


		Kalpi kalpi1 = new Kalpi("Ironei D highschool,Tel Aviv", votersForTheKalpi);
		Kalpi kalpi2 = new Kalpi("Ironei H highschool,Rehovot", votersForTheKalpi);

		Citizen citizen1 = new Citizen("miki", 201231185, 1996, kalpi1);
		Citizen citizen2 = new Citizen("lev", 201231186, 1996, kalpi2);
		Citizen citizen3 = new Citizen("david", 201231187, 1996, kalpi1);
		Citizen citizen4 = new Citizen("misho", 201231188, 1996, kalpi2);
		Citizen citizen5 = new Citizen("adam", 201231189, 1996, kalpi1);
		Citizen citizen6 = new Citizen("Haim", 201231190, 1996, kalpi2);

		Citizen[] citizens = new Citizen[6];
		citizens[0] = citizen1;
		citizens[1] = citizen2;
		citizens[2] = citizen3;
		citizens[3] = citizen4;
		citizens[4] = citizen5;
		citizens[5] = citizen6;

		Candidate candidate1 = new Candidate(citizen1.getCitizenName(), citizen1.getId(), citizen1.getBirthYear(),
				citizen1.getHomeKalpi(), "Likud");
		Candidate candidate2 = new Candidate(citizen2.getCitizenName(), citizen2.getId(), citizen2.getBirthYear(),
				citizen1.getHomeKalpi(), "Likud");
		Candidate candidate3 = new Candidate(citizen3.getCitizenName(), citizen3.getId(), citizen3.getBirthYear(),
				citizen1.getHomeKalpi(), "KaholLavan");
		Candidate candidate4 = new Candidate(citizen4.getCitizenName(), citizen4.getId(), citizen4.getBirthYear(),
				citizen1.getHomeKalpi(), "KaholLavan");
		Candidate candidate5 = new Candidate(citizen5.getCitizenName(), citizen5.getId(), citizen5.getBirthYear(),
				citizen1.getHomeKalpi(), "YeshAtid");
		Candidate candidate6 = new Candidate(citizen6.getCitizenName(), citizen6.getId(), citizen6.getBirthYear(),
				citizen1.getHomeKalpi(), "YeshAtid");

		Candidate[] candidates = new Candidate[6];

		candidates[0] = candidate1;
		candidates[1] = candidate2;
		candidates[2] = candidate3;
		candidates[3] = candidate4;
		candidates[4] = candidate5;
		candidates[5] = candidate6;

		Candidate[] likudCandidates = new Candidate[2];
		likudCandidates[0] = candidate1;
		likudCandidates[1] = candidate2;

		Candidate[] kaholLavanCandidates = new Candidate[2];
		kaholLavanCandidates[0] = candidate3;
		kaholLavanCandidates[1] = candidate4;

		Candidate[] yeshAtidCandidates = new Candidate[2];
		yeshAtidCandidates[0] = candidate5;
		yeshAtidCandidates[1] = candidate6;

		int year = LocalDate.now().getYear();
		int month = LocalDate.now().getMonthValue();

		politicalParty miflaga1 = new politicalParty("Likud", year, month, Faction.RIGHT, likudCandidates);
		politicalParty miflaga2 = new politicalParty("KaholLavan", year, month, Faction.CENTER, kaholLavanCandidates);
		politicalParty miflaga3 = new politicalParty("YeshAtid", year, month, Faction.LEFT, yeshAtidCandidates);

		politicalParty[] parties = new politicalParty[3];
		parties[0] = miflaga1;
		parties[1] = miflaga2;
		parties[2] = miflaga3;

		Citizen[] votersForKalpi1 = new Citizen[3];

		votersForKalpi1[0] = citizen1;
		votersForKalpi1[1] = citizen2;
		votersForKalpi1[2] = citizen3;

		Citizen[] votersForKalpi2 = new Citizen[3];

		votersForKalpi2[0] = citizen4;
		votersForKalpi2[1] = citizen5;
		votersForKalpi2[2] = citizen6;

		Kalpi[] kalpiot = new Kalpi[2];
		kalpiot[0] = kalpi1;
		kalpiot[1] = kalpi2;

		Citizen[] voters = new Citizen[8];
		voters[0] = citizen1;
		voters[1] = citizen2;
		voters[2] = citizen3;
		voters[3] = citizen4;
		voters[4] = citizen5;
		voters[5] = citizen6;

		Citizen citizen7 = new Citizen("momo", 123666781, 1993, kalpi1);
		Citizen citizen8 = new Citizen("bibu", 123666424, 1991, kalpi2);
		voters[6] = citizen7;
		voters[7] = citizen8;

		Soldier soldiers[] = new Soldier[1];
		Soldier soldier = new Soldier("miki", 201231185, 1996, kalpi1, true);
		soldiers[0] = soldier;
		

		Elections electionRound = new Elections(04, 2021, voters, parties, kalpiot, candidates);

		for (int i = 0; i < electionRound.getNumOfVoters(); i++) {
			electionRound.assignCitizenToKalpi(electionRound.getVoters().get(i), true);
		}

		for (int i = 0; i < electionRound.getNumOfParties(); i++) {
			for (int j = 0; j < electionRound.getParties().get(i).getNumOfCandidates(); j++) {
				electionRound.assignCitizenToKalpi(electionRound.getParties().get(i).getCandidates().get(j), false);
			}

		}

		do {
			Main main = new Main();
			System.out.println();
			System.out.println("Please enter an option (1-10)");
			System.out.println("1 - add a Kalpi");
			System.out.println("2 - add a Citizen");
			System.out.println("3 - add a political Party");
			System.out.println("4 - add Candidate to a political party");
			System.out.println("5 - show all kalpis");
			System.out.println("6 - show all citizens");
			System.out.println("7 - show all political parties");
			System.out.println("8 - make the election");
			System.out.println("9 - show the results of the election");
			System.out.println("10 - init data base");
			System.out.println("11 - Remove all data from data base");
			System.out.println("12 - Exit");
			System.out.println();

			System.out.print("Enter your choice --> : ");
			choice = s.nextInt();

			switch (choice) {

			case 1: {
				main.addKalpi(electionRound);
				break;

			}

			case 2: {

				main.addCitizen(electionRound);
				break;

			}

			case 3: {
				main.addPoliticalParty(electionRound);

				break;
			}
			case 4: {
				main.addCandidate(electionRound);

				break;

			}

			case 5: {

				main.showAllKalpis(electionRound);
				break;
			}

			case 6: {
				main.showAllCitizen(electionRound);
				break;
			}
			case 7: {
				main.showAllPoliticalParties(electionRound);
				break;
			}
			case 8: {
				main.makeElections(electionRound);
				break;

			}
			case 9: {
				main.showElectionsResult(electionRound);
				break;

			}

			case 10: {

				initDB(electionRound, candidates, soldiers);

				break;

			}
			case 11: {

				deleteFromDB(electionRound, soldiers);

				break;

			}
			case 12: {

				loopC = false;

				break;

			}


			default: {

				System.out.println("Invalid option , please enter again (1-10) ");

			}

			}

		} while (loopC);

		System.out.println("bye bye!");
		s.close();

	}

	public static void deleteFromDB(Elections elections, Soldier[] soldiers)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {


		for (int i = 0; i < elections.getNumOfCandidates(); i++) {
			deleteCandidateFromDB(elections.getCandArr().get(i));
		}


		for (int i = 0; i < elections.getNumOfParties(); i++) {
			deletePoliticalPartyFromDB(elections.getParties().get(i));
		}
		for (int i = 0; i < soldiers.length; i++) {
			deleteSoldierFromDB(soldiers[i]);
		}

		for (int i = 0; i < elections.getNumOfVoters(); i++) {
			deleteCitizenFromDB(elections.getVoters().get(i));

		}

		for (int i = 0; i < elections.getNumOfKalpiot(); i++) {
			deleteKalpiFromDB(elections.getKalpiot().get(i));
		}

	}

	@Override
	public void addKalpi(Elections electionRound) {
		String address;
		int num;

		Citizen[] votersForKalpi4 = new Citizen[1];

		Scanner s = new Scanner(System.in);

		System.out.println("Please type adress for the kalpi : ");
		address = s.next();

	
		Kalpi kalpi = new Kalpi(address, votersForKalpi4);
		electionRound.addKalpi(kalpi);

			try {
				insertKalpiToDB(kalpi);
			} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException| SecurityException e) {
				e.printStackTrace();
		}
		

			
		

	}

	@Override
	public void addCitizen(Elections electionRound) {
		String name;
		int id;
		int yearOfBirth;

		Scanner s = new Scanner(System.in);
		System.out.println("Please type the name of the citizen you would like to create : ");
		name = s.next();
		System.out.println("Please type the id of the citizen you would like to create(9 digits):");
		id = s.nextInt();
		System.out.println("Please Type the birth year of the citizen you would like to create :");
		yearOfBirth = s.nextInt();

		Citizen citizen8 = new Citizen(name, id, yearOfBirth, null);

		electionRound.addCitizen(citizen8);

		try {
			insertCitizenToDB(citizen8);
		} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addPoliticalParty(Elections electionRound) {
		String name;
		String factionSide;
		Faction factionOfTheParty;

		int year = LocalDate.now().getYear();
		int month = LocalDate.now().getMonthValue();

		Scanner s = new Scanner(System.in);
		System.out.println("please type name of the political Party you would like to add : ");
		name = s.next();

		System.out.println("Please write the faction of the party you would like to add: (CENTER,LEFT,RIGHT)");
		factionSide = s.next();

		Candidate[] candidates = new Candidate[1];

		Faction[] factions = Faction.values();
		for (int i = 0; i < factions.length; i++) {
			if (factions[i].toString().equalsIgnoreCase(factionSide)) {
				factionOfTheParty = Faction.values()[i];
				politicalParty party5 = new politicalParty(name, month, year, factionOfTheParty, candidates);
				electionRound.addPoliticalParty(party5);
				try {
					insertPoliticalPartyToDB(party5);
				} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException| SecurityException e) {
					e.printStackTrace();
				}
				break;
			}
		}

	}

	@Override
	public void addCandidate(Elections electionRound) {
		int choosenNumOfCitizen;
		String nameOfParty;
		Scanner s = new Scanner(System.in);
		System.out.println("Choose one of the citizens's spot to use the citizen to add candidate from : "
				+ getIndexOfCitizensNotCandidate(electionRound));
		choosenNumOfCitizen = s.nextInt();

		System.out.println(
				"Enter the name of the party you would like to add the candidate to :(Likud,KaholLavan,YeshAtid) ");
		nameOfParty = s.next();

		for (int i = 0; i < electionRound.getNumOfVoters(); i++) {
			if (choosenNumOfCitizen == i) {
				Candidate candidate = new Candidate(electionRound.getVoters().get(i).getCitizenName(),
						electionRound.getVoters().get(i).getId(), electionRound.getVoters().get(i).getBirthYear(),
						electionRound.getVoters().get(i).getHomeKalpi(), nameOfParty);
				for (int j = 0; j < electionRound.getNumOfParties(); j++) {
					if (electionRound.getParties().get(j).getNameOfParty().equalsIgnoreCase(nameOfParty)) {
						electionRound.getParties().get(j).addCandidate(candidate);

						electionRound.addCandidates(candidate);

						break;
					}
				}
				try {
					insertCandidateToDB(candidate);

				} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public String getIndexOfCitizensNotCandidate(Elections electionRoun) {
		String indexs = "";
		Boolean isNotCandidate = true;

		for (int i = 0; i < electionRoun.getNumOfVoters(); i++) {

			for (int j = 0; j < electionRoun.getNumOfCandidates(); j++) {
				if (electionRoun.getVoters().get(i).getId() == electionRoun.getCandArr().get(j).getId()) {
					isNotCandidate = false;
					break;
				}

			}
			if (isNotCandidate == true) {
				indexs += " " + i;
			} else
				isNotCandidate = true;
		}

		return indexs;
	}

	@Override
	public void showAllKalpis(Elections electionRound) {
		electionRound.displayAllKalpis();

	}

	@Override
	public void showAllCitizen(Elections electionRound) {
		electionRound.displayAllCitizens();

	}

	@Override
	public void showAllPoliticalParties(Elections electionRound) {
		electionRound.displayAllPoliticalParties();

	}

	@Override
	public void makeElections(Elections electionRound) {
		boolean answer;
		String vote = null;
		boolean wearMask;
		Scanner s = new Scanner(System.in);

		for (int i = 0; i < electionRound.getNumOfVoters(); i++) {
			if (!(electionRound.getVoters().get(i).getIsVotedAlready())) {
				System.out.println("Would you like to vote? (True/False): ");
				answer = s.nextBoolean();

				if (answer == true) {
					System.out.println("Please type the name of party you would like to vote to : ");
					vote = s.next();
					electionRound.getVoters().get(i).vote(vote);
						} else {
							break;
						}
					} else {
						System.out.println("Please type the name of party you would like to vote to : ");
						vote = s.next();
						electionRound.getVoters().get(i).vote(vote);
					}

					int numOfVotes = findNumOfVotesInParty(vote, electionRound);
					try {
						updateVoted(electionRound.getVoters().get(i), vote, numOfVotes);
					} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException
							| SecurityException e) {
						e.printStackTrace();
					}

				}

			}

		

	

	private int findNumOfVotesInParty(String vote, Elections electionRound) {

		for (int i = 0; i < electionRound.getNumOfParties(); i++) {

			if (electionRound.getParties().get(i).getNameOfParty().equals(vote)) {

				electionRound.getParties().get(i).setNumOfVotes(electionRound.getParties().get(i).getNumOfVotes() + 1);

				return electionRound.getParties().get(i).getNumOfVotes();
			}

		}

		return 0;
	}

	@Override
	public void showElectionsResult(Elections electionRound) {
		electionRound.displayResultInEachKalpi();
		System.out.println();
		electionRound.displayTotalVotes();

	}

	public static void insertCitizenToDB(Citizen citizen)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		String str;
		int id = citizen.getId();
		int birthYear = citizen.getBirthYear();
		boolean alreadyVoted = citizen.getIsVotedAlready();
		int val = (alreadyVoted) ? 1 : 0;
		String name = citizen.getCitizenName();
		int kalpiId = citizen.getHomeKalpi().getKalpiId();
		int zero = 0;
		str = "INSERT INTO citizenTable VALUES (" + id + "," + birthYear + "," + val + "," + zero + ",'" + name + "',"
				+ kalpiId + ");";

		connection(str);

	}

	public static void updateVoted(Citizen citizen, String vote, int numOfVotesOfParty)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		System.out.println("f" + numOfVotesOfParty);
		String str;
		int id = citizen.getId();
		int kalpiId = citizen.getHomeKalpi().getKalpiId();
		int numOfVotes = citizen.getHomeKalpi().getNumOfVotes();
		int votePercent = (int) citizen.getHomeKalpi().getKalpiVotingPercent();
		str = "update citizenTable set citizenVotedAlerady = 1 where citizenId = " + id + " ;";
		connection(str);
		String str1;
		str1 = "update kalpitable set numOfVotes = " + numOfVotes + " where kalpiId = " + kalpiId + " ;";
		connection(str1);
		String str2;
		str2 = "update kalpitable set kalpiVotePercent = " + votePercent + " where kalpiId = " + kalpiId + " ;";
		connection(str2);
		String str3;
		str3 = "update politicalpartytable set numOfVotes = " + numOfVotesOfParty + " where title = '" + vote + "' ;";
		connection(str3);

	}

	public static void insertPoliticalPartyToDB(politicalParty party)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		int month = party.getMonth();
		int year = party.getYear();
		String title = party.getNameOfParty();
		String wing = party.getFactions().toString();
		int numOfCandidates = party.getNumOfCandidates();
		int numOfVotes = party.getNumOfVotes();
		String str;

		str = "INSERT INTO politicalPartyTable VALUES ('" + title + "'," + month + "," + year + ",'" + wing + "','"
				+ numOfCandidates + "'," + numOfVotes + ");";

		connection(str);

	}

	public static void insertKalpiToDB(Kalpi kalpi)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		int id = kalpi.getKalpiId();
		String address = kalpi.getKalpiAddress();
		int currNumOfVoters = kalpi.getCurrentNumOfKalpiVoters();
		int percentOfVotes = (int) Math.floor(kalpi.getKalpiVotingPercent());
		int numOfVotes = kalpi.getNumOfVotes();
		String str;
		str = "INSERT INTO kalpitable VALUES (" + id + ",'" + address + "'," + currNumOfVoters + "," + percentOfVotes
				+ "," + numOfVotes + ");";

		connection(str);

	}

	public static void insertCandidateToDB(Candidate candidate)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		int id = candidate.getId();
		String party = candidate.getCandidateParty();

		String str;
		str = "INSERT INTO candidatetable VALUES (" + id + ",'" + party + "');";

		connection(str);

	}

	public static void insertSoldier(Soldier soldier)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		int id = soldier.getId();
		boolean isCarryWeapon = soldier.isCarryWeapon;
		int val = (isCarryWeapon) ? 1 : 0;
		String str;
		str = "INSERT INTO soldierstable VALUES (" + id + "," + isCarryWeapon + ");";

		connection(str);

	}




	public static void deleteKalpiFromDB(Kalpi kalpi)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		int k = kalpi.getKalpiId();
		String str;
		str = "DELETE FROM kalpitable where kalpiId = " + k + " ;";

		connection(str);

	}


	public static void deleteCitizenFromDB(Citizen citizen)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		int k = citizen.getId();
		String str;
		str = "DELETE FROM citizentable where citizenId = " + k + " ;";

		connection(str);

	}



	public static void deleteSoldierFromDB(Soldier citizen)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		int k = citizen.getId();
		String str;
		str = "DELETE FROM soldierstable where CID = " + k + " ;";

		connection(str);

	}

	public static void deleteCandidateFromDB(Candidate citizen)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		int k = citizen.getId();
		String str;
		str = "DELETE FROM candidatetable where CID = " + k + " ;";

		connection(str);

	}

	public static void deletePoliticalPartyFromDB(politicalParty party)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		String k = party.getNameOfParty();
		String str;
		str = "DELETE FROM politicalpartytable where title = '" + k + "' ;";

		connection(str);

	}

	public static void initDB(Elections elections, Candidate[] candidates, Soldier[] soldiers)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		for (int i = 0; i < elections.getNumOfKalpiot(); i++) {
			insertKalpiToDB(elections.getKalpiot().get(i));

		}


		for (int i = 0; i < elections.getNumOfVoters(); i++) {
			insertCitizenToDB(elections.getVoters().get(i));

		}

		for (int i = 0; i < elections.getNumOfParties(); i++) {
			insertPoliticalPartyToDB(elections.getParties().get(i));

		}
		for (int i = 0; i < elections.getNumOfCandidates(); i++) {
			insertCandidateToDB(elections.getCandArr().get(i));

		}

		for (int i = 0; i < soldiers.length; i++) {

			insertSoldier(soldiers[i]);
		}

		

	}

	public static void connection(String string)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			String codex = "Adam9876";

			String dbUrl = "jdbc:mysql://localhost:3306/test";

			conn = DriverManager.getConnection(dbUrl, "root", codex);

			Statement stmt = conn.createStatement();

			stmt.executeUpdate(string);

			conn.close();

		}

		catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
