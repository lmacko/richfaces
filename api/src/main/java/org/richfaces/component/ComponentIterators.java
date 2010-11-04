/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc. and individual contributors
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
package org.richfaces.component;

import java.util.Iterator;

import javax.faces.component.UIComponent;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;

/**
 * @author Nick Belaevski
 * 
 */
public final class ComponentIterators {

    private ComponentIterators() {}

    public static Iterator<UIComponent> parents(final UIComponent component) {
        if (component == null) {
            return Iterators.<UIComponent>emptyIterator();
        }
        
        return new AbstractIterator<UIComponent>() {

            private UIComponent currentComponent = component;
            
            @Override
            protected UIComponent computeNext() {
                currentComponent = currentComponent.getParent();
                
                if (currentComponent == null) {
                    endOfData();
                }
                
                return currentComponent;
            }
        };
    }
    
    public static Iterator<UIComponent> parentsAndSelf(final UIComponent component) {
        if (component == null) {
            return Iterators.<UIComponent>emptyIterator();
        }

        return Iterators.concat(Iterators.singletonIterator(component), parents(component));
    }
}
