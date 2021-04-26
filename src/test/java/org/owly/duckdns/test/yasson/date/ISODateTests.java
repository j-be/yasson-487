package org.owly.duckdns.test.yasson.date;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.stream.Stream;

class ISODateTests {

    @ParameterizedTest
    @MethodSource("getIsoDates")
    void testIsoDates(String isoDate) {
        Assertions.assertEquals(
                Date.from(LocalDateTime.parse(isoDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneOffset.UTC).toInstant()),
                JsonbBuilder.create().fromJson("\"" + isoDate + "\"", Date.class));
    }

    private static Stream<String> getIsoDates() {
        return Stream.of(
                "2021-04-26T16:14:44.538Z",
                "2021-04-26T16:14:44.538+00:00",
                "2021-04-26T16:14:44.538+0000",
                "2021-04-26T16:14:44.538+00"
        );
    }
}
