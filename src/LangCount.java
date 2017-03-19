public class LangCount implements Comparable<LangCount> {

    private String language;
    private int count;

    public LangCount(String language) {
        this.language = language;
        count = 1;
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
    public int compareTo(LangCount o) {
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
        if (!(o instanceof LangCount)) {
            return false;
        }
        return language.equals(((LangCount) o).getLanguage());
    }
}
