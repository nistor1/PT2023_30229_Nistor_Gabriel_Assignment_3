package org.example.presentation;

import org.example.presentation.pages.HomePage;
import org.example.presentation.pages.Page;

import javax.swing.*;

public class View extends JFrame {

    private Page page;
    public View() {

    }
    /**
     * Constructor for objects of class View
     */
    public View ( int n) {

        page = new HomePage();

    }

    public void reset() {
        page = new HomePage();
    }
    /**
     * Return the page
     *
     * @return  page to be returned
     */
    public Page getPage() {
        return page;
    }
    /**
     * Set the value of page
     *
     * @param  page is the new value of page
     */
    public void setPage(Page page) {
        this.page = page;
    }

}
