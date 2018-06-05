package net.remick.metrictime;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalTime;

/**
 *
 */
public class LocalTimeConverter {

  private static final long HOURS_PER_DAY = 24;
  private static final long MINUTES_PER_HOUR = 60;
  private static final long SECONDS_PER_MINUTE = 60;
  private static final long NANOS_PER_SECOND = 1000000000;
  private static final BigDecimal NANO_PER_DAY = new BigDecimal(
    HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE * NANOS_PER_SECOND);

  /**
   *
   * @param localTime
   * @return
   */
  public static double toMetricTime(final LocalTime localTime) {
    final BigDecimal nanos = new BigDecimal(localTime.toNanoOfDay(), MathContext.DECIMAL64);
    final BigDecimal mt = nanos.divide(NANO_PER_DAY, MathContext.DECIMAL64);
    return mt.doubleValue();
  }

  /**
   *
   * @param metricTime
   * @return
   */
  public static LocalTime toLocalTime(double metricTime) {
    final BigDecimal mt = new BigDecimal(metricTime, MathContext.DECIMAL64);
    final BigDecimal nanos = NANO_PER_DAY.multiply(mt, MathContext.DECIMAL64);
    return LocalTime.ofNanoOfDay(nanos.longValue());
  }
}
