package project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import project.dao.UserRepository;
import project.domain.Role;
import project.domain.User;
import project.service.UserService;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ComponentScan("project")
@DataJpaTest
public class UserServiceTests {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	public void testSaveUser() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		userService.save(getTestUser());

		users = userRepository.findAll();
		assertThat(users, hasSize(1));
	}

	@Test
	public void testFindUserByEmail() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		userService.save(getTestUser());

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User user = userService.getUserByEmail("test1@gmail.com");
		assertNotNull(user);
	}

	@Test
	public void testUserExistsByEmail() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		userService.save(getTestUser());

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		assertTrue(userRepository.existsByEmail("test1@gmail.com"));
	}

	@Test
	public void testFindUserById() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		userService.save(getTestUser());

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User user = userService.findById(users.get(0).getId());
		assertNotNull(user);
	}

	private User getTestUser() {
		User user = new User();
		user.setName("test1");
		user.setSurname("test1");
		user.setEmail("test1@gmail.com");
		user.setPassword("test1");
		user.setRole(Role.ROLE_USER);
		return user;
	}
}
