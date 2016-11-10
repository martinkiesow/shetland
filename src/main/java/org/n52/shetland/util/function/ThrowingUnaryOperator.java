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
package org.n52.shetland.util.function;

import java.util.function.Function;

/**
 *
 * @author Christian Autermann
 */

/**
 * Represents an operation on a single operand that produces a result of the
 * same type as its operand.  This is a specialization of {@code Function} for
 * the case where the operand and result are of the same type.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object)}.
 *
 * @param <T> the type of the operand and result of the operator
 * @param <X> the type of the exception that might be thrown
 *
 * @see Function
 * @since 1.8
 */
@FunctionalInterface
public interface ThrowingUnaryOperator<T, X extends Throwable> extends ThrowingFunction<T, T, X> {

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @param <T> the type of the input and output of the operator
     * @param <X> the type of the exception that might be thrown
     * @return a unary operator that always returns its input argument
     */
    static <T, X extends Throwable> ThrowingUnaryOperator<T, X> identity() {
        return t -> t;
    }
}