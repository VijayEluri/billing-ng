/*
 BillingNG, a next-generation billing solution
 Copyright (C) 2010 Brian Cowdery

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as
 published by the Free Software Foundation, either version 3 of the
 License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.
 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see http://www.gnu.org/licenses/agpl-3.0.html
 */

package com.billing.ng.entities;

/**
 * Numbered interface for entities that can generate a human readable identifier
 * number using a {@link NumberPattern}.
 *
 * @author Brian Cowdery
 * @since 02-12-2010
 */
public interface Numbered {

    public String getNumber();

    public NumberPattern getNumberPattern();
    public void setNumberPattern(NumberPattern numberPattern);
    
    public void generateNumber();

}
