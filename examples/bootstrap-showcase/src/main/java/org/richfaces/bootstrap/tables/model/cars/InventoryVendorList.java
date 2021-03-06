/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.richfaces.bootstrap.tables.model.cars;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InventoryVendorList implements Serializable {
    private static final long serialVersionUID = -6547391197128734913L;
    private String vendor;
    private List<InventoryVendorItem> vendorItems;

    public InventoryVendorList() {
        vendorItems = new ArrayList<InventoryVendorItem>();
    }

    public long getCount() {
        if (vendorItems != null) {
            return vendorItems.size();
        } else {
            return 0;
        }
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public List<InventoryVendorItem> getVendorItems() {
        return vendorItems;
    }

    public void setVendorItems(List<InventoryVendorItem> vendorItems) {
        this.vendorItems = vendorItems;
    }
}
