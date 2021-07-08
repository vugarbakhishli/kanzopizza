/*
 * Copyright 2016-2020 the original author or authors from the JHipster project.
 *
 * This file is part of the JHipster project, see https://www.jhipster.tech/
 * for more information.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package az.crbn.common.criteria.filter;

import java.util.Objects;

/**
 * Filter class for Comparable types, where less than / greater than / etc relations could be interpreted. It can be
 * added to a criteria class as a member, to support the following query parameters:
 * <pre>
 *      fieldName.equals=42
 *      fieldName.notEquals=42
 *      fieldName.specified=true
 *      fieldName.specified=false
 *      fieldName.in=43,42
 *      fieldName.notIn=43,42
 *      fieldName.greaterThan=41
 *      fieldName.lessThan=44
 *      fieldName.greaterThanOrEqual=42
 *      fieldName.lessThanOrEqual=44
 * </pre>
 * Due to problems with the type conversions, the descendant classes should be used, where the generic type parameter
 * is materialized.
 *
 * @param <T> the type of filter.
 * @see IntegerFilter
 * @see DoubleFilter
 * @see FloatFilter
 * @see LongFilter
 * @see LocalDateFilter
 * @see InstantFilter
 * @see ShortFilter
 * @see ZonedDateTimeFilter
 */
public class RangeFilter<T extends Comparable<? super T>> extends Filter<T> {

    private static final long serialVersionUID = 1L;

    private T greaterThan;
    private T lessThan;
    private T greaterThanOrEqual;
    private T lessThanOrEqual;

    public RangeFilter() {
        // Empty constructor for RangeFilter.
    }

    public RangeFilter(final RangeFilter<T> filter) {
        super(filter);
        this.greaterThan = filter.greaterThan;
        this.lessThan = filter.lessThan;
        this.greaterThanOrEqual = filter.greaterThanOrEqual;
        this.lessThanOrEqual = filter.lessThanOrEqual;
    }

    @Override
    public RangeFilter<T> copy() {
        return new RangeFilter<>(this);
    }

    public T getGreaterThan() {
        return greaterThan;
    }

    public RangeFilter<T> setGreaterThan(T greaterThan) {
        this.greaterThan = greaterThan;
        return this;
    }

    public T getLessThan() {
        return lessThan;
    }

    public RangeFilter<T> setLessThan(T lessThan) {
        this.lessThan = lessThan;
        return this;
    }

    public T getGreaterThanOrEqual() {
        return greaterThanOrEqual;
    }

    public RangeFilter<T> setGreaterThanOrEqual(T greaterThanOrEqual) {
        this.greaterThanOrEqual = greaterThanOrEqual;
        return this;
    }

    @Deprecated
    public RangeFilter<T> setGreaterOrEqualThan(T greaterThanOrEqual) {
        this.setGreaterThanOrEqual(greaterThanOrEqual);
        return this;
    }

    public T getLessThanOrEqual() {
        return lessThanOrEqual;
    }

    public RangeFilter<T> setLessThanOrEqual(T lessThanOrEqual) {
        this.lessThanOrEqual = lessThanOrEqual;
        return this;
    }

    @Deprecated
    public RangeFilter<T> setLessOrEqualThan(T lessThanOrEqual) {
        this.setLessThanOrEqual(lessThanOrEqual);
        return this;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        final RangeFilter<?> that = (RangeFilter<?>) object;
        return Objects.equals(greaterThan, that.greaterThan)
                && Objects.equals(lessThan, that.lessThan)
                && Objects.equals(greaterThanOrEqual, that.greaterThanOrEqual)
                && Objects.equals(lessThanOrEqual, that.lessThanOrEqual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), greaterThan, lessThan, greaterThanOrEqual, lessThanOrEqual);
    }

    @Override
    public String toString() {
        return getFilterName() + " ["
                + "equals=" + getEquals() + ", "
                + "notEquals=" + getNotEquals() + ", "
                + "specified=" + getSpecified() + ", "
                + "in=" + getIn() + ", "
                + "notIn=" + getNotIn() + ", "
                + "greaterThan=" + getGreaterThan() + ", "
                + "lessThan=" + getLessThan() + ", "
                + "greaterThanOrEqual=" + getGreaterThanOrEqual() + ", "
                + "lessThanOrEqual=" + getLessThanOrEqual()
                + "]";
    }

}
