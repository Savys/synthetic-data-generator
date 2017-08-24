package org.stg.core;

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

        int randomItemIndex = 0;
        int endRange = availableItems-1;
        if(endRange > 0) {
            randomItemIndex = RandUtil.getRandomNumberInRange(0, endRange);
        }

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
