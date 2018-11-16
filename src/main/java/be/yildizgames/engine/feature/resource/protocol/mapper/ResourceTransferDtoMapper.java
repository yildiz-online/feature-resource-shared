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

package be.yildizgames.engine.feature.resource.protocol.mapper;

import be.yildizgames.common.mapping.IntegerMapper;
import be.yildizgames.common.mapping.ObjectMapper;
import be.yildizgames.common.mapping.Separator;
import be.yildizgames.common.mapping.model.PlayerIdMapper;
import be.yildizgames.engine.feature.resource.ResourceTransferDto;
import be.yildizgames.engine.feature.resource.TransferCause;


/**
 * @author Grégory Van den Borre
 */
public class ResourceTransferDtoMapper implements ObjectMapper<ResourceTransferDto> {

    private static final ResourceTransferDtoMapper INSTANCE = new ResourceTransferDtoMapper();

    private ResourceTransferDtoMapper() {
        super();
    }

    public static ResourceTransferDtoMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public ResourceTransferDto from(String s) throws MappingException {
        assert s != null;
        String[] v = s.split(Separator.OBJECTS_SEPARATOR);
        try {
            return new ResourceTransferDto(
                    PlayerIdMapper.getInstance().from(v[0]),
                    PlayerIdMapper.getInstance().from(v[1]),
                    ResourceValueMapper.getInstance().from(v[2]),
                    TransferCause.valueOf(IntegerMapper.getInstance().from(v[3]))
            );
        } catch (IndexOutOfBoundsException e) {
            throw new MappingException(e);
        }
    }

    @Override
    public String to(ResourceTransferDto dto) {
        assert dto != null;
        return PlayerIdMapper.getInstance().to(dto.receiver)
                + Separator.OBJECTS_SEPARATOR
                + PlayerIdMapper.getInstance().to(dto.giver)
                + Separator.OBJECTS_SEPARATOR
                + ResourceValueMapper.getInstance().to(dto.resources)
                + Separator.OBJECTS_SEPARATOR
                + IntegerMapper.getInstance().to(dto.cause.value);
    }
}
