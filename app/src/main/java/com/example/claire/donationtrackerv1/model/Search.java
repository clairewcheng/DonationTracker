package com.example.claire.donationtrackerv1.model;

import android.widget.Button;

class Search {

    // Category Filters
    private boolean clothingFilterIsOff;
    private boolean hatFilterIsOff;
    private boolean householdFilterIsOff;
    private boolean kitchenFilterIsOff;
    private boolean electronicsFilterIsOff;
    private boolean otherFilterIsOff;

    //Location Filters
    private boolean Location1FilterIsOff;
    private boolean Location2FilterIsOff;
    private boolean Location3FilterIsOff;
    private boolean Location4FilterIsOff;
    private boolean Location5FilterIsOff;
    private boolean Location6FilterIsOff;
    private boolean Location7FilterIsOff;
    private boolean Location8FilterIsOff;

    private String activeCategoryFilter;
    private String activeLocationFilter;

    public String getActiveCategoryFilter() {
        return activeCategoryFilter;
    }

    public String getActiveLocationFilter() {
        return activeLocationFilter;
    }

    public void setActiveCategoryFilter(Button button) {
        if (activeCategoryFilter == null) {
            activeCategoryFilter = button.toString();
        } else {
            turnOffAllCategoryFilters();
            activeCategoryFilter = button.toString();
        }
    }

    private void turnOffAllCategoryFilters() {
        clothingFilterIsOff = false;
        hatFilterIsOff = false;
        householdFilterIsOff = false;
        kitchenFilterIsOff = false;
        electronicsFilterIsOff = false;
        otherFilterIsOff = false;
    }

    public void setActiveLocationFilter(Button button) {
        if (activeLocationFilter == null) {
            activeLocationFilter = button.toString();
        } else {
            turnOffAllLocationFilters();
            activeLocationFilter = button.toString();
        }
    }

    private void turnOffAllLocationFilters() {
        Location1FilterIsOff = false;
        Location2FilterIsOff = false;
        Location3FilterIsOff = false;
        Location4FilterIsOff = false;
        Location5FilterIsOff = false;
        Location6FilterIsOff = false;
        Location7FilterIsOff = false;
        Location8FilterIsOff = false;
    }


}
