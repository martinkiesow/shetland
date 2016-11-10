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
package org.n52.shetland.i18n;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;

public class MultilingualString implements Iterable<LocalizedString>, Serializable {
    private static final long serialVersionUID = -1120455418520277338L;
    private final Map<Locale, LocalizedString> localizations = Maps.newHashMap();

    public MultilingualString addLocalization(String lang, String value) {
        return addLocalization(new LocalizedString(new Locale(lang), value));
    }

    public MultilingualString addLocalization(Locale lang, String value) {
        return addLocalization(new LocalizedString(lang, value));
    }

    public MultilingualString addLocalization(LocalizedString value) {
        this.localizations.put(value.getLang(), value);
        return this;
    }

    public Optional<LocalizedString> getLocalization(Locale lang) {
        return Optional.fromNullable(getLocalizations().get(lang));
    }

    public Optional<LocalizedString> getLocalizationOrDefault(Locale lang, Locale defaultLocale) {
        return getLocalization(lang).or(getLocalization(defaultLocale));
    }

    public MultilingualString filter(Locale locale, Locale defaultLocale, boolean showAll) {
        if (locale == null) {
            return showAll ? this : only(defaultLocale);
        } else {
            return hasLocale(locale) ? only(locale) : only(defaultLocale);
        }
    }

    public Set<Locale> getLocales() {
        return Collections.unmodifiableSet(getLocalizations().keySet());
    }

    public boolean hasLocale(Locale locale) {
        return getLocales().contains(locale);
    }

    @Override
    public Iterator<LocalizedString> iterator() {
        return getLocalizations().values().iterator();
    }

    public int size() {
        return getLocalizations().size();
    }

    public boolean isEmpty() {
        return getLocalizations().isEmpty();
    }

    @Override
    public String toString() {
        ToStringHelper h = MoreObjects.toStringHelper(this).omitNullValues();
        for (Entry<Locale, LocalizedString> e : getLocalizations().entrySet()) {
            h.add(e.getKey().toString(), e.getValue().getText());
        }
        return h.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getLocalizations());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MultilingualString) {
            MultilingualString that = (MultilingualString) obj;
            return Objects.equal(this.getLocalizations(),
                                 that.getLocalizations());
        }
        return false;
    }

    public Map<Locale, LocalizedString> getLocalizations() {
        return Collections.unmodifiableMap(this.localizations);
    }

    public MultilingualString setLocalizations(Map<String, String> localizations) {
        this.localizations.clear();
        for (Entry<String, String> localization : localizations.entrySet()) {
            addLocalization(localization.getKey(), localization.getValue());
        }
        return this;
    }

    public MultilingualString only(Locale... locale) {
        return only(Arrays.asList(locale));
    }

    public MultilingualString only(Iterable<Locale> locales) {
        MultilingualString mls = new MultilingualString();
        for (Locale locale : locales) {
            Optional<LocalizedString> localization = getLocalization(locale);
            if (localization.isPresent()) {
                mls.addLocalization(localization.get());
            }
        }
        return mls;
    }

    public Stream<LocalizedString> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}