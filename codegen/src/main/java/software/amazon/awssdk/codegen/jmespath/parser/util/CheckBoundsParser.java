/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.codegen.jmespath.parser.util;

import software.amazon.awssdk.codegen.jmespath.parser.ParseResult;
import software.amazon.awssdk.codegen.jmespath.parser.Parser;
import software.amazon.awssdk.codegen.jmespath.parser.ParserContext;

public final class CheckBoundsParser<T> extends DelegatingParser<T> {
    public CheckBoundsParser(Parser<T> delegate) {
        super(delegate);
    }

    @Override
    public ParseResult<T> parse(int startPosition, int endPosition, ParserContext context) {
        if (startPosition < 0) {
            return ParseResult.error(name(), "Invalid start position: " + startPosition, startPosition);
        }
        if (endPosition > context.input().length()) {
            return ParseResult.error(name(), "Invalid end position: " + endPosition, startPosition);
        }

        return delegate.parse(startPosition, endPosition, context);
    }
}