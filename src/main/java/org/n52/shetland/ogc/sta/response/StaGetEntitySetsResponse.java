/*
 * Copyright 2016-2017 52°North Initiative for Geospatial Open Source
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
package org.n52.shetland.ogc.sta.response;

import org.n52.shetland.ogc.sta.StaConstants;

/**
 * SensorThings GET response for empty resource
 *
 * @author <a href="mailto:m.kiesow@52north.org">Martin Kiesow</a>
 */
public class StaGetEntitySetsResponse extends StaAbstractGetResponse {

    public StaGetEntitySetsResponse() {
        super(StaConstants.Operation.GET_ENTITY_SETS.name());
    }

    public StaGetEntitySetsResponse(String service, String version) {
        super(service, version, StaConstants.Operation.GET_ENTITY_SETS.name());
    }
}
