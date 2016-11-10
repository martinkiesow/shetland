/*
 * Copyright 2016 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.shetland.ogc.swe.simpleType;

import org.n52.shetland.ogc.ows.exception.OwsExceptionReport;
import org.n52.iceland.ogc.swe.SweConstants.SweDataComponentType;
import org.n52.shetland.ogc.swe.RangeValue;
import org.n52.shetland.ogc.swe.SweDataComponentVisitor;
import org.n52.shetland.ogc.swe.VoidSweDataComponentVisitor;

/**
 * SOS internal representation of SWE simpleType quantity
 *
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class SweQuantityRange extends SweAbstractUomType<RangeValue<Double>> implements SweQuality {

    /**
     * axis ID
     */
    private String axisID;

    /**
     * value
     */
    private RangeValue<Double> value;

    /**
     * constructor
     */
    public SweQuantityRange() {
    }

    /**
     * Get axis ID
     *
     * @return the axisID
     */
    public String getAxisID() {
        return axisID;
    }

    /**
     * set axis ID
     *
     * @param axisID
     *            the axisID to set
     * @return This SweQuantityRange
     */
    public SweQuantityRange setAxisID(final String axisID) {
        this.axisID = axisID;
        return this;
    }

    @Override
    public RangeValue<Double> getValue() {
        return value;
    }

    @Override
    public SweQuantityRange setValue(final RangeValue<Double> value) {
        this.value = value;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 97;
        int hash = 7;
        hash = prime * hash + super.hashCode();
        hash = prime * hash + (axisID != null ? axisID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SweQuantityRange other = (SweQuantityRange) obj;
        if ((getAxisID() == null) ? (other.getAxisID() != null) : !getAxisID().equals(other.getAxisID())) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public String getStringValue() {
        return value.toString();
    }

    @Override
    public boolean isSetValue() {
        return value != null;
    }

    public boolean isSetAxisID() {
        return axisID != null && !axisID.isEmpty();
    }

    @Override
    public SweDataComponentType getDataComponentType() {
        return SweDataComponentType.QuantityRange;
    }

    @Override
    public <T> T accept(SweDataComponentVisitor<T> visitor)
            throws OwsExceptionReport {
        return visitor.visit(this);
    }

    @Override
    public void accept(VoidSweDataComponentVisitor visitor)
            throws OwsExceptionReport {
        visitor.visit(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public SweQuantityRange clone() {
        SweQuantityRange clone = new SweQuantityRange();
        copyValueTo(clone);
        if (isSetQuality()) {
            clone.setQuality(cloneQuality());
        }
        if (isSetAxisID()) {
            clone.setAxisID(getAxisID());
        }
        if (isSetValue()) {
            clone.setValue((RangeValue<Double>)getValue().clone());
        }
        return clone;
    }

}