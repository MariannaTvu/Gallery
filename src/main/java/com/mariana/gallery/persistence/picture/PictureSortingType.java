package com.mariana.gallery.persistence.picture;

import java.util.Comparator;

/**
 * Created by Maryana on 31.10.2016.
 */
public enum PictureSortingType implements Comparator<Picture> {
    NEWEST("date") {
        @Override
        public int compare(Picture a, Picture b) {
            return Long.compare(b.getDateAdded(), a.getDateAdded());
        }
    },
    MOST_COMMENTED("comments") {
        @Override
        public int compare(Picture a, Picture b) {
            return Integer.compare(b.getPictureComments().size(), a.getPictureComments().size());
        }
    },
    BY_NAME("name") {
        @Override
        public int compare(Picture a, Picture b) {
            return a.getName().compareTo(b.getName());
        }
    };

    private final String viewName;

    PictureSortingType(String viewName) {
        this.viewName = viewName;
    }

    public static PictureSortingType byViewName(String viewName) {
        for (PictureSortingType type : PictureSortingType.values()) {
            if (type.viewName.equals(viewName)) {
                return type;
            }
        }
        return null;
    }
}
