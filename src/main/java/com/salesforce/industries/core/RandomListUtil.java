package com.salesforce.industries.core;

import java.util.List;

public class RandomListUtil<T extends BaseDataItem> {

    public void reset(List<T> items) {
        for(int i=0;i<items.size();i++) {
            items.get(i).setSelected(false);
        }
    }

    public T getRandomItem(List<T> items) {

        int availableItems =0;
        for(int i=0;i<items.size();i++) {
            T item = items.get(i);

            if(item.getSelected() == null || !item.getSelected()) {
                availableItems++;
            }
        }
        int endRange = availableItems-1;
        if(endRange < 1) {
            endRange = 0;
        }
        int randomItemIndex = RandUtil.getRandomNumberInRange(0, availableItems-1);
        int current=0;
        T selectedItem=null;
        for(int i=0;i<items.size();i++) {
            T item = items.get(i);
            if(current == randomItemIndex && (item.getSelected() == null || !item.getSelected())) {
                item.setSelected(true);
                selectedItem = item;
                break;
            }
            if((item.getSelected() == null || !item.getSelected())) {
                current++;
            }
        }
        return selectedItem;
    }

}
