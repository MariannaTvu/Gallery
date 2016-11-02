package com.mariana.gallery.persistence.picture;

import com.google.common.base.Predicate;

/**
 * Created by Maryana on 31.10.2016.
 */
public enum PictureFilteringType implements Predicate<Picture> {
    FOR_SALE("for_sale") {
        @Override
        public boolean apply(Picture picture) {
            return picture.isForSale();
        }
    };

    private final String viewName;

    PictureFilteringType(String viewName) {
        this.viewName = viewName;
    }

    public static PictureFilteringType byViewName(String viewName) {
        for (PictureFilteringType type : PictureFilteringType.values()) {
            if (type.viewName.equals(viewName)) {
                return type;
            }
        }
        return null;
    }
}
