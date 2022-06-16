package com.ninjaone.domain.base;

import lombok.Builder;

import java.util.Collections;
import java.util.List;


public class NinjaPage<T> {

    private static final int MINIMAL_PAGE_COUNT = 1;

    private final List<T> content;
    private final int page;
    private final int size;
    private final int totalPages;
    private final long totalElements;

    public List<T> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    private NinjaPage(Builder builder) {
        content = buildContent(builder.content);
        page = builder.page;
        size = builder.size;
        totalPages = builder.totalPages;
        totalElements = builder.totalElements;
    }


    private List<T> buildContent(List<T> content) {
        if (content == null) {
            return List.of();
        }
        return Collections.unmodifiableList(content);
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder<T> {
        private List<T> content;
        private int page;
        private int size;
        private int totalPages;
        private long totalElements;

        private Builder() {
        }

        public Builder content(List<T> val) {
            content = val;
            return this;
        }

        public Builder page(int val) {
            page = val;
            return this;
        }

        public Builder size(int val) {
            size = val;
            return this;
        }

        public Builder totalPages(int val) {
            totalPages = val;
            return this;
        }

        public Builder totalElements(long val) {
            totalElements = val;
            return this;
        }

        public NinjaPage build() {
            return new NinjaPage(this);
        }
    }
}
