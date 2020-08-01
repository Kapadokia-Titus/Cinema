package kapadokia.nyandoro.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private int page; // ": 1,
    private int total_results; // 7583,
    private int total_pages; //  380,
    private Results results;

    public Movie(int page, int total_results, int total_pages, Results results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}