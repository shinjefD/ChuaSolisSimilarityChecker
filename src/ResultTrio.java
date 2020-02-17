import java.util.Objects;

class ResultTrio implements Comparable<ResultTrio> {
    private final String first;
    private final String second;
    private final double score;

    public String getFirst(){
        return this.first;
    }

    public String getSecond(){
        return this.second;
    }

    public double getScore(){
        return this.score;
    }

    ResultTrio(String first, String second, double score){
        this.first = first;
        this.second = second;
        this.score = score;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultTrio that = (ResultTrio) o;
        return (Objects.equals(first, that.first) &&
                Objects.equals(second, that.second))
                || (Objects.equals(first, that.second) &&
                Objects.equals(second, that.first));
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public int compareTo(ResultTrio o) {
        return Double.compare(score, o.score);
    }
}
