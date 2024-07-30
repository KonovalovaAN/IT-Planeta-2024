package com.example.weatherhistory.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OffsetPageRequest implements Pageable {
    private final int offset;
    private final int limit;
    private final Sort sort;
    public OffsetPageRequest(int offset, int limit, Sort sort) {
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }
    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pageable previousOrFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pageable first() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pageable withPage(int pageNumber) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasPrevious() {
        throw new UnsupportedOperationException();
    }
}
