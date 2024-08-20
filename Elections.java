package ElectionsSystem;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Elections implements Serializable {

	private int monthOfElections;
	private int yearOfElections;

	private Set<Citizen> voters;
	private int votersCapacity = 10;
	private int numOfVoters;
	private ArrayList<politicalParty> parties;
	private int numOfParties;
	private int partiesCapacity = 10;
	private ArrayList<Candidate> candArr;
	private int candidatesCapacity = 10;
	private int kalpiotCapacity = 10;
	private int numOfCandidates;
	private Kalpi kalpi;

	public ArrayList<Candidate> getCandArr() {
		return this.candArr;
	}

	public boolean setCandArr(Candidate[] candidates) {
		for (int i = 0; i < this.numOfCandidates; i++) {

			this.candArr.add(candidates[i]);
		}

		return true;
	}

	public int getNumOfCandidates() {
		return numOfCandidates;
	}

	public void setNumOfCandidates(int numOfCandidates) {
		this.numOfCandidates = numOfCandidates;
	}

	public ArrayList<Kalpi<? extends Citizen>> getKalpiot() {
		return kalpiot;
	}

	public void setKalpiot(ArrayList<Kalpi<? extends Citizen>> kalpiot) {
		this.kalpiot = kalpiot;
	}

	private ArrayList<Kalpi<? extends Citizen>> kalpiot;
	private int numOfKalpiot;

	public int getNumOfKalpiot() {
		return numOfKalpiot;
	}

	public void setNumOfKalpiot(int numOfKalpiot) {
		this.numOfKalpiot = numOfKalpiot;
	}

	public Elections(int month, int year, Citizen[] voters, politicalParty[] parties, Kalpi[] kalpiot,
			Candidate[] candidates) throws IllegalObjectException {
		this.monthOfElections = month;
		this.yearOfElections = year;

		this.numOfVoters = voters.length;

		this.voters = new Set<Citizen>(this.votersCapacity);

		this.setVoters(voters);

		this.numOfParties = parties.length;

		this.parties = new ArrayList<politicalParty>(this.partiesCapacity);

		this.setParties(parties);

		this.numOfKalpiot = kalpiot.length;

		this.kalpiot = new ArrayList<Kalpi<? extends Citizen>>(this.kalpiotCapacity);

		this.candArr = new ArrayList<Candidate>(this.candidatesCapacity);

		this.numOfCandidates = candidates.length;

		this.setCandArr(candidates);



		this.setKalpiot(kalpiot);
	}













	public void setKalpiot(Kalpi[] kalpiot) {
		for (int i = 0; i < this.numOfKalpiot; i++) {
			this.kalpiot.add(kalpiot[i]);

		}

	}

	public boolean equals(Object obj) {

		boolean equal = false;

		if (this.getClass().equals(obj.getClass())) {

			Elections tmp = (Elections) obj;
			if (tmp.monthOfElections == this.monthOfElections && tmp.yearOfElections == this.yearOfElections
					&& tmp.votersCapacity == this.votersCapacity && tmp.numOfVoters == this.numOfVoters
					&& tmp.numOfParties == this.numOfParties && tmp.partiesCapacity == this.partiesCapacity
					&& tmp.numOfKalpiot == this.numOfKalpiot && tmp.kalpiotCapacity == this.kalpiotCapacity) {

				for (int i = 0; i < numOfVoters; i++) {
					if (!(this.voters.get(i).equals(tmp.voters.get(i)))) {
						return false;
					}
				}
				for (int i = 0; i < numOfKalpiot; i++) {
					if (!(this.kalpiot.get(i).equals(tmp.kalpiot.get(i)))) {
						return false;
					}
				}
				for (int i = 0; i < numOfParties; i++) {
					if (!(this.parties.get(i).equals(tmp.parties.get(i)))) {
						return false;
					}
				}

				equal = true;

			}

		}
		return equal;
	}

	public void addKalpi(Kalpi kalpi) {

		this.kalpiot.add(kalpi);
		this.numOfKalpiot++;

		System.out.println("Kalpi has been added successfully");

	}



	public void displayResultInEachKalpi() {

		for (int i = 0; i < this.numOfKalpiot; i++) {
			System.out.println("Kalpi id:" + kalpiot.get(i).getKalpiId());
			for (int k = 0; k < this.numOfParties; k++) {
				int partyCounter = kalpiot.get(i).sumOfVotesForParty(parties.get(k).getNameOfParty());
				System.out.println(parties.get(k).getNameOfParty() + ":" + partyCounter);

				parties.get(k).setNumOfVotes(parties.get(k).getNumOfVotes() + partyCounter);

			}

		}

	}

	public void displayTotalVotes() {
		System.out.println("Total Results");
		for (int i = 0; i < this.numOfParties; i++) {
			System.out.println(parties.get(i).getNameOfParty() + ": " + parties.get(i).getNumOfVotes());
		}
	}

	public void addCitizen(Citizen citizen) {

	

		for (int i = 0; i < this.numOfVoters; i++) {
			if (voters.get(i).getId() == citizen.getId()) {
				System.out.println("citizen alerady exist in voters list");
				return;
			}
		}

		// check if the citizen is allowed to vote (age 18+ only)

		Calendar currentDate = new GregorianCalendar();
		int citizenAge = currentDate.get(Calendar.YEAR) - citizen.getBirthYear();
		if (citizenAge < 18) {
			System.out.println("Citizen is under 18 ,not allowed to vote");
			return;
		}

		this.voters.add(citizen);
		this.numOfVoters++;

		System.out.println("Citizen has been added successfully");

		assignCitizenToKalpi(citizen, true); // call to assign the citizen to an kalpi

	}

	public boolean assignCitizenToKalpi(Citizen citizen, boolean isCitizen) {

		// set his home kalpi to the kalpi

		Calendar currentDate = new GregorianCalendar();
		int citizenAge = currentDate.get(Calendar.YEAR) - citizen.getBirthYear();
		boolean addedSuccessfully = false;

		for (int i = 0; i < this.numOfKalpiot; i++) {

			if (kalpiot.get(i).canVote(citizen)) {

				if (isCitizen) {
					kalpiot.get(i).addCitizenToKalpi(citizen);
				}

				addedSuccessfully = true;

				citizen.setHomeKalpi(kalpiot.get(i));
				return true;

			}

		}




			Citizen[] votersForKalpi = new Citizen[1];
			votersForKalpi[0] = citizen;
			Kalpi kalpi = new Kalpi("Kalpi,Rehovot", votersForKalpi);

			citizen.setHomeKalpi(kalpi);

			addKalpi(kalpi);

		
		return true;
	}

	public void addCandidates(Candidate candidate) {

		this.candArr.add(candidate);
		this.numOfCandidates++;

		System.out.println("candidates has been added successfully");
	}

	public void addPoliticalParty(politicalParty politicalParty) {

		this.parties.add(politicalParty);
		this.numOfParties++;

		System.out.println("Party has been added successfully");
	}

	public int getMonthOfElections() {
		return monthOfElections;
	}

	public boolean setMonthOfElections(int monthOfElections) {
		this.monthOfElections = monthOfElections;
		return true;
	}

	public int getYearOfElections() {
		return yearOfElections;
	}

	public boolean setYearOfElections(int yearOfElections) {
		this.yearOfElections = yearOfElections;
		return true;
	}

	public Set<Citizen> getVoters() {
		return voters;
	}

	public boolean setVoters(Citizen[] voters) throws IllegalObjectException {
		for (int i = 0; i < this.numOfVoters; i++) {
			if (!(voters[i] instanceof Citizen)) {
				throw new IllegalObjectException("citizen object is required");
			}

			this.voters.add(voters[i]);

		}
		return true;
	}

	public int getVotersCapacity() {
		return votersCapacity;
	}

	public boolean setVotersCapacity(int votersCapacity) {
		this.votersCapacity = votersCapacity;
		return true;
	}

	public int getNumOfVoters() {
		return numOfVoters;
	}

	public boolean setNumOfVoters(int numOfVoters) {
		this.numOfVoters = numOfVoters;
		return true;
	}

	public ArrayList<politicalParty> getParties() {
		return parties;
	}

	public boolean setParties(politicalParty[] parties) {
		for (int i = 0; i < this.numOfParties; i++) {

			this.parties.add(parties[i]);
		}

		return true;
	}

	public int getNumOfParties() {
		return numOfParties;
	}

	public boolean setNumOfParties(int numOfParties) {
		this.numOfParties = numOfParties;
		return true;
	}

	public void displayAllKalpis() {

		for (int i = 0; i < this.numOfKalpiot; i++) {
			System.out.println(kalpiot.get(i).toString());
		}
		System.out.println();

	}

	public void displayAllCitizens() {

		for (int i = 0; i < this.numOfVoters; i++) {
			System.out.println(voters.get(i).toString());
		}
		System.out.println();

	}

	public void displayAllPoliticalParties() {

		for (int i = 0; i < this.numOfParties; i++) {
			System.out.println(parties.get(i).toString());
		}
		System.out.println();

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("month of elections : ").append(" ").append(this.monthOfElections).append(",")
				.append("year of elections : ").append(" ").append(this.yearOfElections).append(",")
				.append("number Of citizens allowed to vote: ").append(" ").append(this.numOfVoters).append(",")
				.append("number Of Parties: ").append(" ").append(this.numOfParties).append(",")
				.append("Number of kalpiot: ").append(" ").append(this.numOfKalpiot);
		return sb.toString();
	}

}
