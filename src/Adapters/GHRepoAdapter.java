package Adapters;
import Interfaces.Repository;
import org.kohsuke.github.GHRepository;

public class GHRepoAdapter implements Repository {

    private GHRepository ghRepository;

    public GHRepoAdapter(GHRepository ghRepository) {
        this.ghRepository = ghRepository;
    }

    @Override
    public String getLanguage() {
        return ghRepository.getLanguage();
    }
}
