package com.epam.testing.controller;
/**
 * Dispatch info to have possibility in different situations
 * to make forward or redirect
 *
 * @author rom4ik
 */

public class DispatchInfo {
    private final  boolean redirect;
    private final String page;

    public DispatchInfo(boolean redirect, String page) {
        this.redirect = redirect;
        this.page = page;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public String getPage() {
        return page;
    }
}
