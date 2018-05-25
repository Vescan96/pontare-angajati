package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.User;

class UserTest {

	@Test
	void test() {
		User user = new User("abc", "abc");
		
		assertEquals("abc", user.getUsername());
		
	}

}
