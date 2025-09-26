package com.springboot;

import com.springboot.dao.UserRepository;
import com.springboot.entities.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringBootCrudOperationApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootCrudOperationApplication.class);

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringBootCrudOperationApplication.class, args);
		UserRepository userRepository = applicationContext.getBean(UserRepository.class);

		Scanner sc = new Scanner(System.in);
		boolean running = true;

		while (running) {
			logger.info("\nPlease, Select your choice....!!!");
			logger.info("1. Save player information");
			logger.info("2. Select particular player information");
			logger.info("3. Select all player information");
			logger.info("4. Update player information");
			logger.info("5. Delete particular player information");
			logger.info("6. Delete all player information");
			logger.info("7. Exit");
			logger.info("------------------------------------------------------------------");

			int number = sc.nextInt();
			sc.nextLine(); //consume leftover newline

			switch (number) {
				// 1. Save multiple players
				case 1:
					logger.info("How many player records do you want to save?");
					int n = sc.nextInt();
					sc.nextLine();
					for (int i = 1; i <= n; i++) {
						logger.info("Enter player name:");
						String name = sc.nextLine();
						logger.info("Enter player Jersey number:");
						int jerseyNumber = sc.nextInt();
						sc.nextLine();
						logger.info("Enter player role:");
						String role = sc.nextLine();
						logger.info("Enter player age:");
						int age = sc.nextInt();
						logger.info("Enter player contact:");
						Long contact = sc.nextLong();
						sc.nextLine();

						Player player = new Player(name, jerseyNumber, role, age, contact);
						userRepository.save(player);
					}
					logger.info("Information successfully saved!");
					break;

				// 2. Find player by ID
				case 2:
					logger.info("Enter player ID to fetch:");
					int id = sc.nextInt();
					Optional<Player> playerOpt = userRepository.findById(id);
					if (playerOpt.isPresent()) {
						logger.info("Player found: {}", playerOpt.get());
					} else {
						logger.error("No player found with ID: {}", id);
					}
					break;

				// 3. Get all players
				case 3:
					List<Player> allPlayers = userRepository.findAll();
					if (allPlayers.isEmpty()) {
						logger.warn("No players found.");
					} else {
						logger.info("All Players:");
						allPlayers.forEach(player -> logger.info(player.toString()));
					}
					break;

				// 4. Update player info
				case 4:
					logger.info("Enter player ID to update:");
					int updateId = sc.nextInt();
					sc.nextLine();
					Optional<Player> updateOpt = userRepository.findById(updateId);
					if (updateOpt.isPresent()) {
						Player playerToUpdate = updateOpt.get();
						logger.info("Enter player name:");
						String name = sc.nextLine();
						logger.info("Enter player Jersey number:");
						int jerseyNumber = sc.nextInt();
						sc.nextLine();
						logger.info("Enter player role:");
						String role = sc.nextLine();
						logger.info("Enter player age:");
						int age = sc.nextInt();
						logger.info("Enter player contact:");
						Long contact = sc.nextLong();
						sc.nextLine();

						playerToUpdate.setName(name);
						playerToUpdate.setJerseyNumber(jerseyNumber);
						playerToUpdate.setRole(role);
						playerToUpdate.setAge(age);
						playerToUpdate.setContact(contact);
						userRepository.save(playerToUpdate);

						logger.info("Player updated successfully!");
					} else {
						logger.error("No player found with ID: {}", updateId);
					}
					break;

				// 5. Delete player by ID
				case 5:
					logger.info("Enter player ID to delete:");
					int deleteId = sc.nextInt();
					if (userRepository.existsById(deleteId)) {
						userRepository.deleteById(deleteId);
						logger.info("Player deleted successfully!");
					} else {
						logger.error("No player found with ID: {}", deleteId);
					}
					break;

				// 6. Delete all players
				case 6:
					userRepository.deleteAll();
					logger.warn("All players deleted successfully!");
					break;

				// 7. Exit
				case 7:
					running = false;
					logger.info("Exiting... Goodbye!");
					break;

				default:
					logger.warn("Invalid choice, please try again!");
			}
		}

		sc.close();
	}
}
