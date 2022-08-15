package nextstep.optional;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersTest {

    @Test
    public void getUser() {
        Users users = new Users();
        assertThat(getUser(users, "crong")).isEqualTo(new User("crong", 35));
    }


    @Test
    public void getDefaultUser() {
        Users users = new Users();
        assertThat(getUser(users, "codesquard")).isEqualTo(Users.DEFAULT_USER);
    }

    private User getUser(Users users, String codesquard) {
        return users.getUser(codesquard).orElse(Users.DEFAULT_USER);
    }
}
