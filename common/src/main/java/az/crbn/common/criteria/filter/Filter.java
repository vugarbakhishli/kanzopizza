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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Base class for the various attribute filters. It can be added to a criteria class as a member, to support the
 * following query parameters:
 * <pre>
 *      fieldName.equals='something'
 *      fieldName.notEquals='somethingElse'
 *      fieldName.specified=true
 *      fieldName.specified=false
 *      fieldName.in='something','other'
 *      fieldName.notIn='something','other'
 * </pre>
 */
public class Filter<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private T equals;
    private T notEquals;
    private Boolean specified;
    private List<T> in;
    private List<T> notIn;

    public Filter() {
        // Empty constructor for Filter.
    }

    public Filter(Filter<T> filter) {
        this.equals = filter.equals;
        this.notEquals = filter.notEquals;
        this.specified = filter.specified;
        this.in = filter.in == null ? null : new ArrayList<>(filter.in);
        this.notIn = filter.notIn == null ? null : new ArrayList<>(filter.notIn);
    }

    public Filter<T> copy() {
        return new Filter<>(this);
    }

    public T getEquals() {
        return equals;
    }

    public Filter<T> setEquals(T equals) {
        this.equals = equals;
        return this;
    }

    public T getNotEquals() {
        return notEquals;
    }

    public Filter<T> setNotEquals(T notEquals) {
        this.notEquals = notEquals;
        return this;
    }

    public Boolean getSpecified() {
        return specified;
    }

    public Filter<T> setSpecified(Boolean specified) {
        this.specified = specified;
        return this;
    }

    public List<T> getIn() {
        return in;
    }

    public Filter<T> setIn(List<T> in) {
        this.in = in;
        return this;
    }

    public List<T> getNotIn() {
        return notIn;
    }

    public Filter<T> setNotIn(List<T> notIn) {
        this.notIn = notIn;
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
        final Filter<?> filter = (Filter<?>) object;
        return Objects.equals(equals, filter.equals)
                && Objects.equals(notEquals, filter.notEquals)
                && Objects.equals(specified, filter.specified)
                && Objects.equals(in, filter.in)
                && Objects.equals(notIn, filter.notIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equals, notEquals, specified, in, notIn);
    }

    @Override
    public String toString() {
        return getFilterName() + " ["
                + (getEquals() != null ? "equals=" + getEquals() + ", " : "")
                + (getNotEquals() != null ? "notEquals=" + getNotEquals() + ", " : "")
                + (getSpecified() != null ? "specified=" + getSpecified() + ", " : "")
                + (getIn() != null ? "in=" + getIn() + ", " : "")
                + (getNotIn() != null ? "notIn=" + getNotIn() : "")
                + "]";
    }

    protected String getFilterName() {
        return this.getClass().getSimpleName();
    }

}
