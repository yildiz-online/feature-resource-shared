/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.engine.feature.resource;

import be.yildizgames.common.model.EntityId;

/**
 * @author Grégory Van den Borre
 */
public class ResourceValueDto {

    /**
     * Id of the city receiving the resources.
     */
    public final EntityId cityId;

    /**
     * Resources values.
     */
    public final ResourceValue resources;

    /**
     * Time when the value has been set.
     */
    public final long time;

    public ResourceValueDto(EntityId cityId, ResourceValue resources, long time) {
        this.cityId = cityId;
        this.resources = resources;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResourceValueDto that = (ResourceValueDto) o;

        return time == that.time && cityId.equals(that.cityId) && resources.equals(that.resources);
    }

    @Override
    public int hashCode() {
        int result = cityId.hashCode();
        result = 31 * result + resources.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        return result;
    }
}
