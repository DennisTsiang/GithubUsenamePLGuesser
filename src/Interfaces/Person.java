package Interfaces;

import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.Map;

public interface Person {

    int getPublicRepoCount() throws IOException;

    Map<String, Repository> getRepositories() throws IOException;
}
