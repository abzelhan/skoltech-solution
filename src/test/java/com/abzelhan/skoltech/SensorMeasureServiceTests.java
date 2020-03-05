package com.abzelhan.skoltech;

import com.abzelhan.skoltech.service.SensorMeasureService;
import com.abzelhan.skoltech.service.dto.SensorMeasureAverageDTO;
import com.abzelhan.skoltech.service.dto.SensorMeasureDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SensorMeasureServiceTests extends CommonTestConfiguration {

    @Autowired
    private SensorMeasureService sensorMeasureService;

    private final static LocalDateTime NOW = LocalDateTime.now();
    private final static long NOW_IN_MILLIS = NOW.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    private static boolean initialized = false;

    @BeforeEach
    @Rollback(false)
    @Transactional
    void testSave() {
        if (!initialized) {
            List<SensorMeasureDTO> results = sensorMeasureService.save(Arrays.asList(
                    new SensorMeasureDTO(null, 1L, 1L, NOW_IN_MILLIS, 11.0),
                    new SensorMeasureDTO(null, 1L, 1L, NOW_IN_MILLIS + 1000, 12.0),
                    new SensorMeasureDTO(null, 1L, 1L, NOW_IN_MILLIS + 2000, 13.0),
                    new SensorMeasureDTO(null, 2L, 2L, NOW_IN_MILLIS, 14.1),
                    new SensorMeasureDTO(null, 2L, 2L, NOW_IN_MILLIS + 1000, 15.2),
                    new SensorMeasureDTO(null, 2L, 2L, NOW_IN_MILLIS + 2000, 16.3),
                    new SensorMeasureDTO(null, 3L, 3L, NOW_IN_MILLIS, 17.4),
                    new SensorMeasureDTO(null, 3L, 3L, NOW_IN_MILLIS + 1000, 18.5),
                    new SensorMeasureDTO(null, 3L, 3L, NOW_IN_MILLIS + 2000, 19.6)));

            assertFalse(results.isEmpty());

            for (SensorMeasureDTO result : results) {
                assertNotNull(result.getId());
            }

            initialized = true;
        }


    }

    @Test
    @Order(2)
    @Rollback(false)
    @Transactional
    void testGetHistory() {
        List<SensorMeasureDTO> result = sensorMeasureService.getHistory(1L, NOW, NOW.plusSeconds(2L));

        assertFalse(result.isEmpty());

        for (SensorMeasureDTO dto : result) {
            assertEquals(Long.valueOf(1L), dto.getSensorId());
        }
    }

    @Test
    @Order(3)
    @Rollback(false)
    @Transactional
    void testGetLatest() {
        List<SensorMeasureDTO> result = sensorMeasureService.getLatest(1L);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(Double.valueOf(13.0), result.get(0).getValue());
    }

    @Test
    @Order(4)
    @Rollback(false)
    @Transactional
    void testGetAverages() {
        List<SensorMeasureAverageDTO> result = sensorMeasureService.getAverages();
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());

        for (SensorMeasureAverageDTO dto : result) {
            if (dto.getObjectId() == 1L) {
                assertEquals(Double.valueOf(13.0), dto.getAverage());
            }
            if (dto.getObjectId() == 2L) {
                assertEquals(Double.valueOf(16.3), dto.getAverage());
            }
            if (dto.getObjectId() == 3L) {
                assertEquals(Double.valueOf(19.6), dto.getAverage());
            }
        }

    }

}
