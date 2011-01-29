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

import org.joda.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Billing period of a customers billing cycle. The billing period defines the number of
 * days, weeks, months or years between each billing cycle (invoice generation).
 *
 * @author Brian Cowdery
 * @since 26-Oct-2010
 */
@Entity
public class BillingPeriod extends BaseEntity {

    public enum Type { DAY, WEEK, MONTH, YEAR }

    @GeneratedValue @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "period_type")
    private Type type = Type.MONTH;
    @Column(name = "period_interval")
    private Integer interval = 1;

    public BillingPeriod() {
    }

    public BillingPeriod(Type type, Integer interval) {
        this.type = type;
        this.interval = interval;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    /**
     * Returns a Joda Time <code>Period</code> representing the period of time
     * of a single BillingPeriod.
     *
     * @return period of time
     */
    public Period getPeriodOfTime() {
        return getPeriodOfTime(1);
    }

    /**
     * Returns a Joda Time <code>Period</code> representing the period of time
     * between the start of any given cycle and the Nth cycle (give as cycleNumber).
     *
     * @param cycleNumber cycle number
     * @return period of time
     */
    public Period getPeriodOfTime(Integer cycleNumber) {
        Integer interval = cycleNumber * getInterval();

        switch (getType()) {
            case DAY:
                return Period.days(interval);
            case WEEK:
                return Period.weeks(interval);
            case MONTH:
                return Period.months(interval);
            case YEAR:
                return Period.years(interval);
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append(interval)
                .append(" ")
                .append(type.name().toLowerCase());

        // pluralize
        if (interval > 1)
            builder.append("s");

        return builder.toString();
    }
}
