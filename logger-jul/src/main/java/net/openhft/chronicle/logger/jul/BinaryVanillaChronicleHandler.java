/*
 * Copyright 2014 Higher Frequency Trading
 *
 * http://www.higherfrequencytrading.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.openhft.chronicle.logger.jul;

import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptAppender;
import net.openhft.chronicle.logger.IndexedLogAppenderConfig;

import java.io.IOException;

public class BinaryVanillaChronicleHandler extends BinaryChronicleHandler {
    private ExcerptAppender appender;
    private IndexedLogAppenderConfig config;

    public BinaryVanillaChronicleHandler() throws IOException {
        super();

        this.appender = null;
        this.config = null;
    }

    @Override
    protected Chronicle createChronicle() throws IOException {
        Chronicle chronicle = (this.config != null)
            ? this.config.build(this.getPath())
            : ChronicleQueueBuilder.indexed(this.getPath()).build();

        this.appender = chronicle.createAppender();

        return chronicle;
    }

    @Override
    protected ExcerptAppender getAppender()  {
        try {
            return getChronicle().createAppender();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
