import Output.StdOutput;
import Output.View;
import org.junit.Test;
import org.kohsuke.github.GHPerson;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.Deque;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GithubClientTests {

    @Test
    public void canFindValidUser() throws IOException {
        View view = new StdOutput();
        GHPerson result = GithubClient.findUser("octocat", view);
        assertNotEquals(null, result);
    }

    @Test
    public void returnsNullsForInvalidUsername() throws IOException {
        View view = new StdOutput();
        GHPerson result = GithubClient.findUser("safdge", view);
        assertEquals(null, result);
    }

    @Test
    public void grabUserReposReturnsHashMapOfUserRepos () throws IOException {
        View view = new StdOutput();
        GHPerson user = GithubClient.findUser("octocat", view);
        Map<String, GHRepository> repos = GithubClient.grabUserRepos(user,
                view);
        assertEquals(7, repos.size());
    }

    @Test
    public void tabulateLanguagesReturnsSortedQueue() throws IOException {
        View view = new StdOutput();
        GHPerson user = GithubClient.findUser("DennisTsiang", view);
        assertNotEquals(null, user);
        Map<String, GHRepository> repos = GithubClient.grabUserRepos(user,
                view);
        assertEquals(3, repos.size());
        Deque<LangCount> queue = GithubClient.tabulateLanguages(repos);
        LangCount frontRepo = queue.poll();
        assertEquals(2, frontRepo.getCount());
        assertEquals("Java", frontRepo.getLanguage());
    }
}
