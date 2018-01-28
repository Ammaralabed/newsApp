package lingaraj.hourglass.in.newsapp.restservice.models.responseworldnews;

import java.util.List;


public class Response {
    String status;
    List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
