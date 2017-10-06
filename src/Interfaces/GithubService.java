package Interfaces;

import java.io.IOException;

public interface GithubService {

    Person getUser(String username) throws IOException;
    int rateLimitRemaining() throws IOException;
}
