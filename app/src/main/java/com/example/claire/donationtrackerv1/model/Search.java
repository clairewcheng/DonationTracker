package com.example.claire.donationtrackerv1.model;

import android.widget.Button;

class Search {

    // Category Filters (most were converted to local variables)
    private boolean clothingFilterIsOff;

    private String activeCategoryFilter;
    private String activeLocationFilter;

    public String getActiveCategoryFilter() {
        return activeCategoryFilter;
    }

    public String getActiveLocationFilter() {
        return activeLocationFilter;
    }

    public boolean getClothingFilterIsOff() {return clothingFilterIsOff;}

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
        boolean hatFilterIsOff = false;
        boolean householdFilterIsOff = false;
        boolean kitchenFilterIsOff = false;
        boolean electronicsFilterIsOff = false;
        boolean otherFilterIsOff = false;
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
        boolean location1FilterIsOff = false;
        boolean location2FilterIsOff = false;
        boolean location3FilterIsOff = false;
        boolean location4FilterIsOff = false;
        boolean location5FilterIsOff = false;
        boolean location6FilterIsOff = false;
        boolean location7FilterIsOff = false;
        boolean location8FilterIsOff = false;
    }


}
