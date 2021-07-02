package com.example.syzer.request_items.for_hometask;

import java.util.List;

public class ListResources {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<Resource> data;

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Resource> getData() {
        return data;
    }
}
