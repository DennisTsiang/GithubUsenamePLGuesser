public class Repository implements Comparable<Repository> {

    private String language;
    private int count;

    public Repository (String language) {
        this.language = language;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public int compareTo(Repository o) {
        if (count == o.getCount()) {
            return 0;
        } else if (count > o.getCount()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals (Object o) {
        if (!(o instanceof Repository)) {
            return false;
        }
        return language.equals(((Repository) o).getLanguage());
    }
}
