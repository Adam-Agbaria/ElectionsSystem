# Elections Management System

The Elections Management System is a Java-based application designed to manage the logistics of an election. The system facilitates the registration and management of citizens, political parties, candidates, and voting processes. It also integrates with a MySQL database to store and manage data such as election results, citizens, and political parties. The system provides various functionalities, including adding new citizens, kalpis (voting stations), political parties, and candidates, as well as managing the election process and displaying the results.

## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Database Operations](#database-operations)
- [File Structure](#file-structure)
- [Contributing](#contributing)
- [License](#license)

## Features
- **Citizen Management**: Add and manage citizens eligible to vote.
- **Kalpi Management**: Manage voting stations (`Kalpi`) and assign citizens to them.
- **Political Party and Candidate Management**: Add political parties, assign candidates, and manage their details.
- **Elections**: Conduct elections, allow citizens to vote, and calculate the results.
- **Database Integration**: Store and retrieve election-related data in a MySQL database.
- **Error Handling**: Comprehensive error handling and database operation validation.

## Installation

### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or later.
- **MySQL Database**: Ensure you have a MySQL server installed and running.
- **MySQL Connector for Java**: Ensure you have the MySQL JDBC driver available for your Java application.

## Database Setup

1. **Create a MySQL Database**: 
   - Create a MySQL database named `test`.
   - Ensure that the connection URL, username, and password in the code match your MySQL setup.

2. **Automatic Table Creation**: 
   - The application will automatically create the necessary tables when you run the application.

## Usage

Upon launching the application, the user is presented with a menu of options. The following operations can be performed:

1. **Add a Kalpi**: Add a new voting station.
2. **Add a Citizen**: Register a new citizen eligible to vote.
3. **Add a Political Party**: Create a new political party.
4. **Add a Candidate to a Party**: Add a candidate to an existing political party.
5. **Show All Kalpis**: Display all registered voting stations.
6. **Show All Citizens**: Display all registered citizens.
7. **Show All Political Parties**: Display all registered political parties.
8. **Make the Election**: Conduct the election where citizens cast their votes.
9. **Show the Election Results**: Display the results of the election.
10. **Initialize Database**: Populate the database with initial data from the application.
11. **Remove All Data from Database**: Clear all election-related data from the database.
12. **Exit**: Exit the application.

### Advanced Options

- **Sorting**: The program allows sorting of citizens by various criteria (e.g., name, age).
- **Searching**: Perform searches within the citizen and candidate databases.

## Database Operations

The system integrates with a MySQL database to store election data persistently. Key operations include:

- **Insert Operations**: Add citizens, kalpis, political parties, and candidates to the database.
- **Update Operations**: Update election data, such as marking a citizen as having voted.
- **Delete Operations**: Remove all election-related data from the database.
- **Fetch Operations**: Retrieve and display data such as kalpis, citizens, and political parties.

## Database Tables

- **citizenTable**: Stores citizen information, including name, ID, birth year, and voting status.
- **kalpiTable**: Stores kalpi information, including ID, address, number of voters, and vote percentage.
- **politicalPartyTable**: Stores political party information, including name, faction, number of candidates, and votes.
- **candidateTable**: Stores candidate information, linking citizens to their respective political parties.
- **soldiersTable**: Stores soldier information, including whether they are carrying weapons.

